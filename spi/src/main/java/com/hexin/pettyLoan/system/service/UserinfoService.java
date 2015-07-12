package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;

public interface UserinfoService {
	public UserinfoItem insertUser(UserinfoItem item) throws ErrorCodeException;
	public UserinfoItem updateUser(UserinfoItem item) throws ErrorCodeException;
	public UserinfoItem getOneUserinfo(Integer id) throws ErrorCodeException;
	public List<UserinfoItem> getUserinfoList(UserinfoItem params) throws ErrorCodeException;
	public Boolean deleteUserinfo(UserinfoItem item) throws ErrorCodeException;
	public List<UserinfoItem> getUserinfoListPage(UserinfoItem params) throws ErrorCodeException;
	public Integer getCount(UserinfoItem params) throws ErrorCodeException;
	/**
	 * 根据组织id和角色名称获取对应的用户列表<br>
	 * 在SimpleWorkflow中用到
	 * @param orgnizationId
	 * @param roleName
	 * @return
	 */
	public List<UserinfoItem> getUserinfoListByOrgnizationAndRole(Integer orgnizationId, String roleName);
	public UserinfoItem login(String userName, String password);
	/**
	 * 根据用户id查询到该用户没有的角色列表
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<RoleItem> getRoleNeeded(UserinfoItem params) throws ErrorCodeException;
	public Integer countRoleNeeded(UserinfoItem params) throws ErrorCodeException;
}
