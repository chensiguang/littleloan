package com.hexin.pettyLoan.archives.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.ArchivesCategoryItem;

public interface ArchivesCategoryService {
	/**
	 * 获得根节点
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<ArchivesCategoryItem> getRoot(ArchivesCategoryItem params) throws ErrorCodeException;
	/**
	 * 查询是否有子节点
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public boolean hasChildren(Integer id) throws ErrorCodeException;
	/**
	 * 递归获得所有根节点的子节点
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<ArchivesCategoryItem> getChildren(Integer id) throws ErrorCodeException;
	
	/**
	 * 插入根节点
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ArchivesCategoryItem insertArchivesCategory(ArchivesCategoryItem item ,Integer authorityUserId) throws ErrorCodeException;

	/**
	 * 更新节点
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ArchivesCategoryItem updateArchivesCategory(ArchivesCategoryItem item) throws ErrorCodeException;
	/**
	 * 删除节点
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteArchivesCategory(ArchivesCategoryItem item) throws ErrorCodeException;
}
