/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 实体档案催还记录model
 * @author wanglei2
 *
 */
public class EntityArchivesUrgeReturnItem extends AbstractItem {

	/**
	 * 
	 */
	
	private Short isValid;//有效标识
	private String arcNo;//档案编号
	private Short lendNo;//借阅序号
	private String urgeNotes;//备注
	private Integer creater;//催还者
	private Date ctime;//催还时间
	private Integer mender;//变更者
	private Date mtime;//变更时间
	
	public EntityArchivesUrgeReturnItem() {
		
	}

	public Short getIsValid() {
		return isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

	public String getArcNo() {
		return arcNo;
	}

	public void setArcNo(String arcNo) {
		this.arcNo = arcNo;
	}

	public Short getLendNo() {
		return lendNo;
	}

	public void setLendNo(Short lendNo) {
		this.lendNo = lendNo;
	}

	public String getUrgeNotes() {
		return urgeNotes;
	}

	public void setUrgeNotes(String urgeNotes) {
		this.urgeNotes = urgeNotes;
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
