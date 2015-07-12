package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.SurveyQuestionItem;

public interface SurveyQuestionService {

	/**
	 * 插入一条问卷问题
	 * @param item
	 * @param options 问题选项
	 * @return SurveyQuestionItem
	 * @throws ErrorCodeException
	 */
	public SurveyQuestionItem insertSurveyQuestion(SurveyQuestionItem item, String[] options) throws ErrorCodeException;
	
	/**
	 * 根据问题编号查询一条记录
	 * @param id
	 * @return
	 */
	public SurveyQuestionItem findById(Integer id) throws ErrorCodeException;
	
	/**
	 * 根据问题编号删除问题及问题选项
	 * @param id
	 * @return
	 */
	public Boolean deleteById(Integer id) throws ErrorCodeException;
	
	/**
	 * 更新问题对象
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean update(SurveyQuestionItem item) throws ErrorCodeException;;
	
	/**
	 * 更新问题描述
	 * @param item
	 * @param options 选项
	 * @return Boolean
	 */
	public Boolean updateForContent(SurveyQuestionItem item, String[] options) throws ErrorCodeException;
	
	/**
	 * 根据指定条件查询问卷问题列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<SurveyQuestionItem> find(SurveyQuestionItem item) throws ErrorCodeException;
	
	/**
	 * 查询所有问题列表
	 * @param item
	 * @return List<SurveyQuestionItem>
	 * @throws ErrorCodeException
	 */
	public List<SurveyQuestionItem> findForQuestion(SurveyQuestionItem item) throws ErrorCodeException;
	
	/**
	 * 查询所有问题记录数
	 * @param item
	 * @return List<SurveyQuestionItem>
	 * @throws ErrorCodeException
	 */
	public Integer getCountForQuestion(SurveyQuestionItem params) throws ErrorCodeException;
	
	/**
	 * 对两个问题的排序号进行互换
	 * @param id
	 * @param toId
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean changeSort(Integer id1, Integer id2) throws ErrorCodeException;
}
