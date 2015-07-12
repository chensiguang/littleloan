package com.hexin.pettyLoan.archives.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.pettyLoan.archives.model.EntityArchivesItem;
import com.hexin.pettyLoan.archives.service.EntityArchivesService;
import com.hexin.pettyLoan.common.ErrorCode;

@Service("entityArchivesService")
public class EntityArchivesServiceImpl implements EntityArchivesService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod(){
		
		writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
		readDao = WebApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	@AccessLog(description="query entity archives", operateType=OperateType.Query)
	public List<EntityArchivesItem> queryArchives(EntityArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(EntityArchivesItem.NAMESPACE,item);
	}

	@Override
	@AccessLog(description="insert one entity archives", operateType=OperateType.Insert,add2DB=Add2DB.Yes)
	public EntityArchivesItem insertArchives(EntityArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			writeDao.insert(EntityArchivesItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("ARC00001",ErrorCode.ARC00001, ex);
		}
		
		return item;
	}

	@Override
	@AccessLog(description="update one entity archives", operateType=OperateType.Update, add2DB=Add2DB.Yes)
	public EntityArchivesItem updateArchives(EntityArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			writeDao.update(EntityArchivesItem.NAMESPACE, item);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return item;
	}

	@Override
	
	public EntityArchivesItem lendArchives(EntityArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityArchivesItem returnArchives(EntityArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityArchivesItem urgeReturnArchives(EntityArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@AccessLog(description="getEntityArchivesList", operateType=OperateType.Query, add2DB=Add2DB.No)
	public List<EntityArchivesItem> getEntityArchivesList(
			EntityArchivesItem params) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(EntityArchivesItem.NAMESPACE, params);
	}

	@Override
	public Integer getCount(EntityArchivesItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(EntityArchivesItem.NAMESPACE, params);
	}

}
