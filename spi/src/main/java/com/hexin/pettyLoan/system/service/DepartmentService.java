package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.DepartmentItem;
import com.hexin.pettyLoan.system.model.FlexkeyItem;

public interface DepartmentService { 
	/**
	 * 取得 部门列表 返回树形结构
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<DepartmentItem> getDeptList(DepartmentItem item) throws ErrorCodeException;
	/**
	 * 新增 部门
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public DepartmentItem insertDepartment(DepartmentItem item) throws ErrorCodeException;
	/**
	 *  修改 部门
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public DepartmentItem updateDepartment(DepartmentItem item) throws ErrorCodeException;
	/**
	 *  删除 部门
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteDepartment(DepartmentItem item) throws ErrorCodeException;

	/**
	 * 简单查询一个组织下面的部门
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<DepartmentItem> list(DepartmentItem item) throws ErrorCodeException;
}
