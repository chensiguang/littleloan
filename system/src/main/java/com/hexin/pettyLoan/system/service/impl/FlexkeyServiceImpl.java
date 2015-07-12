package com.hexin.pettyLoan.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.CacheType;
import com.hexin.core.annotation.RedisRead;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.system.handler.CacheFlushHandler;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.hexin.pettyLoan.system.service.FlexkeyService;

@Service("flexkeyService")
public class FlexkeyServiceImpl implements FlexkeyService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	@Resource(name="cacheFlushHandler")
	CacheFlushHandler cacheFlushHandler;
	public void initMethod(){
		
		writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
		readDao = WebApplicationContextHolder.getBean("readDaoHelper");
	}
	@Override
//	@RedisRead(type=CacheType.Map, keyName="flexkey",fieldName="flexkey-<0>", valueType="com.hexin.pettyLoan.system.model.FlexkeyItem")
	public FlexkeyItem getOneFlexkey(Integer id) throws ErrorCodeException {
		FlexkeyItem params = new FlexkeyItem();
		params.setId(id);
		return readDao.getOne(FlexkeyItem.NAMESPACE, params);
	}

	@Override
	@AccessLog(description="query flexkey", operateType=OperateType.Query)
	public List<FlexkeyItem> getFlexkeyList(FlexkeyItem params) throws ErrorCodeException {
		return readDao.query(FlexkeyItem.NAMESPACE, params);
	}

	@Override
	@AccessLog(description="insert one flexkey", operateType=OperateType.Insert, add2DB=Add2DB.Yes)
	public FlexkeyItem insertFlexkey(FlexkeyItem item) throws ErrorCodeException {
		try{
			writeDao.insert(FlexkeyItem.NAMESPACE, item);//使用唯一键约束
			cacheFlushHandler.FlushFlexkey(item.getFlexkey());
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return item;
	}

	@Override
	@AccessLog(description="update one flexkey", operateType=OperateType.Update, add2DB=Add2DB.Yes)
	public FlexkeyItem updateFlexkey(FlexkeyItem item) throws ErrorCodeException {
		try{
			writeDao.update(FlexkeyItem.NAMESPACE, item);
			cacheFlushHandler.FlushFlexkey(item.getFlexkey());
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return item;
	}

	/**
	 * 值集删除为真删除,isDeleted无效
	 */
	@Override
	@AccessLog(description="delete one flexkey", operateType=OperateType.Delete, add2DB=Add2DB.Yes)
	public Boolean deleteFlexkey(FlexkeyItem item) throws ErrorCodeException {
		item = getOneFlexkey(item.getId());
		Integer retCount = writeDao.delete(FlexkeyItem.NAMESPACE, item);
		cacheFlushHandler.FlushFlexkey(item.getFlexkey());
		return retCount > 0;
	}
	@Override
	@AccessLog(description="getFlexkeyListPage", operateType=OperateType.Query, add2DB=Add2DB.No)
	public List<FlexkeyItem> getFlexkeyListPage(FlexkeyItem params) throws ErrorCodeException{
		return readDao.page(FlexkeyItem.NAMESPACE, params);
	}
	
	@Override
	public Integer getCount(FlexkeyItem params) throws ErrorCodeException{
		return readDao.count(FlexkeyItem.NAMESPACE, params);
	}
	
	@Override
	@AccessLog(description="getFlexkeyList", operateType=OperateType.Query, add2DB=Add2DB.No)
	@RedisRead(type=CacheType.Map, keyName="flexkey", fieldName="<0>")
	public List<FlexkeyItem> getFlexkeyList(String flexkey) throws ErrorCodeException{
		FlexkeyItem params = new FlexkeyItem();
		params.setFlexkey(flexkey);
		return getFlexkeyList(params);
	}

}
