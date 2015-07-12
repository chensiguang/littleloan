package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 角色功能关系实体类
 * @author wuwei
 *
 */
public class RoleFunctionItem extends AbstractItem{
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_role_function";
	private Integer id;
	private Integer roleId;
	private Integer functionId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	
	
}
