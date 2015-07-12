package com.hexin.pettyLoan.finance.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.finance.model.DataDic;

public interface DataDicService {
	/**
	 * 查询数据字典
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	
	public List<DataDic> getDataDicList(DataDic params)
			throws ErrorCodeException;

}
