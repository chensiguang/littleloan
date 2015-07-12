package com.hexin.pettyLoan.portals.controller;

import java.util.Date;
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
import com.hexin.pettyLoan.portals.model.SurveyItem;
import com.hexin.pettyLoan.portals.service.SurveyService;

/**
 * 问卷管理
 * @author chenyongzhi
 */
@Controller
public class SurveyController {
	
	@Resource(name="surveyService")
	private SurveyService surveyService;

	/**
	 * 分页查询问卷列表
	 * @param params
	 * @param callback
	 * @return String
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/survey/page.do")
	public @ResponseBody String page(SurveyItem params,String callback,Integer authorityUserId )
			throws ErrorCodeException {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setCreater(authorityUserId);
		params.setStart((params.getPage()-1)*params.getRows());
		params.setStatus(Short.valueOf("0"));
		List<SurveyItem> surveys =	surveyService.getSurveyList(params);
		Integer count = surveyService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(surveys, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 问卷查询列表
	 * @param params
	 * @param callback
	 * @return String
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/survey/queryList.do")
	public @ResponseBody String queryList(SurveyItem params,String callback,Integer authorityUserId )
			throws ErrorCodeException {
		params.setCreater(authorityUserId);
		List<SurveyItem> surveys =	surveyService.getSurveyList(params);
		JsonResult result = new JsonResult(1, null, surveys);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}

	/**
	 * 问卷审核查询列表
	 * @param item
	 * @param callback
	 * @return String
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/survey/checkList.do")
	public @ResponseBody String checkList(SurveyItem params,String callback)
			throws ErrorCodeException {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<SurveyItem> surveys =	surveyService.getSurveyListByCheckStatus(params);
		Integer count = surveyService.getCheckStatusCount(params);
		PagedJsonResult result = new PagedJsonResult(surveys, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 问卷详情查看
	 * @param id
	 * @param callback
	 * @return String
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/survey/detail.do")
	public @ResponseBody String detail(Integer id, String callback)
			throws ErrorCodeException {
		JsonResult result = null;
		try {
			SurveyItem item =	surveyService.findSurveyById(id);
			result = new JsonResult(1, null, item);
		} catch (ErrorCodeException e) {
			result = new JsonResult(-1, null, e.getMessage());
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 插入或更新问卷标题与描述信息
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return String
	 */
	@RequestMapping(value="/survey/insertOrUpdate.do")
	public @ResponseBody String insertOrUpdate(SurveyItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			
			if(null != item.getId() && 0 != item.getId()){ //id不为空时是更新操作，否则是添加操作
				Boolean flag =	surveyService.updateSurvey(item);
				result = new JsonResult(flag ? 1 : -1, null, item.getId());
			}else{
				item.setStatus(Short.valueOf("0"));
				item.setIsvalid(Short.valueOf("1"));
				item.setCreater(authorityUserId);
				item.setCtime(new Date());
				item = surveyService.insertSurvey(item);
				result = new JsonResult(null != item.getId() ? 1 : -1, null, item.getId());
			}
		} catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 删除问卷
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 */
	@RequestMapping(value="/survey/delete.do")
	public @ResponseBody String delete(SurveyItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			Boolean flag = surveyService.deleteSurvey(item);
			result = new JsonResult(flag ? 1 : -1, null, item.getId());
		} catch (ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 更新问卷状态
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 */
	@RequestMapping(value="/survey/updateStatus.do")
	public @ResponseBody String updateStatus(SurveyItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			Boolean flag = surveyService.updateSurveyStatus(item.getId(), item.getStatus(),authorityUserId);
			result = new JsonResult(flag ? 1 : -1, "更新成功", item.getId());
		} catch (ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}

}
