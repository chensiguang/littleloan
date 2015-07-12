package com.hexin.pettyLoan.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.system.model.RoleFunctionItem;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.service.RoleFunctionService;

@Controller
public class RoleFunctionController {
	//private static final Log logger = LogFactory.getLog(UserRoleServiceImpl.class);
	@Resource(name="roleFunctionService")
	private RoleFunctionService roleFunctionService;
	
	@RequestMapping("/roleFunction/list.do")
	public @ResponseBody String list(RoleFunctionItem params,String callback){
		List<RoleFunctionItem> list = roleFunctionService.queryRoleFunction(params);
		JsonResult result = new JsonResult(1,null,list);
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/roleFunction/insert.do")
	public @ResponseBody String insert(RoleItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			String[] functionIds = item.getFunctionIds().split(",");
			for(String functionId:functionIds){
				RoleFunctionItem roleFunctionItem = new RoleFunctionItem();
				roleFunctionItem.setCreater(item.getCreater());
				roleFunctionItem.setRoleId(item.getId());
				roleFunctionItem.setFunctionId(Integer.parseInt(functionId));
				roleFunctionService.insertRoleFunction(roleFunctionItem);
			}
			result = new JsonResult(1,"添加成功",functionIds);
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/roleFunction/delete.do")
	public @ResponseBody String delete(RoleItem item,String callback){
		JsonResult result;
		try{
			String[] roleFunctionIds = item.getRoleFunctionIds().split(",");
			for(String roleFunctionId:roleFunctionIds){
				RoleFunctionItem roleFunctionItem = new RoleFunctionItem();
				roleFunctionItem.setId(Integer.parseInt(roleFunctionId));
				roleFunctionService.deleteRoleFunction(roleFunctionItem);
			}
			result = new JsonResult(1,"删除成功",roleFunctionIds);
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
