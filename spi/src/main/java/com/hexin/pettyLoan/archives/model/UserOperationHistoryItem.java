/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 用户操作记录model
 * @author wanglei2
 *
 */
public class UserOperationHistoryItem extends AbstractItem {

	/**
	 * 
	 */
	
	private Short isValid;//有效标识
	private String userName;//操作者姓名
	private Short operateType;//动作种类
	private String operateName;//动作名称
	private String arcName;//档案名称
	private String arcNo;//档案编号
	private Integer creater;//操作者
	private Date ctime;//操作时间
	private Integer mender;//变更者
	private Date mtime;//变更时间

	public UserOperationHistoryItem() {
		
	}

	public Short getIsValid() {
		return isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Short getOperateType() {
		return operateType;
	}

	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
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
