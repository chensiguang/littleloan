/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import java.util.Date;
import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 档案类别信息model
 * @author wanglei2
 *
 */
public class ArchivesCategoryItem extends AbstractItem {

	/**
	 * 
	 */
	public static final String NAMESPACE = "arc_category";
	private Integer id;//类别ID
	private String name;//类别名称
	private Short parentId;//父类别ID
	private String parentName;//父类别名称
	private String path;//类别路径
	private Short depth;//类别深度
	private String notes;//备注
	private Integer mender;//修改者
	private List<ArchivesCategoryItem> children;
	private Integer orgId;
	private String text;
	private String createrName;
	private String menderName;

	public ArchivesCategoryItem() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getParentId() {
		return parentId;
	}

	public void setParentId(Short parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Short getDepth() {
		return depth;
	}

	public void setDepth(Short depth) {
		this.depth = depth;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getMender() {
		return mender;
	}

	public void setMender(Integer mender) {
		this.mender = mender;
	}

	public List<ArchivesCategoryItem> getChildren() {
		return children;
	}

	public void setChildren(List<ArchivesCategoryItem> children) {
		this.children = children;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getMenderName() {
		return menderName;
	}

	public void setMenderName(String menderName) {
		this.menderName = menderName;
	}



}
