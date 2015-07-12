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
import com.hexin.pettyLoan.system.service.FunctionService;
import com.hexin.pettyLoan.system.service.OrgnizationService;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	@Resource(name="orgnizationService")
	OrgnizationService orgnizationService;
	public void initMethod(){
		writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
		readDao = WebApplicationContextHolder.getBean("readDaoHelper");
		orgnizationService = WebApplicationContextHolder.getBean("orgnizationService");
	}

	@Override
	public FunctionItem getOneFunction(Integer id) throws ErrorCodeException {
		// TODO Auto-generated method stub
		FunctionItem item = new FunctionItem();
		item.setId(id);
		return readDao.getOne(FunctionItem.NAMESPACE,item);
	}

	@Override
	public List<FunctionItem> getFunctionList(FunctionItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(FunctionItem.NAMESPACE, params);
	}

	
	
	@Override
	public FunctionItem updateFunction(FunctionItem item)
			throws ErrorCodeException {
		try{
			writeDao.update(FunctionItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00006",ErrorCode.SYS00006,ex);
		}
		// TODO Auto-generated method stub
		return item;
	}

	@Override
	public Integer getCount(FunctionItem params) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(FunctionItem.NAMESPACE, params);
	}

	@Override
	public FunctionItem insertFunction(FunctionItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			writeDao.insert(FunctionItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00006",ErrorCode.SYS00006,ex);
		}
		return item;
	}

	@Override
	public Boolean deleteFunction(FunctionItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		Integer retCount = (Integer) writeDao.delete(FunctionItem.NAMESPACE, item);
		return retCount>0;
	}
	
	@Override
	public List<FunctionItem> getOrgnizationFunction(Integer orgnizationId){
		OrgnizationItem org = orgnizationService.getOneOrgnization(orgnizationId);
		return readDao.query(FunctionItem.NAMESPACE, FunctionItem.SQLID_OF, org);
	}
	
}
