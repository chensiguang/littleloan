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
import com.hexin.pettyLoan.system.model.OrgnizationItem;
import com.hexin.pettyLoan.system.model.RoleItem;
import com.hexin.pettyLoan.system.service.FunctionService;

@Controller
public class FunctionController {
	//private static final Log logger = LogFactory.getLog(FunctionServiceImpl.class);
	@Resource(name="functionService")
	private FunctionService functionService;
	
	@RequestMapping("/function/getOne.do")
	public @ResponseBody String getOne(FunctionItem item,String callback){
		JsonResult result = new JsonResult(1,null,functionService.getOneFunction(item.getId()));
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/function/list.do")
	public @ResponseBody String list(FunctionItem params,String callback){
		JsonResult result = new JsonResult(1,null,functionService.getFunctionList(params));
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/function/page.do")
	public @ResponseBody String page(FunctionItem params,String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<FunctionItem> list = functionService.getFunctionList(params);
		Integer count = functionService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/function/insert.do")
	public @ResponseBody String insert(FunctionItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"创建成功",functionService.insertFunction(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/function/update.do")
	public @ResponseBody String update(FunctionItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"修改成功",functionService.updateFunction(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/function/delete.do")
	public @ResponseBody String delete(FunctionItem item,String callback){
		JsonResult result;
		try{
			result = new JsonResult(1,"删除成功",functionService.deleteFunction(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/function/getOrgnizationFunction.do")
	public @ResponseBody String getOrgnizationFunction(Integer orgnizationId, String callback){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, functionService.getOrgnizationFunction(orgnizationId));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
