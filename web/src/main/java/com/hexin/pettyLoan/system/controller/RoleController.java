package com.hexin.pettyLoan.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.service.RoleService;

@Controller
public class RoleController {
	//private final static Log logger = LogFactory.getLog(RoleServiceImpl.class);
	@Resource(name="roleService")
	private RoleService roleService;
	
	@RequestMapping("/role/page.do")
	public @ResponseBody String page(RoleItem params,String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<RoleItem> list = roleService.getRoleList(params);
		Integer count = roleService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/role/list.do")
	public @ResponseBody String list(RoleItem params,String callback){
		JsonResult result;
		List<RoleItem> list = roleService.getRoleList(params);
		result = new JsonResult(1, null, list);
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/role/update.do")
	public @ResponseBody String update(RoleItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"修改成功",roleService.updateRole(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/role/insert.do")
	public @ResponseBody String insert(RoleItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"创建成功",roleService.insertRole(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/role/delete.do")
	public @ResponseBody String delete(RoleItem item,String callback){
		JsonResult result;
		try{
			result = new JsonResult(1,"删除成功",roleService.deleteRole(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/role/getone.do")
	public @ResponseBody String getOne(RoleItem item,String callback){
		JsonResult result = new JsonResult(1,null,roleService.getOneRole(item.getId()));
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/role/getRoleFunctionPage.do")
	public @ResponseBody String getRoleFunction(RoleItem params,String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<FunctionItem> list = roleService.queryRoleFunction(params);
		Integer count = roleService.countRoleFunction(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/role/getOrgnizationRole.do")
	public @ResponseBody String getOrgnizationRole(RoleItem params,String callback){
		JsonResult result;
		List<RoleItem> list = roleService.getOrgnizationRole(params);
		result = new JsonResult(1, null, list);
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/role/getFunctionNeeded.do")
	public @ResponseBody String getFunctionNeeded(RoleItem params,String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<FunctionItem> list = roleService.getFunctionNeeded(params);
		Integer count = roleService.getFunctionNeededCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
