/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 实体档案基本信息model
 * @author wanglei2
 *
 */
public class EntityArchivesItem extends AbstractItem {

	/**
	 * 
	 */
	public static final String NAMESPACE ="arc_entity";
	private String arcName;//档案名称
	private String arcNo;//档案编号
	private Short isValid;//有效标识
	private Short arcCategoryId;//档案类别ID
	private String arcCategoryName;//档案类别名称
	private String arcLocation;//档案位置
	private Short lendStatusCode;//借阅状态代码
	private String lendStatusName;//借阅状态名称
	private String arcNoElec;//电子档案编号
	private Integer creater;//录入者
	private Date ctime;//录入时间
	private Integer mender;//变更者
	private Date mtime;//变更时间
	private Integer orgId;
	
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	
	public EntityArchivesItem() {
		
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

	public Short getIsValid() {
		return isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
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

	public String getArcLocation() {
		return arcLocation;
	}

	public void setArcLocation(String arcLocation) {
		this.arcLocation = arcLocation;
	}

	public Short getLendStatusCode() {
		return lendStatusCode;
	}

	public void setLendStatusCode(Short lendStatusCode) {
		this.lendStatusCode = lendStatusCode;
	}

	public String getLendStatusName() {
		return lendStatusName;
	}

	public void setLendStatusName(String lendStatusName) {
		this.lendStatusName = lendStatusName;
	}

	public String getArcNoElec() {
		return arcNoElec;
	}

	public void setArcNoElec(String arcNoElec) {
		this.arcNoElec = arcNoElec;
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

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}



}
