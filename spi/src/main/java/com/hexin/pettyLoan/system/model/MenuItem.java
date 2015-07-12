package com.hexin.pettyLoan.system.model;

import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;
/**
 * 菜单 model
 */
public class MenuItem extends AbstractItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NAMESPACE = "sys_menu";
	public static final String SQLID_MENU_USER = "getMenuListByUser";
	public static final String SQLID_FUN_MENU = "queryFunctionByMenu";
	public static final String SQLID_FUN_USER = "queryFunctionByUser";
	public static final String SQLID_FUN_IN = "queryAllFunMenuIn";
	public static final String SQLID_FUN_OUT = "queryAllFunMenuOut";
	public static final String SQLID_FUN_INCOUNT = "queryAllFunMenuInCount";
	public static final String SQLID_FUN_OUTCOUNT = "queryAllFunMenuOutCount";
	public static final String SQLID_FUN_INSERT = "insertFunMenu";
	public static final String SQLID_FUN_DELETE = "deleteFunMenu";
	public static final String SQLID_FUN_UPDATE = "updateFunMenu";
	
	
	private Integer id;//id
	private String title;//菜单名称
	private String description;//说明
	private String icon;//图标
	private String module;
	private Integer order;//显示顺序
	private String url;//菜单url
	private String systemName; //系统名称
	//传入查询参数
	private Integer authorityUserId; //登录用户ID
	private String functionName;//传参用
	private Integer systemId; //系统ID
	
	private List<FunctionItem> functionList;
//	如果返回的json数据中不需要显示的字段，在字段前加上transient即可
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	
	
	public MenuItem(){
		
	}
	public MenuItem(Integer authorityUserId){
		this.authorityUserId = authorityUserId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public Integer getAuthorityUserId() {
		return authorityUserId;
	}
	public void setAuthorityUserId(Integer authorityUserId) {
		this.authorityUserId = authorityUserId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
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
	public List<FunctionItem> getFunctionList() {
		return functionList;
	}
	public void setFunctionList(List<FunctionItem> functionList) {
		this.functionList = functionList;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	
	
	
	
}
