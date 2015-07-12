/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 数据统计结果model
 * @author wanglei2
 *
 */
public class ArchivesStatisticResultItem extends AbstractItem {

	/**
	 * 
	 */
	
	private Short arcCategoryId;//档案类别ID
	private String arcCategoryName;//档案类别名称
	private Short arcQuantity;//档案数量	
	private String arcName;//档案名称
	private String arcNo;//档案编号
	private String fileName;//文件名
	private Integer fileSize;//占用空间
	private Integer previewTimes;//预览次数
	private Integer downloadTimes;//下载次数

	
	public ArchivesStatisticResultItem() {
		
	}

	public Short getArcCategoryId() {
		return arcCategoryId;
	}

	public void setArcCategoryId(Short arcCategoryId) {
		this.arcCategoryId = arcCategoryId;
	}

	public String getArcCategoryName() {
		return arcCategoryName;
	}

	public void setArcCategoryName(String arcCategoryName) {
		this.arcCategoryName = arcCategoryName;
	}

	public Short getArcQuantity() {
		return arcQuantity;
	}

	public void setArcQuantity(Short arcQuantity) {
		this.arcQuantity = arcQuantity;
	}

	public String getArcName() {
		return arcName;
	}

	public void setArcName(String arcName) {
		this.arcName = arcName;
	}

	public String getArcNo() {
		return arcNo;
	}

	public void setArcNo(String arcNo) {
		this.arcNo = arcNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

}
