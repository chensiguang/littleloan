package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class SimpleWorkflowConfigItem extends AbstractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_simple_workflow_config";
	private Integer id;
	private Integer orgnizationId;
	private String workflowName;
	private String workflowDescription;
	private String result;
	private String params;
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
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getWorkflowDescription() {
		return workflowDescription;
	}
	public void setWorkflowDescription(String workflowDescription) {
		this.workflowDescription = workflowDescription;
	}
	
	
}
