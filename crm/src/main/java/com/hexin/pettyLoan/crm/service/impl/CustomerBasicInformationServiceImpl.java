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
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.system.model.UserinfoItem;
@Service("customerBasicInformationService")
public class CustomerBasicInformationServiceImpl implements CustomerBasicInformationService {

    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }

    @Override
    @AccessLog(description = "insertCustomerBasicInformation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public CustomerBasicInformationItem insertCustomerBasicInformation(CustomerBasicInformationItem item) throws ErrorCodeException {
        CustomerBasicInformationItem params = new CustomerBasicInformationItem();
        params.setCustomerName(item.getCustomerName());
        CustomerBasicInformationItem result = readDao.getOne(CustomerBasicInformationItem.NAMESPACE, params);
        if (result != null) {
            throw new ErrorCodeException("CRM00001", ErrorCode.CRM00001);
        }
        writeDao.insert(CustomerBasicInformationItem.NAMESPACE, item);
        return item;
    }

    @Override
    public CustomerBasicInformationItem updateCustomerBasicInformation(CustomerBasicInformationItem item)
                                                                                                         throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerBasicInformationItem getOneCustomerBasicInformation(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerBasicInformationItem> getCustomerBasicInformationList(CustomerBasicInformationItem params)
                                                                                                                  throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteCustomerBasicInformation(CustomerBasicInformationItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerBasicInformationItem> getCustomerBasicInformationListPage(CustomerBasicInformationItem params)
                                                                                                                      throws ErrorCodeException {
        return readDao.page(CustomerBasicInformationItem.NAMESPACE, params);
    }

    @Override
    public Integer getCount(CustomerBasicInformationItem params) throws ErrorCodeException {
        return readDao.count(CustomerBasicInformationItem.NAMESPACE, params);
    }

	@Override
	public List<CustomerBasicInformationItem> getSameDepartmentByNameAndRegNum(
			String name, String registNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerBasicInformationItem isExistedPersonalCustomer(Integer organizationId,
			String certificateType, String certificateNO) {
		// TODO Auto-generated method stub
		return null;
	}

}
