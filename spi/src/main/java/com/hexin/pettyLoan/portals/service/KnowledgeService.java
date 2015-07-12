package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.KnowledgeItem;

/**
 * 知识库
 * @author wangfan2
 *
 */
public interface KnowledgeService {
	
	/**
	 * 分页按条件获取知识库列表
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<KnowledgeItem> getKnowledgeListPage(KnowledgeItem params) throws ErrorCodeException;
	/**
	 * 分页按条件获取待审核的知识库列表
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<KnowledgeItem> getApproveKnowledgeList(KnowledgeItem params) throws ErrorCodeException;
	/**
	 * 根据ID获取一条知识库信息
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public KnowledgeItem getOneKnowledge(Integer id) throws ErrorCodeException;
	/**
	 * 分页关键字查询知识库列表
	 * @param keywords
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<KnowledgeItem> getKnowledgeListPage(String keywords) throws ErrorCodeException;
	/**
	 * 插入一条知识库
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public KnowledgeItem insertKnowledge(KnowledgeItem item) throws ErrorCodeException;
	/**
	 * 更新知识库
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public KnowledgeItem updateKnowledge(KnowledgeItem item) throws ErrorCodeException;
	/**
	 * 删除知识库(逻辑删除)
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteKnowledge(KnowledgeItem item) throws ErrorCodeException;
	/**
	 * 审核知识库
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public KnowledgeItem approveKnowledge(KnowledgeItem item) throws ErrorCodeException;
	/**
	 * 按条件获取知识库条数
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(KnowledgeItem params) throws ErrorCodeException;
	/**
	 * 按条件获取待审核的知识库条数
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getApproveCount(KnowledgeItem params) throws ErrorCodeException;

}
