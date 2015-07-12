package com.hexin.pettyLoan.portals.service;

import java.util.List;
import java.util.Map;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.ContentItem;

/**
 * 内容事务接口
 * @author ache
 *
 */
public interface ContentService {
	/**
	 * 添加内容
	 * @param 内容
	 * @return
	 * @throws ErrorCodeException
	 */
	public ContentItem insertContent(ContentItem item) throws ErrorCodeException;
	/**
	 * 查询内容列表
	 * @param 内容
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<ContentItem> getContentList(ContentItem item) throws ErrorCodeException;
	/**
	 * 删除内容
	 * @param 内容
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteContent(ContentItem item) throws ErrorCodeException;
	/**
	 * 更新内容
	 * @param 内容
	 * @return
	 * @throws ErrorCodeException
	 */
	public ContentItem updateContent(ContentItem item)throws ErrorCodeException;
	/**
	 * 查询内容
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public ContentItem getContent(Integer id) throws ErrorCodeException;
	/**
	 * 查询个数
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(ContentItem item) throws ErrorCodeException;
	/**
	 * 提交内容审核
	 * @param 内容的id字符 用','作为分隔符
	 * @throws ErrorCodeException
	 */
	public void submiteContent(String ids) throws ErrorCodeException;
	/**
	 * 删除内容
	 * @param ids
	 * @throws ErrorCodeException
	 */
	public void deleteContents(String ids) throws ErrorCodeException;
	/**
	 * 按类型查询内容列表各num条
	 * @param num
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<Map<String, Object>> getPortalContent(Integer num) throws ErrorCodeException;
}
