package com.hexin.pettyLoan.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.service.CustomerRelationCommonService;

/**
 * 客户关系管理控制器
 * @author chenyongzhi
 *
 */
@Controller
public class CustomerRelationController {

	@Resource(name="customerRelationCommonService")
	private CustomerRelationCommonService customerRelationCommonService;

	/**
	 * 客户关联关系查询
	 * @param customerId	客户编号
	 * @param callback		回调方法
	 * @param authorityUserId	当前等人编号
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/customer/relation/find.do")
	public @ResponseBody String page(Integer customerId, String callback,Integer authorityUserId )
			throws ErrorCodeException {
		Map<String, List<AbstractCustomerRelationItem>>	data = customerRelationCommonService.find(customerId);
		JsonResult result = new JsonResult(1, null, data);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
