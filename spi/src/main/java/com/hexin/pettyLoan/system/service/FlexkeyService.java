package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.FlexkeyItem;

public interface FlexkeyService {
	public FlexkeyItem getOneFlexkey(Integer id) throws ErrorCodeException;
	public List<FlexkeyItem> getFlexkeyList(FlexkeyItem params) throws ErrorCodeException;
	public List<FlexkeyItem> getFlexkeyList(String flexkey) throws ErrorCodeException;
	public FlexkeyItem insertFlexkey(FlexkeyItem item) throws ErrorCodeException;
	public FlexkeyItem updateFlexkey(FlexkeyItem item) throws ErrorCodeException;
	public Boolean deleteFlexkey(FlexkeyItem item) throws ErrorCodeException;
	public List<FlexkeyItem> getFlexkeyListPage(FlexkeyItem params) throws ErrorCodeException;
	public Integer getCount(FlexkeyItem params) throws ErrorCodeException;
}
