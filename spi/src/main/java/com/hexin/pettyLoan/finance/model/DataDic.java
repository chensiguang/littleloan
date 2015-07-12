/**
 * 
 */
package com.hexin.pettyLoan.finance.model;

import java.sql.Timestamp;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 财务数据字典表
 * @author liaojifeng
 * @since 2015-7-8 下午7:09:54
 * 
 */
public class DataDic extends AbstractItem{
	
	 private static final long serialVersionUID = 1L;
	 public static final String NAMESPACE = "fa_data_dic";
	 private Integer id;
     private String paramname;
     private String paramkey;
     private Integer paramvalue;
     private String remark;
     private Integer orgid;
     private Integer creater;
     private Timestamp ctime;
     private Timestamp mtime;
     private Timestamp rtime;
     private Short isvalid;
     
	/**
	 * 
	 */
	public DataDic() {
	}
	/**
	 * @param id
	 * @param paramname
	 * @param paramkey
	 * @param paramvalue
	 * @param remark
	 * @param orgid
	 * @param creater
	 * @param ctime
	 * @param mtime
	 * @param rtime
	 * @param isvalid
	 */
	public DataDic(Integer id, String paramname, String paramkey,
			Integer paramvalue, String remark, Integer orgid, Integer creater,
			Timestamp ctime, Timestamp mtime, Timestamp rtime, Short isvalid) {
		super();
		this.id = id;
		this.paramname = paramname;
		this.paramkey = paramkey;
		this.paramvalue = paramvalue;
		this.remark = remark;
		this.orgid = orgid;
		this.creater = creater;
		this.ctime = ctime;
		this.mtime = mtime;
		this.rtime = rtime;
		this.isvalid = isvalid;
	}
	public Integer getId() {
		return id;
	}
	public String getParamname() {
		return paramname;
	}
	public String getParamkey() {
		return paramkey;
	}
	public Integer getParamvalue() {
		return paramvalue;
	}
	public String getRemark() {
		return remark;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public Integer getCreater() {
		return creater;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public Timestamp getMtime() {
		return mtime;
	}
	public Timestamp getRtime() {
		return rtime;
	}
	public Short getIsvalid() {
		return isvalid;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}
	public void setParamvalue(Integer paramvalue) {
		this.paramvalue = paramvalue;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}
	public void setRtime(Timestamp rtime) {
		this.rtime = rtime;
	}
	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}
}
