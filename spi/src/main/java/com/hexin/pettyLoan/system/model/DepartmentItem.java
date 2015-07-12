package com.hexin.pettyLoan.system.model;

import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;
/**
 * 部门 model
 * @author magang
 *
 */
public class DepartmentItem extends AbstractItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAMESPACE = "sys_department";
	public static final String SQLID_UPDATE_CHILD ="updateChilePath";
	private Integer id;//id
	private String departmentName;//部门名称
	private String departmentType;//部门类型
	private Integer pid;//父部门id
	private String pname;//父部门名称
	private Integer orgnizationId;//所属组织id
	private String idPath;//
	private String namePath;//
	private String text;//
	private boolean leaf; //是否是 叶子节点
	private List<DepartmentItem> children;//子节点
	
	private  Integer parentDepartmentId;	

	public Integer getParentDepartmentId() {
		return parentDepartmentId;
	}

	public void setParentDepartmentId(Integer parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public List<DepartmentItem> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentItem> children) {
		this.children = children;
	}

	public Integer getOrgnizationId() {
		return orgnizationId;
	}

	public void setOrgnizationId(Integer orgnizationId) {
		this.orgnizationId = orgnizationId;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getText() {
		return departmentName;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
