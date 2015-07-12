package com.hexin.pettyLoan.finance.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.finance.model.SubjectItem;


public interface SubjectService {
	/**
	 * 根据机构id查询需要显示的一级科目
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<SubjectItem> getFirstLevel(SubjectItem params) throws ErrorCodeException;
	
}
