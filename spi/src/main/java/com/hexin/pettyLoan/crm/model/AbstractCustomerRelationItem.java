package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 公共的客户关系对象
 * @author 陈泳志
 *
 */
public abstract class AbstractCustomerRelationItem extends AbstractItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7299966700419469541L;
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 同事证件类型
	 */
    private String certificateType;
    
    /**
     * 同事证件号码
     */
    private String certificateNo;
	
	/**
	 * 联系电话
	 */
	private String telphone;
	
	/**
	 * 联系人手机号码
	 */
	private String mobile;
	
	/**
	 * 联系地址
	 */
	private String address;
	
	/**
	 * 是否是系统用户
	 */
	private Integer isSystemUser;
	
	/**
	 * 关联系统用户编号
	 */
	private Integer customerId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsSystemUser() {
		return isSystemUser;
	}

	public void setIsSystemUser(Integer isSystemUser) {
		this.isSystemUser = isSystemUser;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
}
