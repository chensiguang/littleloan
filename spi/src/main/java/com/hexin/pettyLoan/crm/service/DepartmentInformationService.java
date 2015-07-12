package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.DepartmentInformationItem;

public interface DepartmentInformationService {

    /**
     * 插入机构信息
     * 
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public DepartmentInformationItem insertDepartmentInformation(DepartmentInformationItem item)
                                                                                                throws ErrorCodeException;
    /**
     * 更新机构信息
     * 
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public DepartmentInformationItem updateDepartmentInformation(DepartmentInformationItem item)
                                                                                                throws ErrorCodeException;
    /**
     * 根据id获取机构信息
     * 
     * @param id
     * @return
     * @throws ErrorCodeException
     */
    public DepartmentInformationItem getOneDepartmentInformation(Integer id) throws ErrorCodeException;
    
    /**
     * 通过客户编号获取机构基本信息
     * @param customerId
     * @return
     * @throws ErrorCodeException
     */
    public DepartmentInformationItem getOneDepartmentInformationByCustomerId(Integer customerId) throws ErrorCodeException;

    /**
     * 获取多条机构信息
     * 
     * @param params
     * @return
     * @throws ErrorCodeException
     */
    public List<DepartmentInformationItem> getDepartmentInformationList(DepartmentInformationItem params)
                                                                                                         throws ErrorCodeException;

    public Boolean deleteDepartmentInformation(DepartmentInformationItem item) throws ErrorCodeException;

    /**
     * 分页查询机构信息
     * 
     * @param params
     * @return
     * @throws ErrorCodeException
     */
    public List<DepartmentInformationItem> getDepartmentInformationListPage(DepartmentInformationItem params)
                                                                                                             throws ErrorCodeException;

    public Integer getCount(DepartmentInformationItem params) throws ErrorCodeException;

}
