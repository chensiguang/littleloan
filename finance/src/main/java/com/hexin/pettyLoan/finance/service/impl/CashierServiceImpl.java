package com.hexin.pettyLoan.finance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.finance.model.BankAccountInfo;
import com.hexin.pettyLoan.finance.service.CashierService;
import com.hexin.pettyLoan.portals.model.ContentItem;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("cashierService")
public class CashierServiceImpl implements CashierService{
	
	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	public List<BankAccountInfo> getBankAccountInfoList(BankAccountInfo params)
			throws ErrorCodeException {
		try {
			return readDao.query(BankAccountInfo.NAMESPACE, params);
		} catch (Exception e) {
			throw new ErrorCodeException("FA01001", ErrorCode.FA01001, e);
		}
	}

	@Override
	public BankAccountInfo insertBankAccount(BankAccountInfo params) {
		try {
			writeDao.insert(BankAccountInfo.NAMESPACE, params);
		} catch (Exception e) {
			throw new ErrorCodeException("FA01002", ErrorCode.FA01002, e);
		}
		return params;
	}
}
