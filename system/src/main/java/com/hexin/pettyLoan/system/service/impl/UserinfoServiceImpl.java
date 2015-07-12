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
import com.hexin.core.util.Chinese2PinyinUtil;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.common.SimpleWorkflowConstants;
import com.hexin.pettyLoan.common.aop.NeedApprove;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.UserinfoService;

@Service("userinfoService")
public class UserinfoServiceImpl implements UserinfoService {

	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod(){
		writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
		readDao = WebApplicationContextHolder.getBean("readDaoHelper");
	}
	@Override
	@NeedApprove(workflowName=SimpleWorkflowConstants.ADD_USER, 
			description="新增用户<0:username>，真实姓名为<0:realname>，所处部门为<0:departmentName>")
	public UserinfoItem insertUser(UserinfoItem item) throws ErrorCodeException {
		UserinfoItem params = new UserinfoItem();
		params.setUsername(item.getUsername());
		UserinfoItem result = readDao.getOne(UserinfoItem.NAMESPACE, params);
		if(result!=null){
			throw new ErrorCodeException("SYS00008", ErrorCode.SYS00008);
		}
		item.setSpell(Chinese2PinyinUtil.getFirstSpell(item.getRealname()));
		writeDao.insert(UserinfoItem.NAMESPACE, item);
		return item;
	}

	@Override
	public UserinfoItem updateUser(UserinfoItem item) throws ErrorCodeException {
		item.setSpell(Chinese2PinyinUtil.getFirstSpell(item.getRealname()));
		writeDao.update(UserinfoItem.NAMESPACE, item);
		return item;
	}

	@Override
	public UserinfoItem getOneUserinfo(Integer id) throws ErrorCodeException {
		return readDao.getOne(UserinfoItem.NAMESPACE, new UserinfoItem(id));
	}

	@Override
	public List<UserinfoItem> getUserinfoList(UserinfoItem params)
			throws ErrorCodeException {
		return readDao.query(UserinfoItem.NAMESPACE, params);
	}

	@Override
	public Boolean deleteUserinfo(UserinfoItem item) throws ErrorCodeException {
		Object r = writeDao.delete(UserinfoItem.NAMESPACE, item);
		Integer c = Integer.parseInt(r.toString());
		return c > 0;
	}

	@Override
	public List<UserinfoItem> getUserinfoListPage(UserinfoItem params)
			throws ErrorCodeException {
		return readDao.page(UserinfoItem.NAMESPACE, params);
	}

	@Override
	public Integer getCount(UserinfoItem params) throws ErrorCodeException {
		return readDao.count(UserinfoItem.NAMESPACE, params);
	}
	
	@Override
	public List<UserinfoItem> getUserinfoListByOrgnizationAndRole(Integer orgnizationId, String roleId){
		UserinfoItem param = new UserinfoItem();
		param.setOrgnizationId(orgnizationId);
		param.setRoleId(Integer.parseInt(roleId));
		return readDao.query(UserinfoItem.NAMESPACE, UserinfoItem.SQLID_ULBOR, param);
	}
	
	@Override
	public UserinfoItem login(String userName, String password) throws ErrorCodeException{
		try{
			UserinfoItem params = new UserinfoItem();
			params.setUsername(userName);
			params.setPassword(password);
			return readDao.getOne(UserinfoItem.NAMESPACE, params);
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
	}
	@Override
	public List<RoleItem> getRoleNeeded(UserinfoItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(UserinfoItem.NAMESPACE,UserinfoItem.SQLID_GETROLENEEDED, params);
	}
	@Override
	public Integer countRoleNeeded(UserinfoItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(UserinfoItem.NAMESPACE,UserinfoItem.SQLID_COUNTROLENEEDED, params);
	}

}
