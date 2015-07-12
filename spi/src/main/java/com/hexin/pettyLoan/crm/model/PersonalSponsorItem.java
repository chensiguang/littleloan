package com.hexin.pettyLoan.crm.model;

import java.util.Date;

/**
 * 个人被担保人（保证人）对象
 * @author chenyongzhi
 *
 */
public class PersonalSponsorItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624117044835462867L;
	
	public static final String NAMESPACE= "crm_personal_sponsor";
	
	/**
	 * 合同编号
	 */
	private String contractNumber;

	/**
	 * 被担保人姓名
	 */
	private String name;
	
	/**
	 * 客户类型
	 */
	private String userType;
	
	/**
	 * 数据来源
	 */
	private String dataCome;
	
	/**
	 * 开始日期
	 */
	private Date startDate;
	
	/**
	 * 到期日期
	 */
	private Date endDate;
	
	/**
	 * 合同金额
	 */
	private Double contractAmount;
	
	/**
	 * 贷款金额
	 */
	private Double loanAmount;

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
}
