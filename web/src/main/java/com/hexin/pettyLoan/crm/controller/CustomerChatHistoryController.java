package com.hexin.pettyLoan.crm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.crm.model.CustomerChatHistoryItem;
import com.hexin.pettyLoan.crm.service.CustomerChatHistoryService;

@Controller
public class CustomerChatHistoryController {
	@Resource(name="customerChatHistoryService")
	private CustomerChatHistoryService customerChatHistoryService;
	
	@RequestMapping("/customerChatHistory/list.do")
	public @ResponseBody String list(CustomerChatHistoryItem item, String callback) {
		List<CustomerChatHistoryItem> list = customerChatHistoryService.getCustomerChatHistory(item);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/customerChatHistory/delete.do")
	public @ResponseBody String delete(CustomerChatHistoryItem item, String callback, Integer id){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, customerChatHistoryService.deleteCustomerChatHistory(id));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/customerChatHistory/getone.do")
	public @ResponseBody String getOne(Integer id, String callback){
		JsonResult result = new JsonResult();
		try{
			CustomerChatHistoryItem customerChatHistoryItem = customerChatHistoryService.getOneCustomerChatHistory(id);
			result = new JsonResult(1, null, customerChatHistoryItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/customerChatHistory/download.do")
	public void upload(Integer id, String callback, final HttpServletResponse response){
		try{
			CustomerChatHistoryItem item = customerChatHistoryService.getOneCustomerChatHistory(id);
			File fileToDown = new File(item.getFilePath());
			FileInputStream fileIn = new FileInputStream(fileToDown);
			BufferedInputStream in = new BufferedInputStream(fileIn);
			byte[] fileBytes = new byte[1024];
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(item.getFileName(), "UTF-8"));
			ServletOutputStream out;
			out = response.getOutputStream();
			int len = -1;
			while ((len = in.read(fileBytes, 0, 1024)) != -1){
				out.write(fileBytes, 0, len);
			}
			in.close();
			out.close();
		}
		catch(Exception ex){
			throw new ErrorCodeException("PTL00401", ErrorCode.PTL00401, ex);
		}
	}
}
