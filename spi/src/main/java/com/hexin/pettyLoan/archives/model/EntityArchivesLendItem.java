/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 实体档案借阅信息model
 * @author wanglei2
 *
 */
public class EntityArchivesLendItem extends AbstractItem {

	/**
	 * 
	 */
	
	private String arcNo;//档案编号
	private Integer lendNo;//借阅序号
	private Short isValid;//有效标识
	private Integer borrowerId;//借阅者ID
	private String borrowerName;//借阅者姓名
	private String telNo;//借阅者联系方式
	private Date lendYmd;//借阅时间
	private Short renewFlag;//是否续借
	private Date returnYmdSche;//归还时间（预定）
	private Date returnYmdReal;//归还时间（实际）
	private Short urgeTimes;//催还次数
	private Integer creater;//录入者
	private Date ctime;//录入时间
	private Integer mender;//变更者
	private Date mtime;//变更时间

	public EntityArchivesLendItem() {
		
	}

	public String getArcNo() {
		return arcNo;
	}

	public void setArcNo(String arcNo) {
		this.arcNo = arcNo;
	}

	public Integer getLendNo() {
		return lendNo;
	}

	public void setLendNo(Integer lendNo) {
		this.lendNo = lendNo;
	}

	public Short getIsValid() {
		return isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Date getLendYmd() {
		return lendYmd;
	}

	public void setLendYmd(Date lendYmd) {
		this.lendYmd = lendYmd;
	}

	public Short getRenewFlag() {
		return renewFlag;
	}

	public void setRenewFlag(Short renewFlag) {
		this.renewFlag = renewFlag;
	}

	public Date getReturnYmdSche() {
		return returnYmdSche;
	}

	public void setReturnYmdSche(Date returnYmdSche) {
		this.returnYmdSche = returnYmdSche;
	}

	public Date getReturnYmdReal() {
		return returnYmdReal;
	}

	public void setReturnYmdReal(Date returnYmdReal) {
		this.returnYmdReal = returnYmdReal;
	}

	public Short getUrgeTimes() {
		return urgeTimes;
	}

	public void setUrgeTimes(Short urgeTimes) {
		this.urgeTimes = urgeTimes;
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
