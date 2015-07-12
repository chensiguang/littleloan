package com.hexin.pettyLoan.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.pettyLoan.crm.model.CustomerHouseItem;
import com.hexin.pettyLoan.crm.model.CustomerJobItem;
import com.hexin.pettyLoan.crm.model.DepartmentSeniorItem;
import com.hexin.pettyLoan.crm.service.DepartmentSeniorService;

@Service("departmentSeniorService")
public class DepartmentSeniorServiceImpl implements DepartmentSeniorService {
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertDepartmentSenior", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public DepartmentSeniorItem insertDepartmentSenior(DepartmentSeniorItem item) throws ErrorCodeException {
        writeDao.insert(DepartmentSeniorItem.NAMESPACE, item);
        return item;
    }

    @Override
    public DepartmentSeniorItem updateDepartmentSenior(DepartmentSeniorItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepartmentSeniorItem getOneDepartmentSenior(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentSeniorItem> getDepartmentSeniorList(DepartmentSeniorItem params) throws ErrorCodeException {
        return readDao.query(DepartmentSeniorItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteDepartmentSenior(DepartmentSeniorItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentSeniorItem> getDepartmentSeniorListPage(DepartmentSeniorItem params)
                                                                                              throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(DepartmentSeniorItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

}
