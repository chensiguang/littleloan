package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class UserinfoItem extends AbstractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_userinfo";
	public static final String SQLID_ULBOR = "getUserinfoListByOrgnizationAndRole";//根据组织id和角色id获取用户信息
	public static final String SQLID_GETROLENEEDED = "getRoleNeeded";
	public static final String SQLID_COUNTROLENEEDED = "countRoleNeeded";
	private Integer id;
	private String username;
	private String password;
	private String realname;
	private String email;
	private String telephone;
	private Integer departmentId;
	private Integer orgnizationId;
	private String status;//正式，试用，离职
	private String spell;
	
	private String departmentName;
	private String orgnizationName;
	private String departmentIdPath;
	private String orgnizationIdPath;
	private String departmentNamePath;
	private String orgnizationNamePath;
	
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	private Integer parentDepartmentId;//用于查询一个部门及其子部门的人员
	private Integer parentOrgnizationId;//用于查询一个公司及其子公司的人员
	private Integer roleId;//用于根据角色查询用户
	private String roleIds;//用户添加的的角色
	private String userRoleIds;//用户拥有的的角色
	private String listType;		//请求方（服务公司or小贷公司）
	
	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(String userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public UserinfoItem(){
		
	}
	
	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public UserinfoItem(Integer id){
		this.id = id;
	}
	
	public String getDepartmentIdPath() {
		return departmentIdPath;
	}
	public void setDepartmentIdPath(String departmentIdPath) {
		this.departmentIdPath = departmentIdPath;
	}
	public String getOrgnizationIdPath() {
		return orgnizationIdPath;
	}
	public void setOrgnizationIdPath(String orgnizationIdPath) {
		this.orgnizationIdPath = orgnizationIdPath;
	}
	public String getDepartmentNamePath() {
		return departmentNamePath;
	}
	public void setDepartmentNamePath(String departmentNamePath) {
		this.departmentNamePath = departmentNamePath;
	}
	public String getOrgnizationNamePath() {
		return orgnizationNamePath;
	}
	public void setOrgnizationNamePath(String orgnizationNamePath) {
		this.orgnizationNamePath = orgnizationNamePath;
	}
	public Integer getParentDepartmentId() {
		return parentDepartmentId;
	}
	public void setParentDepartmentId(Integer parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}
	public Integer getParentOrgnizationId() {
		return parentOrgnizationId;
	}
	public void setParentOrgnizationId(Integer parentOrgnizationId) {
		this.parentOrgnizationId = parentOrgnizationId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getOrgnizationId() {
		return orgnizationId;
	}
	public void setOrgnizationId(Integer orgnizationId) {
		this.orgnizationId = orgnizationId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
