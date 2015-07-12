package com.hexin.pettyLoan.finance.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.finance.model.BankAccountInfo;

public interface CashierService {
	/**
	 * 查询银行账户
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	
	public List<BankAccountInfo> getBankAccountInfoList(BankAccountInfo params)
			throws ErrorCodeException;

	/**
	 * 添加银行账户
	 * @param params
	 * @return
	 */
	public BankAccountInfo insertBankAccount(BankAccountInfo params);
}
