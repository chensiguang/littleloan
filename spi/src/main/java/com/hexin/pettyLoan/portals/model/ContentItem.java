package com.hexin.pettyLoan.portals.model;

import java.util.Date;
import com.hexin.pettyLoan.common.model.AbstractItem;
/**
 * 内容model
 * @author ache
 *
 */
public class ContentItem extends AbstractItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "ptl_content";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	//内容类型值集表中的flexkey
	public static final String TYPE_FLEXKEY = "contentType";
	//主键
	private Integer id;
	//标题
	private String title;
	//内容类型
	private String type;
	//内容
	private String content;
	//附件
	private Integer file;
	//是否全员
	private Short isAll;
	//状态
	private Integer status;
	//审核人
	private Integer approveUserId;
	//提交时间
	private Date stime;
	//不需要在返回的json数据中显示的字段，在前面加transient
	
	private String statusArrayStr;
	private Integer[] statusArray;
	private Integer rows;
	private Integer page;
	private Integer start;
	public ContentItem(Integer id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public ContentItem() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(Integer approveUserId) {
		this.approveUserId = approveUserId;
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
	public Integer getFile() {
		return file;
	}
	public void setFile(Integer file) {
		this.file = file;
	}
	public Short getIsAll() {
		return isAll;
	}
	public void setIsAll(Short isAll) {
		this.isAll = isAll;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Integer[]  getStatusArray() {
		return statusArray;
	}

	public void setStatusArray(Integer[] statusArray) {
		this.statusArray = statusArray;
	}

	public String getStatusArrayStr() {
		return statusArrayStr;
	}

	public void setStatusArrayStr(String statusArrayStr) {
		this.statusArrayStr = statusArrayStr;
	}
	
	
	
}
