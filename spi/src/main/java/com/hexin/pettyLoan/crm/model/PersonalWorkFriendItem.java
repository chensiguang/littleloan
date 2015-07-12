package com.hexin.pettyLoan.crm.model;

/**
 * 个人同事关系
 * @author chenyongzhi
 *
 */
public class PersonalWorkFriendItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7101363597840372155L;
	
	public static final String NAMESPACE= "crm_work_friend";
	
	/**
	 * 同事姓名
	 */
	private String name;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
