/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.io.InputStream;
import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 电子档案附件model
 * @author wanglei2
 *
 */
public class ElectronicArchivesFileItem extends AbstractItem {

	/**
	 * 
	 */
	public static final String NAMESPACE = "arc_elec_file";
	
	private Integer acrId;//档案ID
	private Integer orgId;//组织ID
	private Short procNo;//处理序号
	private Short isValid;//有效标识
	private Short fileNo;//文件序号
	private Short operType;//文件追加/删除标识
	private String fileName;//文件名
	private InputStream fileStream;//文件内容
	private String fileId;//文件索引
	private Integer fileSize;//文件大小
	private Integer previewTimes;//预览次数
	private Integer downloadTimes;//下载次数
	private Integer creater;//建档者
	private Date ctime;//建档时间
	private Integer mender;//变更者
	private Date mtime;//变更时间

	public ElectronicArchivesFileItem() {
		
	}

	public Integer getAcrId() {
		return acrId;
	}

	public void setAcrId(Integer acrId) {
		this.acrId = acrId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Short getProcNo() {
		return procNo;
	}

	public void setProcNo(Short procNo) {
		this.procNo = procNo;
	}

	public Short getIsValid() {
		return isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

	public Short getFileNo() {
		return fileNo;
	}

	public void setFileNo(Short fileNo) {
		this.fileNo = fileNo;
	}

	public Short getOperType() {
		return operType;
	}

	public void setOperType(Short operType) {
		this.operType = operType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getPreviewTimes() {
		return previewTimes;
	}

	public void setPreviewTimes(Integer previewTimes) {
		this.previewTimes = previewTimes;
	}

	public Integer getDownloadTimes() {
		return downloadTimes;
	}

	public void setDownloadTimes(Integer downloadTimes) {
		this.downloadTimes = downloadTimes;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getMender() {
		return mender;
	}

	public void setMender(Integer mender) {
		this.mender = mender;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}


	
}
