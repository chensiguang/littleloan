package com.hexin.pettyLoan.system.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.CacheType;
import com.hexin.core.annotation.RedisRead;
import com.hexin.core.annotation.RedisWrite;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.PageAuthorityItem;
import com.hexin.pettyLoan.system.model.PageItem;
import com.hexin.pettyLoan.system.service.PageService;

@Service("pageService")
public class PageServiceImpl implements PageService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	/**
	 * 根据 功能ID 取得该功能下的所有选项
	 */
	@Override
	public List<PageItem> getPageListAll(PageItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<PageItem> list =  readDao.query(PageItem.NAMESPACE,PageItem.SQLID_ALL, params);
		return list;
	}
	/**
	 * 根据 功能ID 用户ID 
	 * 取得该用户选中的选项
	 */
	@Override
	public List<PageItem> getPageListByUser(PageItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<PageItem> list =  readDao.query(PageItem.NAMESPACE,PageItem.SQLID_LIST_USER, params);
		return list;
	}
	/**
	 * 新增  / 修改 该用户的选项 = type = “字段”
	 * 修改 先删除该用户下的所有 然后再新增进去
	 * redis field 存放格式 pageItemField-用户ID-功能ID
	 */
	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@RedisWrite(type=CacheType.Map, keyName="pageItem",fieldName="pageItemField-<1>-<2>")
	public List<PageItem> insertPage(List<PageAuthorityItem> list,Integer authorityUserId,Integer functionId) throws ErrorCodeException {
		PageItem pitem = new PageItem();
		try{
			pitem.setUserId(authorityUserId);
			pitem.setFunctionId(functionId);
			writeDao.delete(PageItem.NAMESPACE, pitem);
			for(PageAuthorityItem pageAuthorityItem : list){
				writeDao.insert(PageItem.NAMESPACE, pageAuthorityItem);
			}
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
		return getPageListAllByUser(pitem);
	}
	@Override
	@RedisRead(type=CacheType.Map, keyName="pageItem",fieldName="pageItemField-<0:userId>-<0:functionId>", valueType="com.hexin.pettyLoan.system.model.PageItem")
	public List<PageItem> getPageListAllByUser(PageItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<PageItem> list =  readDao.query(PageItem.NAMESPACE,PageItem.SQLID_ALL_USER, params);
		return list;
	}
	@Override
	@RedisWrite(type=CacheType.Map, keyName="pageItem",fieldName="pageItemField-<0:userId>-<0:functionId>")
	public List<PageItem> updatePagePation(PageAuthorityItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		PageItem pitem = new PageItem();
		try{
			pitem.setUserId(item.getUserId());
			pitem.setFunctionId(item.getFunctionId());
			writeDao.update(PageItem.NAMESPACE, item);
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
		return getPageListAllByUser(pitem);
	}
	@Override
	@RedisWrite(type=CacheType.Map, keyName="pageItem",fieldName="pageItemField-<0:userId>-<0:functionId>")
	public List<PageItem> insertPagePation(PageAuthorityItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		PageItem pitem = new PageItem();
		try{
			pitem.setUserId(item.getUserId());
			pitem.setFunctionId(item.getFunctionId());
			writeDao.insert(PageItem.NAMESPACE, item);
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
		return getPageListAllByUser(pitem);
	}

}
