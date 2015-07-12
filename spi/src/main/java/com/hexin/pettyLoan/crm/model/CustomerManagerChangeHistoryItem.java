package com.hexin.pettyLoan.crm.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 客户经理变更
 * @author csg
 *
 */
public class CustomerManagerChangeHistoryItem extends AbstractItem {

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "crm_manager_change";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	
	private Integer rows;
	private Integer page;
	private Integer start;
	
	private Integer id;// 主键
	private Integer operateUserId;// 修改人
	private String operateUserName;// 修改人名称
	private Integer customerId;// 客户编号
	private Date operateDate;// 修改日期
	private String operateColumnName;// 修改字段
	private String oldValue;// 字段原值
	private String currentValue;// 字段新值
	
	private String customerName;// 客户名称
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Integer operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getOperateColumnName() {
		return operateColumnName;
	}
	public void setOperateColumnName(String operateColumnName) {
		this.operateColumnName = operateColumnName;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
}
