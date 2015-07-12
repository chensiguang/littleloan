package com.hexin.pettyLoan.system.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.base.DaoHelper;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.cache.ShardedRedisUtil;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.MenuItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.FlexkeyService;
import com.hexin.pettyLoan.system.service.UserRoleService;

/**
 * 提供方法用于特殊情况的缓存刷新
 * @author Administrator
 *
 */
@Service("cacheFlushHandler")
public class CacheFlushHandler {
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	@Resource(name="shardedRedisUtil")
	ShardedRedisUtil redisUtil;
	
	@Resource(name="userRoleService")
	UserRoleService userRoleService;
	
	@Resource(name="flexkeyService")
	FlexkeyService flexkeyService;
	
	public void FlushUserFunction(Integer roleId){
		System.out.println("start flush UserFunction roleId = " + roleId);
		List<UserinfoItem> userList = userRoleService.queryUserByRole(roleId);
		for(UserinfoItem user : userList){
			List<FunctionItem> functionList = readDao.query(MenuItem.NAMESPACE,MenuItem.SQLID_FUN_USER,new MenuItem(user.getId()));
			String key = "UserFunction";
			String field = user.getId().toString();
			redisUtil.setMap(key, field, JSONUtil.toJsonString(functionList));
			System.out.println("flush UserFunction key="+key +" ; field="+field);
		}
		System.out.println("end flush UserFunction roleId = " + roleId);
	}
	
	public void FlushFlexkey(String flexkey){
		FlexkeyItem params = new FlexkeyItem();
		params.setFlexkey(flexkey);
		List<FlexkeyItem> itemList = readDao.query(FlexkeyItem.NAMESPACE, params);
		redisUtil.setMap("flexkey", flexkey, JSONUtil.toJsonString(itemList));
		System.out.println("flush flexkey key= flexkey ; field="+flexkey);
	}
}
