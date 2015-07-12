package com.hexin.pettyLoan.system.model;

import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class FunctionItem extends AbstractItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_function";
	public static final String SQLID_OF = "queryOrgnizationFunction";
	private Integer id;//id
	private String functionName;//功能名称
	private String functionDescription;//说明
	private String type;//功能类型
	private String url;
	private Integer systemId;//系统id
	private String module;//
	private String fgroup;
	private Integer order;
	private String title;//菜单功能展示名称
	private String icon;//菜单功能展示图标
	private Integer roleFunctionId;		//角色功能关系id
	private String systemName;				//所属系统名称
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public Integer getRoleFunctionId() {
		return roleFunctionId;
	}
	public void setRoleFunctionId(Integer roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}
	private transient Integer menuId;//系统id
	private transient List<Integer> ids;//id
	private Integer rows;
	private Integer page;
	private Integer start;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionDescription() {
		return functionDescription;
	}
	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getFgroup() {
		return fgroup;
	}
	public void setFgroup(String fgroup) {
		this.fgroup = fgroup;
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
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	
}
