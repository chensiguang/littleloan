package com.hexin.pettyLoan.archives.service;


import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.UserArchivesAuthorityItem;

public interface UserArchivesAuthorityService {
	/**
	 * 得到list
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<UserArchivesAuthorityItem> getUserArchivesAuthorityPage(UserArchivesAuthorityItem params) throws ErrorCodeException;
	/**
	 * 得到list上总数
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(UserArchivesAuthorityItem params) throws ErrorCodeException;
	
	public UserArchivesAuthorityItem getOneUserArchivesAuthority(UserArchivesAuthorityItem params) throws ErrorCodeException;
	
	public UserArchivesAuthorityItem insertUserArchivesAuthority(UserArchivesAuthorityItem params) throws ErrorCodeException;
	
	public UserArchivesAuthorityItem updateUserArchivesAuthority(UserArchivesAuthorityItem params) throws ErrorCodeException;
	
	public Boolean deleteUserArchivesAuthority(UserArchivesAuthorityItem params) throws ErrorCodeException;

}
