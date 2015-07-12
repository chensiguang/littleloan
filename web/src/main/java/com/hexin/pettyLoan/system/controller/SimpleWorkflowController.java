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
import com.hexin.pettyLoan.system.model.SimpleWorkflowConfigItem;
import com.hexin.pettyLoan.system.model.SimpleWorkflowExecuteItem;
import com.hexin.pettyLoan.system.service.SimpleWorkflowService;

@Controller
public class SimpleWorkflowController {
	@Resource(name="simpleWorkflowService")
	SimpleWorkflowService simpleWorkflowService;
	
	@RequestMapping("/simpleWorkflow/getListByOrgnizationId.do")
	public @ResponseBody String getListByOrgnizationId(Integer orgnizationId, String callback){
		JsonResult result = null;
		try{
			List<SimpleWorkflowConfigItem> list = simpleWorkflowService.getSimpleWorkflowConfigListByOrgnizationId(orgnizationId);
			result = new JsonResult(1, null, list);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/simpleWorkflow/setSimpleWorkflowConfig.do")
	public @ResponseBody String setSimpleWorkflowConfig(String configListJson, Integer authorityUserId, String callback){
		JsonResult result = null;
		try{
			List<SimpleWorkflowConfigItem> list = JSONUtil.parseArray(configListJson, SimpleWorkflowConfigItem.class);
			for(SimpleWorkflowConfigItem item : list){
				item.setCreater(authorityUserId);
			}
			simpleWorkflowService.setSimpleWorkflowConfig(list);
			result = new JsonResult(1, "设置成功！", null);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/simpleWorkflow/getWorkflowExecuteByUserId.do")
	public @ResponseBody String getWorkflowExecuteByUserId(SimpleWorkflowExecuteItem params, Integer authorityUserId, String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<SimpleWorkflowExecuteItem> list = simpleWorkflowService.getWorkflowExecuteByUserId(params);
		Integer count = simpleWorkflowService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	} 
	
	@RequestMapping("/simpleWorkflow/approveWorkflowExecute.do")
	public @ResponseBody String approveWorkflowExecute(Integer id, String status, String approveDescription, Integer approveUserId,Integer authorityUserId, String callback){
		JsonResult result = null;
		try{
			SimpleWorkflowExecuteItem item = simpleWorkflowService.approveWorkflowExecute(id, status, approveDescription, approveUserId);
			result = new JsonResult(1, null, item);
		}
		catch(Exception ex){
			result = new JsonResult(-1, ex.getMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
