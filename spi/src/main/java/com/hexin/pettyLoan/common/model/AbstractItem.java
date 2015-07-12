package com.hexin.pettyLoan.common.model;

import java.util.Date;

import com.hexin.core.base.IEntity;

public abstract class AbstractItem extends IEntity {
	private static final long serialVersionUID = 1L;
	private Date ctime;
	private Date mtime;
	private Date rtime;
	private Integer creater;
	private Short isvalid;
	private Integer authorityUserId;
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Date getRtime() {
		return rtime;
	}
	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}
	public Short getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}
	public Integer getAuthorityUserId() {
		return authorityUserId;
	}
	public void setAuthorityUserId(Integer authorityUserId) {
		this.authorityUserId = authorityUserId;
	}
	
	
}
