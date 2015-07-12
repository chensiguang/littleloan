package com.hexin.pettyLoan.portals.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.KnowledgeItem;
import com.hexin.pettyLoan.portals.service.KnowledgeService;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod(){
		
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}

	@Override
	public List<KnowledgeItem> getKnowledgeListPage(KnowledgeItem params)
			throws ErrorCodeException {
		return readDao.page(KnowledgeItem.NAMESPACE, params);
	}
	
	@Override
	public List<KnowledgeItem> getApproveKnowledgeList(KnowledgeItem params)
			throws ErrorCodeException {
		return readDao.query(KnowledgeItem.NAMESPACE, KnowledgeItem.SQLID_PAGE_APPROVE, params);
	}

	@Override
//	@RedisRead(type=CacheType.Map, keyName="knowledge",fieldName="knowledge-<0>", valueType="com.hexin.pettyLoan.portals.model.KnowledgeItem")
	public KnowledgeItem getOneKnowledge(Integer id) throws ErrorCodeException {
		KnowledgeItem params = new KnowledgeItem();
		params.setId(id);
		return readDao.getOne(KnowledgeItem.NAMESPACE, params);
	}

	@Override
	public List<KnowledgeItem> getKnowledgeListPage(String keywords)
			throws ErrorCodeException {
		KnowledgeItem params = new KnowledgeItem();
		params.setKeywords(keywords);
		return getKnowledgeListPage(params);
	}

	@Override
	@AccessLog(description="insert one knowledge", operateType=OperateType.Insert, add2DB=Add2DB.Yes)
//	@RedisWrite(type=CacheType.Map, keyName="knowledge",fieldName="knowledge-<result:id>")
	public KnowledgeItem insertKnowledge(KnowledgeItem item) throws ErrorCodeException {
		try{
			writeDao.insert(KnowledgeItem.NAMESPACE, item);//使用唯一键约束
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("PTL00401", ErrorCode.PTL00401, ex);
		}
		return item;
	}

	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="update one knowledge", operateType=OperateType.Update, add2DB=Add2DB.Yes)
//	@RedisWrite(type=CacheType.Map, keyName="knowledge",fieldName="knowledge-<result:id>")
	public KnowledgeItem updateKnowledge(KnowledgeItem item) throws ErrorCodeException {
		KnowledgeItem currentItem = readDao.getOne(KnowledgeItem.NAMESPACE, item);
		if(null == currentItem){
			throw new ErrorCodeException("PTL00402", ErrorCode.PTL00402);
		}else if(0 != currentItem.getStatus() && 1 != currentItem.getStatus()){
			throw new ErrorCodeException("PTL00403", ErrorCode.PTL00403);
		}
		writeDao.update(KnowledgeItem.NAMESPACE, item);
		return item;
	}
	
	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="delete one knowledge", operateType=OperateType.Delete, add2DB=Add2DB.Yes)
//	@RedisWrite(type=CacheType.Map, keyName="knowledge",fieldName="knowledge-<0:id>",remove=Remove.Yes)
	public Boolean deleteKnowledge(KnowledgeItem item) throws ErrorCodeException {
		KnowledgeItem currentItem = readDao.getOne(KnowledgeItem.NAMESPACE, item);
		if(null == currentItem){
			throw new ErrorCodeException("PTL00402", ErrorCode.PTL00402);
		}else if(0 != currentItem.getStatus() && 1 != currentItem.getStatus()){
			throw new ErrorCodeException("PTL00403", ErrorCode.PTL00403);
		}
		Integer retCount = writeDao.delete(KnowledgeItem.NAMESPACE, item);
		return retCount > 0;
	}
	
	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="approve one knowledge", operateType=OperateType.Update, add2DB=Add2DB.Yes)
//	@RedisWrite(type=CacheType.Map, keyName="knowledge",fieldName="knowledge-<result:id>")
	public KnowledgeItem approveKnowledge(KnowledgeItem item) throws ErrorCodeException {
		KnowledgeItem currentItem = readDao.getOne(KnowledgeItem.NAMESPACE, item);
		if(null == currentItem){
			throw new ErrorCodeException("PTL00402", ErrorCode.PTL00402);
		}else if(2 == item.getStatus() && 0 != currentItem.getStatus() && 1 != currentItem.getStatus()){//提交审核
			throw new ErrorCodeException("PTL00403", ErrorCode.PTL00403);
		}else if((1 == item.getStatus() || 3 == item.getStatus()) && 2 != currentItem.getStatus()){//审核
			throw new ErrorCodeException("PTL00403", ErrorCode.PTL00403);
		}
		writeDao.update(KnowledgeItem.NAMESPACE, KnowledgeItem.SQLID_UPDATE_APPROVE, item);
		return item;
	}

	@Override
	public Integer getCount(KnowledgeItem params) throws ErrorCodeException {
		return readDao.count(KnowledgeItem.NAMESPACE, params);
	}
	
	@Override
	public Integer getApproveCount(KnowledgeItem params) throws ErrorCodeException {
		return readDao.count(KnowledgeItem.NAMESPACE, KnowledgeItem.SQLID_COUNT_APPROVE, params);
	}
}
