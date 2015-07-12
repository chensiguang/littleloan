package com.hexin.pettyLoan.crm.model;


/**
 * 个人家庭成员
 * @author chenyongzhi
 *
 */
public class PersonalFamilyMemberItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7101363597840372155L;
	
	public static final String NAMESPACE= "crm_family_member";
	
	/**
	 * 家属姓名
	 */
	private String name;
	
	/**
	 * 称谓
	 */
	private String appellation;
	
	/**
	 * 工作单位
	 */
	private String workUnit;
	
	/**
	 * 工作部门
	 */
	private  String workDepartment;
	
	/**
	 * 职位
	 */
	private String duty;
	
	/**
	 * 年收入
	 */
	private Double yearIncome;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
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
