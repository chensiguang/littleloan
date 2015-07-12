package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.AttachmentItem;

/**
 * 附件接口
 * @author wangfan2
 *
 */
public interface AttachmentService {
	
	/**
	 * 按类型和类型id获取附件信息
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<AttachmentItem> getAttachmentList(AttachmentItem params) throws ErrorCodeException;
	/**
	 * 根据ID获取一条附件信息
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public AttachmentItem getOneAttachment(Integer id) throws ErrorCodeException;
	/**
	 * 插入一条附件信息
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public AttachmentItem insertAttachment(AttachmentItem item) throws ErrorCodeException;
	/**
	 * 批量修改附件关联typeid
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean updateAttachments(String ids, Integer typeid) throws ErrorCodeException;
	/**
	 * 物理附件信息(包括磁盘文件)
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteAttachment(AttachmentItem item) throws ErrorCodeException;
	
}
