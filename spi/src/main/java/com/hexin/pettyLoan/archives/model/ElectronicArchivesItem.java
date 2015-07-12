/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import com.hexin.pettyLoan.common.model.AbstractItem;
import java.util.List;
import java.util.Date;
/**
 * 电子档案基本信息model
 * @author wanglei
 *
 */
public class ElectronicArchivesItem extends AbstractItem {

	/**
	 * 
	 */
	public static final String NAMESPACE = "arc_electronic";
	
	private Integer arcId;//档案ID
	private Integer orgId;//组织ID
	private Short procNo;//处理序号
	private Short isValid;//有效标识
	private String arcName;//档案名称
	private String arcNo;//档案编号
	private Short arcCategoryId;//档案类别ID
	private String arcCategoryName;//档案类别名称
	private Short arcStatusCode;//档案状态代码
	private String arcStatusName;//档案状态名称
	private Integer customerId;//所属客户ID
	private String customerName;//所属客户姓名
	private String contractNo;//关联合同
	private String keyWords;//关键字
	private List<ElectronicArchivesFileItem> files;//附件信息
	private Short srcTerminalCode;//档案来源（终端）代码
	private String srcTerminalName;//档案来源（终端）名称
	private Short srcSystemCode;//档案来源（系统）代码
	private String srcSystemName;//档案来源（系统）名称
	private String arcNameEnti;//实体档案名称
	private String arcNoEnti;//实体档案编号
	private String arcLocationEnti;//实体档案位置
	private Integer creater;//建档者
	private Date ctime;//建档时间
	private Integer mender;//变更者
	private Date mtime;//变更时间
	private Integer approver;//审核者
	private Date atime;//审核时间
	
	public ElectronicArchivesItem() {
		
	}

	public Integer getArcId() {
		return arcId;
	}

	public void setArcId(Integer arcId) {
		this.arcId = arcId;
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

	public Short getArcStatusCode() {
		return arcStatusCode;
	}

	public void setArcStatusCode(Short arcStatusCode) {
		this.arcStatusCode = arcStatusCode;
	}

	public String getArcStatusName() {
		return arcStatusName;
	}

	public void setArcStatusName(String arcStatusName) {
		this.arcStatusName = arcStatusName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public List<ElectronicArchivesFileItem> getFiles() {
		return files;
	}

	public void setFiles(List<ElectronicArchivesFileItem> files) {
		this.files = files;
	}

	public Short getSrcTerminalCode() {
		return srcTerminalCode;
	}

	public void setSrcTerminalCode(Short srcTerminalCode) {
		this.srcTerminalCode = srcTerminalCode;
	}

	public String getSrcTerminalName() {
		return srcTerminalName;
	}

	public void setSrcTerminalName(String srcTerminalName) {
		this.srcTerminalName = srcTerminalName;
	}

	public Short getSrcSystemCode() {
		return srcSystemCode;
	}

	public void setSrcSystemCode(Short srcSystemCode) {
		this.srcSystemCode = srcSystemCode;
	}

	public String getSrcSystemName() {
		return srcSystemName;
	}

	public void setSrcSystemName(String srcSystemName) {
		this.srcSystemName = srcSystemName;
	}

	public String getArcNameEnti() {
		return arcNameEnti;
	}

	public void setArcNameEnti(String arcNameEnti) {
		this.arcNameEnti = arcNameEnti;
	}

	public String getArcNoEnti() {
		return arcNoEnti;
	}

	public void setArcNoEnti(String arcNoEnti) {
		this.arcNoEnti = arcNoEnti;
	}

	public String getArcLocationEnti() {
		return arcLocationEnti;
	}

	public void setArcLocationEnti(String arcLocationEnti) {
		this.arcLocationEnti = arcLocationEnti;
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

	public Integer getApprover() {
		return approver;
	}

	public void setApprover(Integer approver) {
		this.approver = approver;
	}

	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}


	
}
