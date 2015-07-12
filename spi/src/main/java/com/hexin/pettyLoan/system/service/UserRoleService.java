package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.model.UserRoleItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;

public interface UserRoleService {
	/**
	 * 为一个用户配置一个角色
	 * @param item
	 * @return
	 */
	public UserRoleItem insertUserRole(UserRoleItem item);
	/**
	 * 去除一个用户的一个角色
	 * @param item
	 * @return
	 */
	public Boolean deleteUserRole(UserRoleItem item);
	/**
	 * 查询一个用户拥有的角色
	 * @param params
	 * @return
	 */
	public List<RoleItem> queryUserRolePage(UserinfoItem params);
	
	/**
	 * 查询一个角色对应的用户
	 * @param roleId
	 * @return
	 */
	public List<UserinfoItem> queryUserByRole(Integer roleId);
}
