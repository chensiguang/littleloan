package com.hexin.pettyLoan.system.model;


import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 系统角色实体
 * @author wuwei
 *
 */
public class RoleItem extends AbstractItem{
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_role";
	public static final String SQLID_OR = "getOrgnizationRole";
	public static final String SQLID_ROLEFUNCTION = "queryRoleFunction";//查询角色拥有的功能
	public static final String SQLID_ROLEFUNCTIONCOUNT = "countRoleFunction";
	public static final String SQLID_FUNCTIONNEEDED = "queryFunctionNeeded";//查询角色没有的功能
	public static final String SQLID_FUNCTIONNEEDEDCOUNT = "queryFunctionNeededCount";
	private Integer id;
	private String roleName;
	private String roleDescription;
	private Integer orgnizationId;
	private Integer isSystemRole;
	
	private Integer userRoleId;						//对应的用户角色关系id
	
	private String listType;				//请求方（服务公司or小贷公司）
	private String functionIds;			//角色需要添加的功能
	private String roleFunctionIds;		//角色拥有的功能
	private String orgnizationType;		//用于根据公司类型查询功能
	
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getRoleFunctionIds() {
		return roleFunctionIds;
	}
	public void setRoleFunctionIds(String roleFunctionIds) {
		this.roleFunctionIds = roleFunctionIds;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	private Integer rows;
	private Integer page;
	public String getFunctionIds() {
		return functionIds;
	}
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	private Integer start;
	
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getOrgnizationId() {
		return orgnizationId;
	}
	public void setOrgnizationId(Integer orgnizationId) {
		this.orgnizationId = orgnizationId;
	}
	public Integer getIsSystemRole() {
		return isSystemRole;
	}
	public void setIsSystemRole(Integer isSystemRole) {
		this.isSystemRole = isSystemRole;
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
	public String getOrgnizationType() {
		return orgnizationType;
	}
	public void setOrgnizationType(String orgnizationType) {
		this.orgnizationType = orgnizationType;
	}
	
	
}
