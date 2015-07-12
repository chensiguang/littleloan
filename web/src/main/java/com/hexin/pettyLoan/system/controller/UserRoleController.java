package com.hexin.pettyLoan.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.model.UserRoleItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.UserRoleService;

@Controller
public class UserRoleController {
	//private static final Log logger = LogFactory.getLog(UserRoleServiceImpl.class);
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	
	@RequestMapping("/userRole/getUserRolePage.do")
	public @ResponseBody String getUserRolePage(UserinfoItem params,String callback){
		List<RoleItem> list = userRoleService.queryUserRolePage(params);
		JsonResult result = new JsonResult(1,null,list);
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/userRole/insert.do")
	public @ResponseBody String insert(UserinfoItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			String[] roleIds = item.getRoleIds().split(",");
			UserRoleItem ur = null;
			for(String roleId:roleIds){
				UserRoleItem userRoleItem = new UserRoleItem();
				userRoleItem.setCreater(item.getCreater());
				userRoleItem.setUserId(item.getId());
				userRoleItem.setRoleId(Integer.parseInt(roleId));
				ur = userRoleService.insertUserRole(userRoleItem);
			}
			if(ur==null){
				result = new JsonResult(-10,"角色分配进入审批环节！",roleIds);
			}
			else{
				result = new JsonResult(1,"添加成功",roleIds);
			}
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/userRole/delete.do")
	public @ResponseBody String delete(UserinfoItem item,String callback){
		JsonResult result;
		try{
			String[] userRoleIds = item.getUserRoleIds().split(",");
			for(String userRoleId:userRoleIds){
				UserRoleItem userRoleItem = new UserRoleItem();
				userRoleItem.setId(Integer.parseInt(userRoleId));
				userRoleService.deleteUserRole(userRoleItem);
			}
			result = new JsonResult(1,"删除成功",userRoleIds);
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
