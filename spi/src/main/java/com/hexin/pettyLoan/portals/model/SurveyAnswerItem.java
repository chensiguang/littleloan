package com.hexin.pettyLoan.portals.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 问题答案model
 * @author chenyongzhi
 *
 */
public class SurveyAnswerItem extends AbstractItem {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5339002564953422174L;

	public static final String NAMESPACE = "ptl_survey_answer";
	
	/**
	 * 编号（主键）
	 */
	private Integer id;
	
	/**
	 * 问卷编号
	 */
	private Integer surveyId;
	
	/**
	 * 填写机构编号
	 */
	private Integer orgizationId;
	
	/**
	 * 填写用户编号
	 */
	private Integer  userId;
	
	/**
	 * 问题编号
	 */
	private Integer questionId;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 选项
	 */
	private Integer optionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getOrgizationId() {
		return orgizationId;
	}

	public void setOrgizationId(Integer orgizationId) {
		this.orgizationId = orgizationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	
}
