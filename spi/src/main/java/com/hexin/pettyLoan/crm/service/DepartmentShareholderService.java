package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.DepartmentShareholderItem;


public interface DepartmentShareholderService {
    /**
     * 插入机构股东
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public DepartmentShareholderItem insertDepartmentShareholder(DepartmentShareholderItem item) throws ErrorCodeException;
    public DepartmentShareholderItem updateDepartmentShareholder(DepartmentShareholderItem item) throws ErrorCodeException;
    public DepartmentShareholderItem getOneDepartmentShareholder(Integer id) throws ErrorCodeException;
    public List<DepartmentShareholderItem> getDepartmentShareholderList(DepartmentShareholderItem params) throws ErrorCodeException;
    public Boolean deleteDepartmentShareholder(DepartmentShareholderItem item) throws ErrorCodeException;
    public List<DepartmentShareholderItem> getDepartmentShareholderListPage(DepartmentShareholderItem params) throws ErrorCodeException;
    public Integer getCount(DepartmentShareholderItem params) throws ErrorCodeException;
}
