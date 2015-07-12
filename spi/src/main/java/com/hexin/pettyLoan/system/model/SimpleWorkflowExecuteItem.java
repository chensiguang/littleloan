package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class SimpleWorkflowExecuteItem extends AbstractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_simple_workflow_execute";
	private Integer id;
	private Integer configId;
	private String description;
	private String dealUser;
	private String status;
	private String invokeClass;
	private String invokeMethod;
	private String invokeParams;
	private Integer approveUserId;
	private String invokeParamTypes;
	private String approveDescription;
	
	private String workflowName;
	private String workflowDescription;
	
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	
	public SimpleWorkflowExecuteItem(){
		
	}
	
	public SimpleWorkflowExecuteItem(Integer id){
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDealUser() {
		return dealUser;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getInvokeClass() {
		return invokeClass;
	}
	public void setInvokeClass(String invokeClass) {
		this.invokeClass = invokeClass;
	}
	public String getInvokeMethod() {
		return invokeMethod;
	}
	public void setInvokeMethod(String invokeMethod) {
		this.invokeMethod = invokeMethod;
	}
	public String getInvokeParams() {
		return invokeParams;
	}
	public void setInvokeParams(String invokeParams) {
		this.invokeParams = invokeParams;
	}
	public Integer getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(Integer approveUserId) {
		this.approveUserId = approveUserId;
	}

	public String getInvokeParamTypes() {
		return invokeParamTypes;
	}

	public void setInvokeParamTypes(String invokeParamTypes) {
		this.invokeParamTypes = invokeParamTypes;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowDescription() {
		return workflowDescription;
	}

	public void setWorkflowDescription(String workflowDescription) {
		this.workflowDescription = workflowDescription;
	}

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

	public String getApproveDescription() {
		return approveDescription;
	}

	public void setApproveDescription(String approveDescription) {
		this.approveDescription = approveDescription;
	}
	
	
}
