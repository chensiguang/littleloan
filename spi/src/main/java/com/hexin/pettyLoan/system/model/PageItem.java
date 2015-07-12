package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;
/**
 * 页面选项配置
 *
 */
public class PageItem extends AbstractItem{

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_page_item";
	public static final String SQLID_ALL = "getPageListAll";
	public static final String SQLID_LIST_USER = "getPageListByUser";
	public static final String SQLID_ALL_USER = "getPageListAllByUser";
	
	private Integer id;//id
	private String type; //类型
	private Integer functionId;//功能ID 
	private String functionName;//功能名称
	private String itemName;//选项名称
	private String itemDescription;//选项描述
	private Boolean checked;
	
	private String pageitemValue;
	private Integer userId; //登录用户Id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPageitemValue() {
		return pageitemValue;
	}

	public void setPageitemValue(String pageitemValue) {
		this.pageitemValue = pageitemValue;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
	
}
