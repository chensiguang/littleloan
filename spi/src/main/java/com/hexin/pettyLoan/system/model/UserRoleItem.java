package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 用户角色关系实体类
 * @author wuwei
 *
 */
public class UserRoleItem extends AbstractItem{
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_user_role";
	public static final String SQLID_UBR = "queryUserByRole";
	private Integer id;
	private Integer userId;
	private Integer roleId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
