package com.hexin.pettyLoan.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.service.CustomerBankAccountService;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.CustomerContactInformationService;
import com.hexin.pettyLoan.crm.service.CustomerFamilyService;
import com.hexin.pettyLoan.crm.service.CustomerHouseService;
import com.hexin.pettyLoan.crm.service.CustomerJobService;
import com.hexin.pettyLoan.system.model.RoleItem;

/**
 * 客户基础信息管理
 * @author chenjiang
 *
 */
public class CustomerInfoController {
    @Resource(name = "customerBasicInformationService")
    private CustomerBasicInformationService   customerBasicInformationService;

    @Resource(name = "customerJobService")
    private CustomerJobService                customerJobService;

    @Resource(name = "customerBankAccountService")
    private CustomerBankAccountService        customerBankAccountService;

    @Resource(name = "customerHouseService")
    private CustomerHouseService              customerHouseService;

    @Resource(name = "customerContactInformationService")
    private CustomerContactInformationService customerContactInformationService;

    @Resource(name = "customerFamilyService")
    private CustomerFamilyService             customerFamilyService;
    
    @RequestMapping("/customerInfo/put.do")
    public @ResponseBody String put(CustomerBasicInformationItem item, String callback,Integer authorityUserId) {
        customerBasicInformationService.insertCustomerBasicInformation(item);
        JsonResult result = new JsonResult(0, "操作成功", null);
        String jsonresult = JSONUtil.toJsonpString(result, callback);
        return jsonresult;
    }
    
    @RequestMapping("/customerInfo/page.do")
    public @ResponseBody String page(CustomerBasicInformationItem param, String callback,Integer authorityUserId) {
        List<CustomerBasicInformationItem> customerList = customerBasicInformationService.getCustomerBasicInformationListPage(param);
        Integer count = customerBasicInformationService.getCount(param);
        PagedJsonResult result = new PagedJsonResult(customerList, 1, null, count, param.getRows());
        String jsonresult = JSONUtil.toJsonpString(result, callback);
        return jsonresult;
    }

}
