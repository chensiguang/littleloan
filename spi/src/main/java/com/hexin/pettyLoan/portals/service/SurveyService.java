package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.SurveyItem;

/**
 * 问卷接口
 * @author chenyongzhi
 *
 */
public interface SurveyService {

	
	/**
	 * 插入一条问卷
	 * @param item
	 * @return SurveyItem
	 * @throws ErrorCodeException
	 */
	public SurveyItem insertSurvey(SurveyItem item) throws ErrorCodeException;
	
	/**
	 * 根据ID查询问卷
	 * @param id
	 * @return SurveyItem
	 * @throws ErrorCodeException
	 */
	public SurveyItem findSurveyById(Integer id) throws ErrorCodeException;
	
	/**
	 * 更新问卷
	 * @param item
	 * @return Boolean
	 * @throws ErrorCodeException
	 */
	public Boolean updateSurvey(SurveyItem item) throws ErrorCodeException;
	
	/**
	 * 删除问卷（标记删除）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteSurvey(SurveyItem item) throws ErrorCodeException;
	
	/**
	 * 更新问卷状态
	 * @param id
	 * @param status
	 * @return Boolean
	 * @throws ErrorCodeException
	 */
	public Boolean updateSurveyStatus(Integer id, Short status, Integer userId) throws ErrorCodeException;
	
	/**
	 * 根据指定条件查询问卷
	 * @param params
	 * @return List<SurveyItem>
	 * @throws ErrorCodeException
	 */
	public List<SurveyItem> getSurveyList(SurveyItem params) throws ErrorCodeException;
	
	/**
	 * 根据查询审核或待审核问卷记录数
	 * @param params
	 * @return
	 * @throws ErrorCodeExceptio
	 */
	public Integer getCheckStatusCount(SurveyItem params) throws ErrorCodeException;
	
	/**
	 * 根据查询审核或待审核问卷列表
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<SurveyItem> getSurveyListByCheckStatus(SurveyItem params) throws ErrorCodeException;
	
	/**
	 * 根据指定条件查询问卷记录数
	 * @param params
	 * @return Integer
	 * @throws ErrorCodeException
	 */
	public Integer getCount(SurveyItem params) throws ErrorCodeException;
}
