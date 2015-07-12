/**
 * 
 */
package com.hexin.pettyLoan.archives.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.ArchivesStatisticItem;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesFileItem;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesItem;

/**
 * 电子档案相关业务接口
 * @author yinxiaolin
 *
 */
public interface ElectronicArchivesService {

	/**
	 * 建档（保存）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesItem addArchivesSave(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 建档（归档申请）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesItem addArchivesFiling(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 建档审核（同意）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean approveFilingArchives(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 建档审核（拒绝）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean refuseFilingArchives(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 档案变更（保存）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesItem modifyArchivesSave(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 档案变更（变更申请）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesItem modifyArchivesFiling(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 档案变更审核（同意）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean approveModifyArchives(ElectronicArchivesItem item) throws ErrorCodeException;

	/**
	 * 档案变更审核（拒绝）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean refuseModifyArchives(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 档案查询
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<ElectronicArchivesItem> queryArchives(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 根据档案ID获取档案详情
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<ElectronicArchivesItem> getArchivesDetail(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 更新文件预览次数等信息
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesFileItem updateFilePreviewInfo(ElectronicArchivesFileItem item) throws ErrorCodeException;
	
	/**
	 * 更新文件下载次数等信息
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesFileItem updateFileDownloadInfo(ElectronicArchivesFileItem item) throws ErrorCodeException;
	
	/**
	 * 数据统计
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ArchivesStatisticItem countArchivesData(ArchivesStatisticItem item) throws ErrorCodeException;
	
}
