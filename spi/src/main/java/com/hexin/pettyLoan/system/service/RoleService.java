package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.RoleItem;

public interface RoleService {
	/**
	 * 添加一个角色
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public RoleItem insertRole(RoleItem item) throws ErrorCodeException;
	/**
	 * 删除一个角色
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteRole(RoleItem item) throws ErrorCodeException;
	/**
	 * 更新一个角色
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public RoleItem updateRole(RoleItem item) throws ErrorCodeException;
	/**
	 * 获取一个角色
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public RoleItem getOneRole(Integer id) throws ErrorCodeException;
	/**
	 * 获取符合条件的角色（可分页）
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<RoleItem> getRoleList(RoleItem params) throws ErrorCodeException;
	/**
	 * 获取符合条件的角色数量
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(RoleItem params) throws ErrorCodeException;
	/**
	 * 获取一个角色的功能
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<FunctionItem> queryRoleFunction(RoleItem params) throws ErrorCodeException;
	/**
	 * 获取一个角色的功能数量
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer countRoleFunction(RoleItem params) throws ErrorCodeException;
	
	/**
	 * 获取一个组织对应的角色，同时获取所有系统角色
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<RoleItem> getOrgnizationRole(RoleItem params) throws ErrorCodeException;
	
	/**
	 * 获取一个角色尚未添加的所有功能
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<FunctionItem> getFunctionNeeded(RoleItem params) throws ErrorCodeException;
	/**
	 * 获取一个角色尚未添加的所有功能的数量
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getFunctionNeededCount(RoleItem params) throws ErrorCodeException;
}
