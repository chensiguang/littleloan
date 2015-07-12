package com.hexin.pettyLoan.finance.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.finance.model.SubjectItem;
import com.hexin.pettyLoan.finance.service.SubjectService;

@Controller
public class SubjectController {
	@Resource(name="subjectService")
	SubjectService subjectService;
	
	@RequestMapping("/finance/subject/getFirstLevel.do")
	public @ResponseBody String getFirstLevel(SubjectItem params,String callback){
		JsonResult result = null;
		try{
			List<SubjectItem> list = subjectService.getFirstLevel(params);
			result = new JsonResult(1, null, list);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
