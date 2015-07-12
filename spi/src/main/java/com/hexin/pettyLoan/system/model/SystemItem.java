package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class SystemItem extends AbstractItem {
	public static final String NAMESPACE = "sys_system";
	public static final String SQLID_ASBUI = "getAuthoritySystemByUserId";
	public static final String SQLID_FS = "getFunctioSystem";
	private Integer id;
	private String systemName;
	private String shortName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
}
