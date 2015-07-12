package com.hexin.pettyLoan.crm.model;


/**
 * 企业担保人对象
 * @author chenyongzhi
 *
 */
public class EnterpriseGuarantorItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624117044835462867L;
	
	public static final String NAMESPACE= "crm_enterprise_guarantor";
	
	/**
	 * 担保人姓名
	 */
	private String username;
	
	/**
	 * 客户类型
	 */
	private String userType;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
