package com.hexin.pettyLoan.portals.model;

import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 知识库model
 * @author chenyongzhi
 *
 */
public class KnowledgeItem extends AbstractItem {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8206132544399530697L;

	public static final String NAMESPACE = "ptl_knowledge";
	
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	public static final String SQLID_PAGE_APPROVE = "pageByApprove";
	public static final String SQLID_COUNT_APPROVE = "countByApprove";
	public static final String SQLID_UPDATE_APPROVE = "updateByApprove";
	//知识库类型值集表中的flexkey
	public static final String TYPE_FLEXKEY = "knowledgeType";
//	如果返回的json数据中不需要显示的字段，在字段前加上transient即可
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	private List<AttachmentItem> attachList;
	
	/**
	 * 编号（主键）
	 */
	private Integer id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 知识库类型
	 */
	private String type;
	
	/**
	 * 关键字
	 */
	private String keywords;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 关联附件编号
	 */
	private Integer file;
	
	/**
	 * 是否是全员可见
	 */
	private Short isall;
	
	/**
	 * 状态
	 */
	private Short status;
	
	/**
	 * 审核人编号
	 */
	private Integer approveUserId;
	
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFile() {
		return file;
	}

	public void setFile(Integer file) {
		this.file = file;
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

	public List<AttachmentItem> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachmentItem> attachList) {
		this.attachList = attachList;
	}
	
}
