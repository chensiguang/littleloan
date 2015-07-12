package com.hexin.pettyLoan.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.annotation.AccessLog.Type;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.hexin.pettyLoan.system.service.FlexkeyService;
import com.hexin.core.util.JSONUtil;

@Controller
public class FlexkeyController {
	@Resource(name="flexkeyService")
	private FlexkeyService flexkeyService;
	
	@RequestMapping("/flexkey/page.do")
	public @ResponseBody String page(FlexkeyItem params, String callback) {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<FlexkeyItem> list = flexkeyService.getFlexkeyListPage(params);
		Integer count = flexkeyService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/flexkey/list.do")
	@AccessLog(type=Type.Info, operateType=OperateType.Query, description="query flexkey list")
	public @ResponseBody String list(FlexkeyItem params, String callback) {
		List<FlexkeyItem> list = flexkeyService.getFlexkeyList(params);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping(value="/flexkey/insert.do")
	public @ResponseBody String insert(FlexkeyItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1, null, flexkeyService.insertFlexkey(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/flexkey/update.do")
	public @ResponseBody String update(FlexkeyItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, flexkeyService.updateFlexkey(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/flexkey/delete.do")
	public @ResponseBody String delete(FlexkeyItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, flexkeyService.deleteFlexkey(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	
	@RequestMapping("/flexkey/getone.do")
	public @ResponseBody String getOne(FlexkeyItem item, String callback){
		JsonResult result = new JsonResult();
		try{
			FlexkeyItem flexkeyItem = flexkeyService.getOneFlexkey(item.getId());
			result = new JsonResult(1, null, flexkeyItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
