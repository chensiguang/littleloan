package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.OrgnizationItem;

public interface OrgnizationService {
	/**
	 * 获取一个组织
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public OrgnizationItem getOneOrgnization(Integer id) throws ErrorCodeException;
	/**
	 * 获取符合条件的组织（可分页）
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<OrgnizationItem> getOrgnizationList(OrgnizationItem params) throws ErrorCodeException;
	/**
	 * 更新一个组织
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public OrgnizationItem updateOrgnization(OrgnizationItem item) throws ErrorCodeException;
	/**
	 * 获取符合条件的组织数量
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(OrgnizationItem item) throws ErrorCodeException;
	/**
	 * 获取一个组织的下一级组织
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<OrgnizationItem> getChildrenOrgnizations(Integer id) throws ErrorCodeException;
	/**
	 * 新增一个组织
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public OrgnizationItem insertOrgnization(OrgnizationItem item) throws ErrorCodeException;
	/**
	 * 删除一个组织
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteOrgnization(OrgnizationItem item) throws ErrorCodeException;
	/**
	 * 获取第一级组织
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<OrgnizationItem> getFirst(OrgnizationItem item) throws ErrorCodeException;
	/**
	 * 插入一个子组织
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public OrgnizationItem insertChildOrgnization(OrgnizationItem item) throws ErrorCodeException;
}
