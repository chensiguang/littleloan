package com.hexin.pettyLoan.portals.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 问卷model
 * @author chenyonghzi
 *
 */
public class SurveyItem extends AbstractItem {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -584819343782458929L;
	
	public static final String NAMESPACE = "ptl_survey";
	
	/**
	 * 编号（主键）
	 */
	private Integer id;
	
	/**
	 * 问卷名称
	 */
	private String name;
	
	/**
	 * 问卷描述
	 */
	private String description;
	
	/**
	 * 是否全员
	 */
	private Short isall;
	
	/**
	 * 问卷状态(0:新建、1:退回、2:待审批、3:审批通过)
	 */
	private Short status;
	
	/**
	 * 审批人编号
	 */
	private Integer approveuserid;
	
	//不需要在返回的json数据中显示的字段，在前面加transient
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getIsall() {
		return isall;
	}

	public void setIsall(Short isall) {
		this.isall = isall;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	
	public Integer getApproveuserid() {
		return approveuserid;
	}

	public void setApproveuserid(Integer approveuserid) {
		this.approveuserid = approveuserid;
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
	
}
