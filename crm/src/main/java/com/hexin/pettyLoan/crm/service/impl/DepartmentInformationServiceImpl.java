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
import com.hexin.pettyLoan.crm.model.DepartmentInformationItem;
import com.hexin.pettyLoan.crm.service.DepartmentInformationService;

@Service("departmentInformationService")
public class DepartmentInformationServiceImpl implements DepartmentInformationService {

    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertDepartmentInformation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public DepartmentInformationItem insertDepartmentInformation(DepartmentInformationItem item)
                                                                                                throws ErrorCodeException {
        writeDao.insert(DepartmentInformationItem.NAMESPACE, item);
        return item;
    }

    @Override
    public DepartmentInformationItem updateDepartmentInformation(DepartmentInformationItem item)
                                                                                                throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepartmentInformationItem getOneDepartmentInformation(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentInformationItem> getDepartmentInformationList(DepartmentInformationItem params)
                                                                                                         throws ErrorCodeException {
        return readDao.query(DepartmentInformationItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteDepartmentInformation(DepartmentInformationItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentInformationItem> getDepartmentInformationListPage(DepartmentInformationItem params)
                                                                                                             throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(DepartmentInformationItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public DepartmentInformationItem getOneDepartmentInformationByCustomerId(
			Integer customerId) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

}
