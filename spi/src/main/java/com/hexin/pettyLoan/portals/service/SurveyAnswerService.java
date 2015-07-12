package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.pettyLoan.portals.model.SurveyAnswerItem;

/**
 * 问题答案service
 * @author chenyongzhi
 *
 */
public interface SurveyAnswerService {

	/**
	 * 根据指定条件查询答案
	 * @param item
	 * @return List<SurveyAnswerItem>
	 */
	public List<SurveyAnswerItem> find(SurveyAnswerItem item);
	
	/**
	 * 删除答案
	 * @param paras
	 * @return Boolean
	 */
	public Boolean deleteById(SurveyAnswerItem paras);
}
