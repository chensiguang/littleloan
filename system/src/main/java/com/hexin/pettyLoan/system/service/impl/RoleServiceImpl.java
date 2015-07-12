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
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.OrgnizationItem;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.service.OrgnizationService;
import com.hexin.pettyLoan.system.service.RoleService;
import com.vteba.service.context.spring.ApplicationContextHolder;



@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	@Resource(name="orgnizationService")
	OrgnizationService orgnizationService;
	public void initMethod(){
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
		orgnizationService = WebApplicationContextHolder.getBean("orgnizationService");
	}

	@Override
	@AccessLog(description="insert one role",operateType=OperateType.Insert,add2DB=Add2DB.Yes)
	public RoleItem insertRole(RoleItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			writeDao.insert(RoleItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00004",ErrorCode.SYS00004,ex);
		}
		return item;
	}

	@Override
	@AccessLog(description="delete one role",operateType=OperateType.Delete,add2DB=Add2DB.Yes)
	public Boolean deleteRole(RoleItem item) throws ErrorCodeException {
			Integer retCount = (Integer)writeDao.delete(RoleItem.NAMESPACE, item);
		return retCount>0;
	}

	@Override
	@AccessLog(description="update one role",operateType=OperateType.Update,add2DB=Add2DB.Yes)
	public RoleItem updateRole(RoleItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			writeDao.update(RoleItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00004",ErrorCode.SYS00004,ex);
		}
		return item;
	}

	@Override
	public RoleItem getOneRole(Integer id) throws ErrorCodeException {
		// TODO Auto-generated method stub
		RoleItem params = new RoleItem();
		params.setId(id);
		return readDao.getOne(RoleItem.NAMESPACE, params);
	}

	@Override
	public List<RoleItem> getRoleList(RoleItem params) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(RoleItem.NAMESPACE, params);
	}

	@Override
	public Integer getCount(RoleItem params) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(RoleItem.NAMESPACE, params);
	}
	@Override
	public List<FunctionItem> queryRoleFunction(RoleItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(RoleItem.NAMESPACE,RoleItem.SQLID_ROLEFUNCTION, params);
	}
	@Override
	public Integer countRoleFunction(RoleItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(RoleItem.NAMESPACE,RoleItem.SQLID_ROLEFUNCTIONCOUNT,params);
	}
	
	@Override
	public List<RoleItem> getOrgnizationRole(RoleItem params) throws ErrorCodeException{
		return readDao.query(RoleItem.NAMESPACE, RoleItem.SQLID_OR, params);
	}

	@Override
	public List<FunctionItem> getFunctionNeeded(RoleItem params)
			throws ErrorCodeException {
		OrgnizationItem org = orgnizationService.getOneOrgnization(params.getOrgnizationId());
		params.setOrgnizationType(org.getOrgnizationType());
		return readDao.query(RoleItem.NAMESPACE,RoleItem.SQLID_FUNCTIONNEEDED, params);
	}

	@Override
	public Integer getFunctionNeededCount(RoleItem params)
			throws ErrorCodeException {
		OrgnizationItem org = orgnizationService.getOneOrgnization(params.getOrgnizationId());
		params.setOrgnizationType(org.getOrgnizationType());
		return readDao.count(RoleItem.NAMESPACE,RoleItem.SQLID_FUNCTIONNEEDEDCOUNT, params);
	}
}
