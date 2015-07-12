package com.hexin.pettyLoan.crm.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class PersonalFinanceItem extends AbstractItem{

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "crm_personal_finance";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	
	private transient Integer rows;// 每页显示的行数,pageSize
	private transient Integer page;// 当前页,pageIndex
	private transient Integer start;// 行值，表示从哪一行开始
	
	private Integer id;// 主键
	private Integer customerId;// 客户编号
	private String customerName;// 客户名称
	private String customerType;// 客户类型
	private String certificateType;// 证件类型
	private String certificateNo;// 证件号码
	private String fileName;// 文件名
	private String filePath;// 文件路径
	private String fileType;// 附件类型
	private String description;// 附件描述
	private Integer uploadUserId;// 上传者id
	private Date uploadTime;// 上传时间
	
	private String uploadUserName;// 上传者名称

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

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(Integer uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadUserName() {
		return uploadUserName;
	}

	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
}
