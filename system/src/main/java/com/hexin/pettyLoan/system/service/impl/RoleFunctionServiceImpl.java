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
import com.hexin.pettyLoan.system.handler.CacheFlushHandler;
import com.hexin.pettyLoan.system.model.RoleFunctionItem;
import com.hexin.pettyLoan.system.service.RoleFunctionService;



@Service("roleFunctionService")
public class RoleFunctionServiceImpl implements RoleFunctionService {
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
	@AccessLog(description="add one function to a role",operateType=OperateType.Insert,add2DB=Add2DB.Yes)
	public RoleFunctionItem insertRoleFunction(RoleFunctionItem item) {
		// TODO Auto-generated method stub
		try{
			writeDao.insert(RoleFunctionItem.NAMESPACE, item);
			cacheFlushHandler.FlushUserFunction(item.getRoleId());
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00007",ErrorCode.SYS00007,ex);
		}
		return item;
	}

	@Override
	@AccessLog(description="remove one function from a role",operateType=OperateType.Delete,add2DB=Add2DB.Yes)
	public Boolean deleteRoleFunction(RoleFunctionItem item) {
		item = readDao.getOne(RoleFunctionItem.NAMESPACE, item);
		Integer retCount = (Integer) writeDao.delete(RoleFunctionItem.NAMESPACE, item);
		cacheFlushHandler.FlushUserFunction(item.getRoleId());
		return retCount>0;
	}

	@Override
	public List<RoleFunctionItem> queryRoleFunction(RoleFunctionItem params) {
		// TODO Auto-generated method stub
		List<RoleFunctionItem> list = readDao.query(RoleFunctionItem.NAMESPACE,params);
		return list;
	}

	
	

}
