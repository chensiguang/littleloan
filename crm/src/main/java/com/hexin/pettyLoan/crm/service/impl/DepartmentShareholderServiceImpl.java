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
import com.hexin.pettyLoan.crm.model.DepartmentSeniorItem;
import com.hexin.pettyLoan.crm.model.DepartmentShareholderItem;
import com.hexin.pettyLoan.crm.service.DepartmentShareholderService;

@Service("departmentShareholderService")
public class DepartmentShareholderServiceImpl implements DepartmentShareholderService {
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertDepartmentShareholder", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public DepartmentShareholderItem insertDepartmentShareholder(DepartmentShareholderItem item)
                                                                                                throws ErrorCodeException {
        writeDao.insert(DepartmentShareholderItem.NAMESPACE, item);
        return item;
    }

    @Override
    public DepartmentShareholderItem updateDepartmentShareholder(DepartmentShareholderItem item)
                                                                                                throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepartmentShareholderItem getOneDepartmentShareholder(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentShareholderItem> getDepartmentShareholderList(DepartmentShareholderItem params)
                                                                                                         throws ErrorCodeException {
        return readDao.query(DepartmentShareholderItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteDepartmentShareholder(DepartmentShareholderItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentShareholderItem> getDepartmentShareholderListPage(DepartmentShareholderItem params)
                                                                                                             throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(DepartmentShareholderItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

}
