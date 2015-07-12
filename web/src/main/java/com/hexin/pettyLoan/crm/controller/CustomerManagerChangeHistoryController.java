package com.hexin.pettyLoan.crm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.crm.model.CustomerChatHistoryItem;
import com.hexin.pettyLoan.crm.model.CustomerManagerChangeHistoryItem;
import com.hexin.pettyLoan.crm.service.CustomerManagerChangeHistoryService;

@Controller
public class CustomerManagerChangeHistoryController {
	
	@Resource(name="customerManagerChangeHistoryService")
	private CustomerManagerChangeHistoryService customerManagerChangeHistoryService;
	
	@RequestMapping("/customerManagerChangeHistory/page.do")
	public @ResponseBody String page(CustomerManagerChangeHistoryItem item, String callback) {
		/*List<CustomerManagerChangeHistoryItem> list = userManagerChangeHistoryService.getCustomerList(item);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);*/
		return null;
	}
	
	@RequestMapping("/customerManagerChangeHistory/update.do")
	public @ResponseBody String update(CustomerChatHistoryItem item, String callback, Integer id){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, customerManagerChangeHistoryService.updateUserManager(id));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
/*	@RequestMapping("/userManagerChangeHistory/getone.do")
	public @ResponseBody String getOne(Integer id, String callback){
		JsonResult result = new JsonResult();
		try{
			CustomerChatHistoryItem customerChatHistoryItem = userManagerChangeHistoryService.getOneCustomerChatHistory(id);
			result = new JsonResult(1, null, customerChatHistoryItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	*/
}
