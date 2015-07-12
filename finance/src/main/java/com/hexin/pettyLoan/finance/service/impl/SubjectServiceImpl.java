package com.hexin.pettyLoan.finance.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.finance.model.SubjectItem;
import com.hexin.pettyLoan.finance.service.SubjectService;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
	@Override
	public List<SubjectItem> getFirstLevel(SubjectItem params)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return null;
	}

}
