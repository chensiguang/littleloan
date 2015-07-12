package com.hexin.pettyLoan.crm.model;


/**
 * 企业被担保人（保证人）对象
 * @author chenyongzhi
 *
 */
public class EnterpriseSponsorItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624117044835462867L;
	
	public static final String NAMESPACE= "crm_enterprise_sponsor";

	/**
	 * 被担保人姓名
	 */
	private String username;
	
	/**
	 * 客户类型
	 */
	private String userType;
	
	/**
	 * 数据来源
	 */
	private String dataCome;

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

	public String getDataCome() {
		return dataCome;
	}

	public void setDataCome(String dataCome) {
		this.dataCome = dataCome;
	}
	
}
