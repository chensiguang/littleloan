package com.hexin.pettyLoan.finance.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.pettyLoan.finance.model.BankAccountInfo;
import com.hexin.pettyLoan.finance.service.CashierService;
import com.hexin.pettyLoan.portals.service.ContentService;

@Controller
public class CashierController {
	
	private final static Log logger = LogFactory.getLog(CashierController.class);
	
	@Resource(name="cashierService")
	private CashierService cashierService;
	
	@RequestMapping("/bankaccountinfo/insert.do")
	@ResponseBody
	public String add(BankAccountInfo params, String callback) {
		JsonResult result;
		try{
			result = new JsonResult(1, null, cashierService.insertBankAccount(params));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/bankaccountinfo/list.do")
	@ResponseBody
	public String list(BankAccountInfo params, String callback) {
		JsonResult result;
		try{
			List<BankAccountInfo> list = cashierService.getBankAccountInfoList(params);
			result = new JsonResult(1, null, list);
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
