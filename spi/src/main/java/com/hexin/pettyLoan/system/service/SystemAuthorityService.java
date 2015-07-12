package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.SystemAuthorityItem;
import com.hexin.pettyLoan.system.model.SystemItem;

public interface SystemAuthorityService {
	/**
	 * 获取所有的系统信息
	 * @return
	 */
	public List<SystemItem> getAllSystem();
	/**
	 * 获取一个组织的准入规则配置
	 * @param orgnizationId
	 * @return
	 */
	public List<SystemAuthorityItem> getSystemAuthorityListByOrgnizationId(Integer orgnizationId);
	/**
	 * 保存一个组织准入规则信息，先根据systemId,orgnizationId获取一个SystemAuthorityItem，如果存在则更新， 不存在则新增
	 * @param item
	 * @return
	 */
	public SystemAuthorityItem saveSystemAuthorityItem(SystemAuthorityItem item) throws ErrorCodeException;
	
	/**
	 * 配置一个组织的准入规则
	 * @param list
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean setSystemAuthority(List<SystemAuthorityItem> list) throws ErrorCodeException;
	
	/**
	 * 获取一个用户对应的准入规则
	 * @param userId
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<SystemItem> getAuthoritySystemByUserId(Integer userId) throws ErrorCodeException;
}
