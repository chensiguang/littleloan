package com.hexin.pettyLoan.crm.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerManagerChangeHistoryItem;
import com.hexin.pettyLoan.crm.service.CustomerManagerChangeHistoryService;

@Service("customerManagerChangeHistoryService")
public class CustomerManagerChangeHistoryServiceImpl implements CustomerManagerChangeHistoryService {

	@Override
	public List<CustomerManagerChangeHistoryItem> getCustomerList(CustomerManagerChangeHistoryItem item) throws ErrorCodeException {
		return null;
		// TODO Auto-generated method stub 调用客户基本信息查询接口即可
		
	}

	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="update manager(s) of user", operateType=OperateType.Update, add2DB=Add2DB.Yes)
	public Boolean updateUserManager(Integer id) throws ErrorCodeException {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public List<CustomerManagerChangeHistoryItem> getChangeList(CustomerManagerChangeHistoryItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerManagerChangeHistoryItem getOne(CustomerManagerChangeHistoryItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

}
