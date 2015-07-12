package com.hexin.pettyLoan.system.service.impl;

import java.util.List;
import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.util.Chinese2PinyinUtil;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.system.model.OrgnizationItem;
import com.hexin.pettyLoan.system.service.OrgnizationService;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("orgnizationService")
public class OrgnizationServiceImpl implements OrgnizationService{
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	
	public void initMethod(){
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
	}
	@Override
	public OrgnizationItem getOneOrgnization(Integer id)
			throws ErrorCodeException {
		OrgnizationItem params = new OrgnizationItem();
		params.setId(id);
		return readDao.getOne(OrgnizationItem.NAMESPACE,params);
	}
	@Override
	public List<OrgnizationItem> getOrgnizationList(OrgnizationItem params) throws ErrorCodeException{
		return readDao.query(OrgnizationItem.NAMESPACE, params);
	}
	@Override
	public OrgnizationItem updateOrgnization(OrgnizationItem item) throws ErrorCodeException{
		try{
			item.setSpell(Chinese2PinyinUtil.getFirstSpell(item.getOrgnizationName()));
			//更新namepath字段
			writeDao.update(OrgnizationItem.NAMESPACE,OrgnizationItem.SQLID_UPDATENAMEPATH, item);
			//更新namepath以外的字段
			writeDao.update(OrgnizationItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00003", ErrorCode.SYS00003, ex);
		}
		 return item;
	}
	@Override
	public Integer getCount(OrgnizationItem item) throws ErrorCodeException{
		return readDao.count(OrgnizationItem.NAMESPACE,item);
	}
	@Override
	public List<OrgnizationItem> getChildrenOrgnizations(Integer id) throws ErrorCodeException{
		OrgnizationItem params = new OrgnizationItem();
		params.setId(id);
		return readDao.query(OrgnizationItem.NAMESPACE, OrgnizationItem.SQLID_GETCHILDREN, params);
	}
	@Override
	public List<OrgnizationItem> getFirst(OrgnizationItem item) throws ErrorCodeException{
		OrgnizationItem params = new OrgnizationItem();
		return readDao.query(OrgnizationItem.NAMESPACE,OrgnizationItem.SQLID_GETFIRST, params);
	}
	@Override
	public OrgnizationItem insertOrgnization(OrgnizationItem item) throws ErrorCodeException{
		try{
			item.setSpell(Chinese2PinyinUtil.getFirstSpell(item.getOrgnizationName()));
			writeDao.insert(OrgnizationItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00003", ErrorCode.SYS00003);
		}
		return item;
	}
	@Override
	public OrgnizationItem insertChildOrgnization(OrgnizationItem item) throws ErrorCodeException{
		try{
			item.setSpell(Chinese2PinyinUtil.getFirstSpell(item.getOrgnizationName()));
			writeDao.insert(OrgnizationItem.NAMESPACE,"insertChild",item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00003", ErrorCode.SYS00003);
		}
		return item;
	}
	@Override
	public Boolean deleteOrgnization(OrgnizationItem item)throws ErrorCodeException {
		Integer retCount = (Integer) writeDao.delete(OrgnizationItem.NAMESPACE, item);
		return retCount>0;
	}
	
}
