package com.hexin.pettyLoan.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.SystemAuthorityItem;
import com.hexin.pettyLoan.system.model.SystemItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.SystemAuthorityService;
import com.hexin.pettyLoan.system.service.UserinfoService;

@Service("systemAuthorityService")
public class SystemAuthorityServiceImpl implements SystemAuthorityService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	@Resource(name="userinfoService")
	UserinfoService userService;
	
	@Override
	public List<SystemItem> getAllSystem() {
		return readDao.query(SystemItem.NAMESPACE, null);
	}

	@Override
	public List<SystemAuthorityItem> getSystemAuthorityListByOrgnizationId(Integer orgnizationId) {
		SystemAuthorityItem params = new SystemAuthorityItem();
		params.setOrgnizationId(orgnizationId);
		return readDao.query(SystemAuthorityItem.NAMESPACE, SystemAuthorityItem.SQLID_SALBOI, params);
	}

	@Override
	public SystemAuthorityItem saveSystemAuthorityItem(SystemAuthorityItem item)
			throws ErrorCodeException {
		try{
			SystemAuthorityItem params = new SystemAuthorityItem();
			params.setOrgnizationId(item.getOrgnizationId());
			params.setSystemId(item.getSystemId());
			SystemAuthorityItem authority = readDao.getOne(SystemAuthorityItem.NAMESPACE, params);
			if(authority == null){
				writeDao.insert(SystemAuthorityItem.NAMESPACE, item);
			}
			else{
				item.setId(authority.getId());
				writeDao.update(SystemAuthorityItem.NAMESPACE, item);
			}
			return item;
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
	}

	@Override
	@Transactional(rollbackFor=ErrorCodeException.class)
	public Boolean setSystemAuthority(List<SystemAuthorityItem> list) throws ErrorCodeException {
		try{
			for(SystemAuthorityItem item : list){
				saveSystemAuthorityItem(item);
			}
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
		return true;
	}
	
	@Override
	public List<SystemItem> getAuthoritySystemByUserId(Integer userId) throws ErrorCodeException{
		UserinfoItem user = userService.getOneUserinfo(userId);
		if(user==null) return null;
		//先过滤准入规则
		SystemAuthorityItem params = new SystemAuthorityItem();
		params.setOrgnizationId(user.getOrgnizationId());	
		List<SystemItem> list = readDao.query(SystemAuthorityItem.NAMESPACE, SystemItem.SQLID_ASBUI, params);
		//再过滤用户权限
		List<SystemItem> list2 = readDao.query(SystemItem.NAMESPACE, SystemItem.SQLID_FS, user);
		
		List<SystemItem> itemList = new ArrayList<SystemItem>();
		for(SystemItem s1 : list){
			for(SystemItem s2 : list2){
				if(s1.getId().equals(s2.getId())){
					itemList.add(s1);
				}
			}
		}
		
		return itemList;
	}

}
