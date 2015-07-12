/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 用户档案权限信息
 * @author wanglei2
 *
 */
public class UserArchivesAuthorityItem extends AbstractItem {

	/**
	 * 
	 */
	public static final String NAMESPACE="arc_authority";
	private Integer id;
	private Integer level;//级别
	private Integer type;//设定or解除
	private Integer userId;//用户ID
	private String userName;//用户姓名
	private Integer targetUserId;//对象用户ID
	private String targetUserName;//对象用户姓名
	private Short arcCategoryId;//档案类别ID
	private String arcCategoryName;//档案类别名称
	private String arcName;//档案名称
	private String arcNo;//档案编号
	private Integer orgId;
	private Integer mender;//修改者
	
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	

	public UserArchivesAuthorityItem() {
		
	}
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(Integer targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetUserName() {
		return targetUserName;
	}

	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

	public Integer getMender() {
		return mender;
	}

	public void setMender(Integer mender) {
		this.mender = mender;
	}



}
