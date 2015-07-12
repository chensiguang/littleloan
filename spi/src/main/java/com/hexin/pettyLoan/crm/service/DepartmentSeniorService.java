package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.DepartmentSeniorItem;


public interface DepartmentSeniorService {
    /**
     * 插入机构高管
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public DepartmentSeniorItem insertDepartmentSenior(DepartmentSeniorItem item) throws ErrorCodeException;
    public DepartmentSeniorItem updateDepartmentSenior(DepartmentSeniorItem item) throws ErrorCodeException;
    public DepartmentSeniorItem getOneDepartmentSenior(Integer id) throws ErrorCodeException;
    public List<DepartmentSeniorItem> getDepartmentSeniorList(DepartmentSeniorItem params) throws ErrorCodeException;
    public Boolean deleteDepartmentSenior(DepartmentSeniorItem item) throws ErrorCodeException;
    public List<DepartmentSeniorItem> getDepartmentSeniorListPage(DepartmentSeniorItem params) throws ErrorCodeException;
    public Integer getCount(DepartmentSeniorItem params) throws ErrorCodeException;
}
