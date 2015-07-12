package com.hexin.pettyLoan.archives.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.UserArchivesAuthorityItem;
import com.hexin.pettyLoan.archives.service.UserArchivesAuthorityService;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.system.model.FlexkeyItem;

@Service("userArchivesAuthorityService")
public class UserArchivesAuthorityServiceImpl implements UserArchivesAuthorityService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	

	@Override
	@AccessLog(description="query userArchivesAuthority", operateType=OperateType.Query)
	public List<UserArchivesAuthorityItem> getUserArchivesAuthorityPage(
			UserArchivesAuthorityItem params) throws ErrorCodeException {
		return readDao.page(UserArchivesAuthorityItem.NAMESPACE, params);
	}

	@Override
	public Integer getCount(UserArchivesAuthorityItem params)
			throws ErrorCodeException {
		return readDao.count(UserArchivesAuthorityItem.NAMESPACE, params);
	}

	@Override
	public UserArchivesAuthorityItem getOneUserArchivesAuthority(UserArchivesAuthorityItem params)
			throws ErrorCodeException {
		return readDao.getOne(UserArchivesAuthorityItem.NAMESPACE, params);
	}

	@Override
	@AccessLog(description="insert a userArchivesAuthority",operateType=OperateType.Insert,add2DB=Add2DB.Yes)
	public UserArchivesAuthorityItem insertUserArchivesAuthority(
			UserArchivesAuthorityItem params) throws ErrorCodeException {
		try{
			writeDao.insert(UserArchivesAuthorityItem.NAMESPACE, params);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return params;
	}

	@Override
	@AccessLog(description="delete a userArchivesAuthority",operateType=OperateType.Delete,add2DB=Add2DB.Yes)
	public Boolean deleteUserArchivesAuthority(UserArchivesAuthorityItem params)
			throws ErrorCodeException {
		Integer retCount = (Integer) writeDao.delete(UserArchivesAuthorityItem.NAMESPACE, params);
		return retCount>0;
	}

	@Override
	@AccessLog(description="delete a userArchivesAuthority",operateType=OperateType.Update,add2DB=Add2DB.Yes)
	public UserArchivesAuthorityItem updateUserArchivesAuthority(
			UserArchivesAuthorityItem params) throws ErrorCodeException {
		writeDao.update(UserArchivesAuthorityItem.NAMESPACE, params);
		return params;
	}

}
