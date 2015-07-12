package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.PageAuthorityItem;
import com.hexin.pettyLoan.system.model.PageItem;

public interface PageService {
	/**
	 * 取得该 功能下的所有 列名
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<PageItem> getPageListAll(PageItem params) throws ErrorCodeException;
	/**
	 * 根据 取得该 功能下的所有 列名 是否有选择的
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<PageItem> getPageListAllByUser(PageItem params) throws ErrorCodeException;
	/**
	 * 只返回 该功能下 用户选择中的列名
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<PageItem> getPageListByUser(PageItem params) throws ErrorCodeException;
	/**
	 * 配置 功能下的列名
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<PageItem> insertPage(List<PageAuthorityItem> list,Integer authorityUserId,Integer functionId) throws ErrorCodeException;
	/**
	 * 修改 功能下的列名 分页
	 */
	public List<PageItem> updatePagePation(PageAuthorityItem item) throws ErrorCodeException;
	/**
	 * 修改 功能下的列名 分页
	 */
	public List<PageItem> insertPagePation(PageAuthorityItem item) throws ErrorCodeException;
}
