package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;
/**
 * 页面选项配置 用户配置表
 *
 */
public class PageAuthorityItem extends AbstractItem{

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_page_item_authority";
	
	private Integer id;//id
	private Integer pageItemId; //选项ID
	private Integer functionId;//功能ID 
	private Integer userId; //登录用户Id
	private String  pageitemValue;//选项值
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPageItemId() {
		return pageItemId;
	}
	public void setPageItemId(Integer pageItemId) {
		this.pageItemId = pageItemId;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
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

	
	
	
}
