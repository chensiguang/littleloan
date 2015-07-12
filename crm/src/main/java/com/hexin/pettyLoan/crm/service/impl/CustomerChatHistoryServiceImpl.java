package com.hexin.pettyLoan.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerChatHistoryItem;
import com.hexin.pettyLoan.crm.service.CustomerChatHistoryService;
import com.vteba.service.context.spring.ApplicationContextHolder;
@Service("customerChatHistoryService")
public class CustomerChatHistoryServiceImpl implements
		CustomerChatHistoryService {

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;
	
	@Resource(name = "readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	public List<CustomerChatHistoryItem> getCustomerChatHistory(CustomerChatHistoryItem item) throws ErrorCodeException {
		return readDao.query(CustomerChatHistoryItem.NAMESPACE, item);
	}

	@Override
	public CustomerChatHistoryItem insertCustomerChatHistory(
			CustomerChatHistoryItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCustomerChatHistory(CustomerChatHistoryItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteCustomerChatHistory(Integer id)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean uploadFile(String fileName, String filePath)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerChatHistoryItem getOneCustomerChatHistory(Integer id)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

}
