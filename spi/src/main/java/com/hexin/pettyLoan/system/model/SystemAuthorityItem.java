package com.hexin.pettyLoan.system.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class SystemAuthorityItem extends AbstractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_system_authority";
	public static final String SQLID_SALBOI = "getSystemAuthorityListByOrgnizationId";
	private Integer id;
	private Integer orgnizationId;
	private Integer systemId;
	private Date validateDate;
	
	private String systemName;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrgnizationId() {
		return orgnizationId;
	}
	public void setOrgnizationId(Integer orgnizationId) {
		this.orgnizationId = orgnizationId;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public Date getValidateDate() {
		return validateDate;
	}
	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	
}
