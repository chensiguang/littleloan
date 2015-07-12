package com.hexin.pettyLoan.crm.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class CustomerChatHistoryItem extends AbstractItem {

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "crm_customer_chat_history";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	
	private transient Integer rows;// 每页显示的行数,pageSize
	private transient Integer page;// 当前页,pageIndex
	private transient Integer start;// 行值，表示从哪一行开始
	
	private Integer id;// 主键
	private Integer customerId;// 客户编号
	private Integer organizationId;// 组织编号
	private Integer managerId;// 客户经理编号
	private Date chatTime;// 时间
	private String chatAddress;// 地点
	private String dataComeFrom;// 数据来源
	private String outline;// 概要
	private String details;// 详细信息
	private String fileName;// 附件名称
	private String filePath;// 附件路径
	
	private String customerName;// 客户名称
	private String customerType;// 客户类型
	private String managerName;// 客户经理
	
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
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Date getChatTime() {
		return chatTime;
	}
	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
	public String getChatAddress() {
		return chatAddress;
	}
	public void setChatAddress(String chatAddress) {
		this.chatAddress = chatAddress;
	}
	public String getDataComeFrom() {
		return dataComeFrom;
	}
	public void setDataComeFrom(String dataComeFrom) {
		this.dataComeFrom = dataComeFrom;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
