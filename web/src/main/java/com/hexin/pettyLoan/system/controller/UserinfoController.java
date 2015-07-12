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
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.UserinfoService;

@Controller
public class UserinfoController {
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	@RequestMapping("/user/login.do")
	public @ResponseBody String login(String userName, String password, String callback){
		JsonResult result = null;
		try{
			UserinfoItem user = userinfoService.login(userName, password);
			if(user == null){
				result = new JsonResult(-1, "登录失败，用户名或密码不正确！", null);
			}
			else{
				result = new JsonResult(1,"登陆成功!", user);
			}
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/user/page.do")
	public @ResponseBody String page(UserinfoItem params, String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<UserinfoItem> list = userinfoService.getUserinfoListPage(params);
		Integer count = userinfoService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/user/insert.do")
	public @ResponseBody String insert(UserinfoItem user, Integer authorityUserId, String callback){
		JsonResult result = new JsonResult();
		try{
			user.setCreater(authorityUserId);
			user = userinfoService.insertUser(user);
			if(user==null){
				result = new JsonResult(-10, "创建用户进入审批环节！",null);
			}
			else{
				result = new JsonResult(1, null, user);
			}
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/user/update.do")
	public @ResponseBody String update(UserinfoItem user, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, userinfoService.updateUser(user));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/user/delete.do")
	public @ResponseBody String delete(UserinfoItem user, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, userinfoService.deleteUserinfo(user));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/user/list.do")
	public @ResponseBody String list(UserinfoItem params, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, userinfoService.getUserinfoList(params));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/user/getRoleNeeded.do")
	public @ResponseBody String getRoleNeeded(UserinfoItem params, String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<RoleItem> list = userinfoService.getRoleNeeded(params);
		Integer count = userinfoService.countRoleNeeded(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
