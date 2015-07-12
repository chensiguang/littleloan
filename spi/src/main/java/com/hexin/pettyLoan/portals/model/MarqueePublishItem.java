package com.hexin.pettyLoan.portals.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 跑马灯发布model
 * @author ache
 *
 */
public class MarqueePublishItem extends AbstractItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "ptl_marquee_publish";
	private Integer id;
	private String description;
	private String solution;
	private Integer approveUserId;
	private Integer status;
	private Date stime;
	//不需要在返回的json数据中显示的字段，在前面加transient
	private Integer rows;
	private Integer page;
	private Integer start;
	public MarqueePublishItem(Integer id) {
		// TODO Auto-generated constructor stub
		this.id =id ;
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
	public Integer getId() {
		return id;
	}
	public MarqueePublishItem() {
		super();
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public Integer getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(Integer approveUserId) {
		this.approveUserId = approveUserId;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	
}
