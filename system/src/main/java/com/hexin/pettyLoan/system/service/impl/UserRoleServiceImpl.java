package com.hexin.pettyLoan.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.common.SimpleWorkflowConstants;
import com.hexin.pettyLoan.common.aop.NeedApprove;
import com.hexin.pettyLoan.system.handler.CacheFlushHandler;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.model.UserRoleItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.UserRoleService;



@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	@Resource(name="cacheFlushHandler")
	CacheFlushHandler cacheFlushHandler;
	
	public void initMethod(){
		writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
		readDao = WebApplicationContextHolder.getBean("readDaoHelper");
		cacheFlushHandler = WebApplicationContextHolder.getBean("cacheFlushHandler");
	}

	@Override
	@AccessLog(description="add one role to a user",operateType=OperateType.Insert,add2DB=Add2DB.Yes)
	@NeedApprove(workflowName=SimpleWorkflowConstants.DISTRIBUTE_AUTHORITY, description="用户<0:userId>，分配了角色<0:roleId>")
	public UserRoleItem insertUserRole(UserRoleItem item) {
		try{
			writeDao.insert(UserRoleItem.NAMESPACE, item);
			cacheFlushHandler.FlushUserFunction(item.getRoleId());
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00005",ErrorCode.SYS00005,ex);
		}
		return item;
	}

	@Override
	@AccessLog(description="remove one role from a user",operateType=OperateType.Delete,add2DB=Add2DB.Yes)
	public Boolean deleteUserRole(UserRoleItem item) {
		item = readDao.getOne(UserRoleItem.NAMESPACE, item);
		Integer retCount = (Integer) writeDao.delete(UserRoleItem.NAMESPACE, item);
		cacheFlushHandler.FlushUserFunction(item.getRoleId());
		return retCount>0;
	}

	@Override
	public List<RoleItem> queryUserRolePage(UserinfoItem params) {
		// TODO Auto-generated method stub
		List<RoleItem> list = readDao.query(UserRoleItem.NAMESPACE,params);
		return list;
	}

	@Override
	public List<UserinfoItem> queryUserByRole(Integer roleId){
		RoleItem role = new RoleItem();
		role.setId(roleId);
		return readDao.query(UserRoleItem.NAMESPACE,UserRoleItem.SQLID_UBR, role);
	}

}
