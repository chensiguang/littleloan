package com.hexin.pettyLoan.crm.model;


/**
 * 企业高管对象
 * @author chenyongzhi
 *
 */
public class EnterpriseTopManagerItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624117044835462867L;
	
	public static final String NAMESPACE= "crm_enterprise_top_manager";
	
	/**
	 * 高管姓名
	 */
	private String username;
	
	/**
	 * 客户类型
	 */
	private String managerType;
	
	/**
	 * 任职部门
	 */
	private String workDepartment;
	
	/**
	 * 职务
	 */
	private String duty;
	
	/**
	 * 年收入
	 */
	private Double yearIncome;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getManagerType() {
		return managerType;
	}

	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	public String getWorkDepartment() {
		return workDepartment;
	}

	public void setWorkDepartment(String workDepartment) {
		this.workDepartment = workDepartment;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Double getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(Double yearIncome) {
		this.yearIncome = yearIncome;
	}

}
