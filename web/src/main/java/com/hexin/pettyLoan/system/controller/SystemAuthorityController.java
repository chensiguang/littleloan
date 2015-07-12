package com.hexin.pettyLoan.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.system.model.SimpleWorkflowConfigItem;
import com.hexin.pettyLoan.system.model.SystemAuthorityItem;
import com.hexin.pettyLoan.system.model.SystemItem;
import com.hexin.pettyLoan.system.service.SystemAuthorityService;

@Controller
public class SystemAuthorityController {
	@Resource(name="systemAuthorityService")
	SystemAuthorityService systemAuthorityService;
	
	@RequestMapping("/system/getAll.do")
	public @ResponseBody String getAllSystem(String callback){
		JsonResult result = null;
		try{
			List<SystemItem> list = systemAuthorityService.getAllSystem();
			result = new JsonResult(1, null, list);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/system/getSystemAuthorityListByOrgnizationId.do")
	public @ResponseBody String getSystemAuthorityListByOrgnizationId(Integer orgnizationId, String callback){
		JsonResult result = null;
		try{
			List<SystemAuthorityItem> list = systemAuthorityService.getSystemAuthorityListByOrgnizationId(orgnizationId);
			result = new JsonResult(1, null, list);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/system/setSystemAuthority.do")
	public @ResponseBody String setSystemAuthority(String configListJson, Integer authorityUserId, String callback){
		JsonResult result = null;
		try{
			List<SystemAuthorityItem> list = JSONUtil.parseArray(configListJson, SystemAuthorityItem.class);
			for(SystemAuthorityItem item : list){
				item.setCreater(authorityUserId);
			}
			systemAuthorityService.setSystemAuthority(list);
			result = new JsonResult(1, "保存成功！", null);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/system/getAuthoritySystemByUserId.do")
	public @ResponseBody String getAuthoritySystemByUserId(Integer userId, Integer authorityUserId, String callback){
		JsonResult result = null;
		try{
			result = new JsonResult(1, null, systemAuthorityService.getAuthoritySystemByUserId(userId));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
