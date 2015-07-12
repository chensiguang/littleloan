package com.hexin.pettyLoan.archives.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.constants.ArchivesConstants;
import com.hexin.pettyLoan.archives.model.ArchivesStatisticItem;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesFileItem;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesItem;
import com.hexin.pettyLoan.archives.service.ArchivesCommonService;
import com.hexin.pettyLoan.archives.service.ElectronicArchivesService;
import com.hexin.pettyLoan.common.ErrorCode;

/**
 * 电子档案相关业务
 * @author yinxiaolin
 *
 */
@Service("electronicArchivesService")
public class ElectronicArchivesServiceImpl implements ElectronicArchivesService {

	@Resource(name = "archivesCommonService")
	ArchivesCommonService archivesCommonService;
	
	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;
	
	/**
	 * 建档（保存）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public ElectronicArchivesItem addArchivesSave(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 档案ID不为空时，删除DB中该ID的数据
			// 档案ID为空时，现在DB中最大的档案ID+1作为本次的档案ID
			if(item.getArcId() != null) {

				// 删除该ID的档案基本信息
				writeDao.delete(ElectronicArchivesItem.NAMESPACE, "physicalDelete", item);
				
				// 删除该ID的档案附件信息
				writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "physicalDelete", item);
			} else {
				int maxArcId = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxArcId", item);
				item.setArcId(maxArcId + 1);
			}
			
			// 档案基本信息的处理
			// 处理序号
			item.setProcNo((short)1);
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：保存
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_SAVE);
			
			// 建档时间
			item.setCtime(new Date());
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return item;
	}

	/**
	 * 建档（归档申请）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public ElectronicArchivesItem addArchivesFiling(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 删除该ID的档案基本信息
			writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
			
			// 删除该ID的档案附件信息
			writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：申请中
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_REQUEST);
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return item;
	}

	/**
	 * 建档审核（同意）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public Boolean approveFilingArchives(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 删除该ID的档案基本信息
			writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
			
			// 删除该ID的档案附件信息
			writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 档案编号：生成
			item.setArcNo(generateArcNo());
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：归档
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_FILING);
			
			// 审核时间
			item.setAtime(new Date());
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return true;
	}

	/**
	 * 建档审核（拒绝）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public Boolean refuseFilingArchives(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 删除该ID的档案基本信息
			writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
			
			// 删除该ID的档案附件信息
			writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：拒绝归档
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_REFUSE);
			
			// 审核时间
			item.setAtime(new Date());
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return true;
	}

	/**
	 * 档案变更（保存）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public ElectronicArchivesItem modifyArchivesSave(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 如果当前这条数据的档案状态是“保存”，则物理删除之
			// 否则，逻辑删除
			if(item.getArcStatusCode() == ArchivesConstants.ARC_STATUS_SAVE) {
				
				// 删除该ID的档案基本信息
				writeDao.delete(ElectronicArchivesItem.NAMESPACE, "physicalDelete", item);
				
				// 删除该ID的档案附件信息
				writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "physicalDelete", item);
			} else {
				// 删除该ID的档案基本信息
				writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
				
				// 删除该ID的档案附件信息
				writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			}
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：保存
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_SAVE);
			
			// 变更时间
			item.setMtime(new Date());
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return item;
	}

	/**
	 * 档案变更（变更申请）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public ElectronicArchivesItem modifyArchivesFiling(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 删除该ID的档案基本信息
			writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
			
			// 删除该ID的档案附件信息
			writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：申请中
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_REQUEST);
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return item;
	}

	/**
	 * 档案变更审核（同意）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@Override
	public Boolean approveModifyArchives(ElectronicArchivesItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 删除该ID的档案基本信息
			writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
			
			// 删除该ID的档案附件信息
			writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 档案编号：生成
			item.setArcNo(generateArcNo());
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：归档
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_FILING);
			
			// 审核时间
			item.setAtime(new Date());
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return true;
	}

	/**
	 * 档案变更审核（拒绝）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean refuseModifyArchives(ElectronicArchivesItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			// 删除该ID的档案基本信息
			writeDao.delete(ElectronicArchivesItem.NAMESPACE, "logicalDelete", item);
			
			// 删除该ID的档案附件信息
			writeDao.delete(ElectronicArchivesFileItem.NAMESPACE, "logicalDelete", item);
			
			// 档案基本信息的处理
			// 处理序号：现在DB中本档案ID的最大的处理序号+1作为本次的处理序号
			int maxProcNo = readDao.count(ElectronicArchivesItem.NAMESPACE, "getMaxProcNo", item);
			item.setProcNo((short)(maxProcNo + 1));
			
			// 有效标识：有效
			item.setIsValid(ArchivesConstants.VALID);
			
			// 档案状态代码：拒绝归档
			item.setArcStatusCode(ArchivesConstants.ARC_STATUS_REFUSE);
			
			// 审核时间
			item.setAtime(new Date());
			
			// 向DB插入新的档案基本信息
			writeDao.insert(ElectronicArchivesItem.NAMESPACE, item);
			
			// 档案附件的处理
			for(ElectronicArchivesFileItem fileItem : item.getFiles()) {
				
				// 删除文件的话，不做任何处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_DEL) {
					continue;
				}
				
				// 添加文件的话，做文件上传处理
				if(fileItem.getOperType() == ArchivesConstants.FILE_ADD) {
					// TODO: 文件上传处理，返回文件索引
					String fileId = "";
					
					// 文件索引
					fileItem.setFileId(fileId);
				}
				
				// 档案ID
				fileItem.setAcrId(item.getArcId());
				
				// 组织ID
				fileItem.setOrgId(item.getOrgId());
				
				// 处理序号
				fileItem.setProcNo(item.getProcNo());
				
				// 有效标识
				fileItem.setIsValid(ArchivesConstants.VALID);
				
				// 建档者
				fileItem.setCreater(item.getCreater());
				
				// 建档时间
				fileItem.setCtime(item.getCtime());
				
				// 变更者
				fileItem.setMender(item.getMender());
				
				// 变更时间
				fileItem.setMtime(item.getMtime());
				
				// 向DB插入这条档案附件信息
				writeDao.insert(ElectronicArchivesFileItem.NAMESPACE, fileItem);
			}
			
		} catch (ErrorCodeException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return true;
	}
	
	@Override
	public List<ElectronicArchivesItem> queryArchives(
			ElectronicArchivesItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElectronicArchivesItem> getArchivesDetail(
			ElectronicArchivesItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElectronicArchivesFileItem updateFilePreviewInfo(
			ElectronicArchivesFileItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElectronicArchivesFileItem updateFileDownloadInfo(
			ElectronicArchivesFileItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArchivesStatisticItem countArchivesData(ArchivesStatisticItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 生成档案编号
	 * @return 
	 */
	private String generateArcNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
