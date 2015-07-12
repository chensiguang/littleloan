package com.hexin.pettyLoan.archives.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.EntityArchivesItem;

/**
 * 实体档案
 * @author wanglei2
 *
 */
public interface EntityArchivesService {
	/**
	 * 获取实体档案列表
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<EntityArchivesItem>getEntityArchivesList(EntityArchivesItem params) throws ErrorCodeException;
	
	/**
	 * 获取符合条件的实体档案数量
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(EntityArchivesItem params) throws ErrorCodeException;
	/**
	 * 实体档案查询
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<EntityArchivesItem>queryArchives(EntityArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 实体档案录入
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public EntityArchivesItem insertArchives(EntityArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 实体档案变更
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public EntityArchivesItem updateArchives(EntityArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 实体档案借阅
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public EntityArchivesItem lendArchives(EntityArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 实体档案归还
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public EntityArchivesItem returnArchives(EntityArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 实体档案催还
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public EntityArchivesItem urgeReturnArchives(EntityArchivesItem item) throws ErrorCodeException;

}
