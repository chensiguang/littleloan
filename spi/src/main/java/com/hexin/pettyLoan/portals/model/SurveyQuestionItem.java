package com.hexin.pettyLoan.portals.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 问卷问题model
 * @author chenyongzhi
 *
 */
public class SurveyQuestionItem extends AbstractItem {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5339002564953422174L;

	public static final String NAMESPACE = "ptl_survey_question";
	
	/**
	 * 编号（主键）
	 */
	private Integer id;
	
	/**
	 * 问卷编号
	 */
	private Integer surveyid; 
	
	/**
	 * 问题编号（自关联字段）
	 */
	private Integer questionid;
	
	/**
	 * 问题类型(1:单选，2：多选，3：问题)
	 */
	private String type;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 排序字段(默认：用ID作为排序值)
	 */
	private Short sortno;
	
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

	
	public Integer getSurveyid() {
		return surveyid;
	}

	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}

	public Integer getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
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

	
	
	public Short getSortno() {
		return sortno;
	}

	public void setSortno(Short sortno) {
		this.sortno = sortno;
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
