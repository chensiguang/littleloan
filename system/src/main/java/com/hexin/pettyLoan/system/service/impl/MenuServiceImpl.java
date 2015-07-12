package com.hexin.pettyLoan.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.annotation.CacheType;
import com.hexin.core.annotation.RedisRead;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.MenuItem;
import com.hexin.pettyLoan.system.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	
	public void initMethod(){
		writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
		readDao = WebApplicationContextHolder.getBean("readDaoHelper");
	}
	@Override
	public List<MenuItem> getMenuList(Integer authorityUserId,Integer systemId)
			throws ErrorCodeException {
		MenuItem menuitem = new MenuItem();
		menuitem.setAuthorityUserId(authorityUserId);
		menuitem.setSystemId(systemId);
		return readDao.query(MenuItem.NAMESPACE,MenuItem.SQLID_MENU_USER,menuitem);
	}

	@Override
	public List<FunctionItem> queryFunctionByMenu(Integer authorityUserId,
			Integer systemId, Integer menuId) throws ErrorCodeException {
		MenuItem menuitem = new MenuItem();
		menuitem.setAuthorityUserId(authorityUserId);
		menuitem.setSystemId(systemId);
		menuitem.setId(menuId);
		return readDao.query(MenuItem.NAMESPACE,MenuItem.SQLID_FUN_MENU,menuitem);
	}

	@Override
	//由于开发需要，暂时把从redis读菜单去掉
//	@RedisRead(keyName="UserFunction", fieldName="<0>",type=CacheType.Map, valueType="com.hexin.pettyLoan.system.model.FunctionItem")
	public List<FunctionItem> queryFunctionByUser(Integer authorityUserId)
			throws ErrorCodeException {
		MenuItem menuitem = new MenuItem();
		menuitem.setAuthorityUserId(authorityUserId);
		return readDao.query(MenuItem.NAMESPACE,MenuItem.SQLID_FUN_USER,menuitem);
	}

	@Override
	public List<MenuItem> getAllMenuList(MenuItem item)
			throws ErrorCodeException {
		return  readDao.query(MenuItem.NAMESPACE,item);
	}
	@Override
	public MenuItem getOneMenuItem(MenuItem item) throws ErrorCodeException {
		return  readDao.getOne(MenuItem.NAMESPACE, item);
	}
	
	@Override
	public MenuItem insertMenu(MenuItem item) throws ErrorCodeException {
		try{
			writeDao.insert(MenuItem.NAMESPACE, item);//使用唯一键约束
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return item;
	}

	@Override
	public MenuItem updateMenu(MenuItem item) throws ErrorCodeException {
		try{
			writeDao.update(MenuItem.NAMESPACE, item);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return item;
	}

	@Override
	public Boolean deleteMenu(MenuItem item) throws ErrorCodeException {
		Integer retCount = writeDao.delete(MenuItem.NAMESPACE, item);
		return retCount > 0;
	}
	@Override
	public List<MenuItem> getAllMenuPage(MenuItem item)
			throws ErrorCodeException {
		return readDao.page(MenuItem.NAMESPACE, item);
	}
	@Override
	public Integer getCount(MenuItem item) throws ErrorCodeException {
		return readDao.count(MenuItem.NAMESPACE, item);
	}
	@Override
	public List<FunctionItem> queryAllFunMenu(MenuItem param, String type)
			throws ErrorCodeException {
		String queryId = MenuItem.SQLID_FUN_IN;
		if(type!=null&&"out".equals(type)){
			queryId = MenuItem.SQLID_FUN_OUT;
		}
		return readDao.query(MenuItem.NAMESPACE,queryId,param);
	}
	@Override
	public int queryAllFunMenuCount(MenuItem param, String type)
			throws ErrorCodeException {
		String queryId = MenuItem.SQLID_FUN_INCOUNT;
		if(type!=null&&"out".equals(type)){
			queryId = MenuItem.SQLID_FUN_OUTCOUNT;
		}
		return readDao.count(MenuItem.NAMESPACE,queryId,param);
	}
	
	@Override
	public Boolean insertFunMenu(FunctionItem item) throws ErrorCodeException {
		try{
			writeDao.insert(MenuItem.NAMESPACE,MenuItem.SQLID_FUN_INSERT,item);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return true;
	}
	@Override
	public Boolean deleteFunMenu(FunctionItem item) throws ErrorCodeException {
		try{
			writeDao.delete(MenuItem.NAMESPACE,MenuItem.SQLID_FUN_DELETE,item);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return true;
	}
	@Override
	public Boolean updateFunMenu(FunctionItem item) throws ErrorCodeException {
		try{
			writeDao.update(MenuItem.NAMESPACE,MenuItem.SQLID_FUN_UPDATE,item);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return true;
	}
	

	

}
