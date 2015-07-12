package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.MenuItem;
import com.hexin.pettyLoan.system.model.PageAuthorityItem;
import com.hexin.pettyLoan.system.model.PageItem;

public interface MenuService {
	/**
	 * 权限 功能 管理
	 */ 
	/**
	 * 根据 用户权限 ，系统ID，取得菜单列表
	 * @param authorityUserId
	 * @param systemId
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<MenuItem> getMenuList(Integer authorityUserId,Integer systemId) throws ErrorCodeException;
	/**
	 * 根据 用户权限 ，系统ID，菜单ID 取得菜单下的功能列表
	 * @param authorityUserId
	 * @param systemId
	 * @param menuId
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<FunctionItem> queryFunctionByMenu(Integer authorityUserId,Integer systemId,Integer menuId) throws ErrorCodeException;
	/**
	 * 根据登录用户 取得所有权限
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<FunctionItem> queryFunctionByUser(Integer authorityUserId) throws ErrorCodeException;
	/**
	 * 菜单分配功能
	 */
	/**
	 * 取得菜单下的功能(type="in" 已经分配，“out” 还未分配)
	 * @param param
	 * @param type
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<FunctionItem> queryAllFunMenu(MenuItem param,String type) throws ErrorCodeException;
	/**
	 * 取得菜单下的功能(type="in" 已经分配，“out” 还未分配)
	 * @param param
	 * @param type
	 * @return
	 * @throws ErrorCodeException
	 */
	public int queryAllFunMenuCount(MenuItem param,String type) throws ErrorCodeException;
	/**
	 * 新增配置 菜单项
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean insertFunMenu(FunctionItem item) throws ErrorCodeException;
	/**
	 * 修改 配置 菜单项
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean updateFunMenu(FunctionItem item) throws ErrorCodeException;
	/**
	 * 删除 配置 菜单项
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteFunMenu(FunctionItem item) throws ErrorCodeException;
	
	/**
	 * 菜单 管理功能
	 */
	/**
	 * 取得 所有菜单列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<MenuItem> getAllMenuList(MenuItem item) throws ErrorCodeException;
	/**
	 * 根据ID 取得一个菜单信息
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public MenuItem getOneMenuItem(MenuItem item) throws ErrorCodeException;
	/**
	 * 新增菜单列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public MenuItem insertMenu(MenuItem item) throws ErrorCodeException;
	/**
	 * 修改菜单列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public MenuItem updateMenu(MenuItem item) throws ErrorCodeException;
	/**
	 * 删除菜单列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteMenu(MenuItem item) throws ErrorCodeException;
	/**
	 * 分页 取得 所有菜单列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<MenuItem> getAllMenuPage(MenuItem item) throws ErrorCodeException;
	/**
	 * 分页 取得总数
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(MenuItem item) throws ErrorCodeException;
	
	
}
