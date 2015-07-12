package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.pettyLoan.system.model.RoleFunctionItem;

public interface RoleFunctionService {
	/**
	 * 为一个角色配置一个功能
	 * @param item
	 * @return
	 */
	public RoleFunctionItem insertRoleFunction(RoleFunctionItem item);
	/**
	 * 去除一个角色的一个功能
	 * @param item
	 * @return
	 */
	public Boolean deleteRoleFunction(RoleFunctionItem item);
	/**
	 * 查询一个角色拥有的功能
	 * @param params
	 * @return
	 */
	public List<RoleFunctionItem> queryRoleFunction(RoleFunctionItem params);
}
