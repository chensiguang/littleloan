package com.hexin.pettyLoan.portals.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.StringUtil;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.SurveyItem;
import com.hexin.pettyLoan.portals.model.SurveyQuestionItem;
import com.hexin.pettyLoan.portals.service.SurveyQuestionService;
import com.hexin.pettyLoan.portals.service.SurveyService;

/**
 * 问卷问题管理
 * @author chenyongzhi
 */
@Controller
public class SurveyQuestionController {
	
	@Resource(name="surveyService")
	private SurveyService surveyService;
	
	/**
	 * 问卷问题接口 
	 */
	@Resource(name="surveyQuestionService")
	private SurveyQuestionService surveyQuestionService;
	
	/**
	 * 问卷问题详情查询
	 * @param params
	 * @param callback
	 * @return String
	 * @throws ErrorCodeException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/surveyquestion/queryList.do")
	public @ResponseBody String queryList(SurveyQuestionItem params,String callback,Integer authorityUserId )
			throws ErrorCodeException {
		JsonResult result = null;
		List<SurveyQuestionItem> data = surveyQuestionService.find(params);
		if(null != data && data.size() > 0) {
			List<Map<Integer,List<SurveyQuestionItem>>> questions = (List<Map<Integer, List<SurveyQuestionItem>>>) dealQuestionByGroup(data);
			sortByNo(questions);
			result = new JsonResult(1, null, questions);
		}else{
			result = new JsonResult(-1, new ErrorCodeException("PTL00306",ErrorCode.PTL00306,null).getMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 查询所有问卷问题列表
	 * @param params
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/surveyquestion/queryQuestionList.do")
	public @ResponseBody String queryQuestionList(SurveyQuestionItem params,String callback,Integer authorityUserId )
			throws ErrorCodeException {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setCreater(authorityUserId);
		params.setStart((params.getPage()-1)*params.getRows());
		List<SurveyQuestionItem> questions =	surveyQuestionService.findForQuestion(params);
		Integer count = surveyQuestionService.getCountForQuestion(params);
		//构造返回结果treegrid数据
		List<Object>data = initTreeGridData(questions);

		PagedJsonResult result = new PagedJsonResult(data, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 根据ID删除问题或选项
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 */
	@RequestMapping(value="/surveyquestion/delete.do")
	public @ResponseBody String delete(SurveyQuestionItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			Boolean flag = surveyQuestionService.deleteById(item.getId());
			result = new JsonResult(flag ? 1 : -1, null, item.getId());
		} catch (ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 改变问题排序
	 * @param questionId
	 * @param toId
	 * @param callback
	 * @param authorityUserId
	 * @return
	 */
	@RequestMapping(value="/surveyquestion/changeSort.do")
	public @ResponseBody String changeSort(Integer questionId, Integer toId, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			Boolean flag = surveyQuestionService.changeSort(questionId, toId);
			result = new JsonResult(flag ? 1 : -1, null, null);
		} catch (ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 保存或更新问题
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 */
	@RequestMapping(value="/surveyquestion/insertOrUpdate.do")
	public @ResponseBody String insertOrUpdate(SurveyQuestionItem item, String option, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			String[] options = null;
			if(!StringUtil.isEmpty(option)){
				options = option.split(",");
			}
			if(null != item.getId() && 0 != item.getId()){ //id不为空时是更新操作，否则是添加操作
				
				Boolean flag = surveyQuestionService.updateForContent(item, options);
				result = new JsonResult(flag ? 1 : -1, null, item.getId());
			}else{
				item.setIsvalid(Short.valueOf("1"));
				item.setCreater(authorityUserId);
				item.setCtime(new Date());
				item = surveyQuestionService.insertSurveyQuestion(item, options);
				result = new JsonResult(null != item.getId() ? 1 : -1, null, item.getId());
			}
		} catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	
	/**
	 * 对问题分组处理
	 * @param questions
	 * @return
	 */
	private Object dealQuestionByGroup(List<SurveyQuestionItem> questions){
		if(null != questions){
			List<Map<Integer,List<SurveyQuestionItem>>> result = new  ArrayList<Map<Integer,List<SurveyQuestionItem>>>();
			for(int i=0;i<questions.size();i++){
				SurveyQuestionItem item =	questions.get(i);
				if(null == item.getQuestionid()){
					Map<Integer,List<SurveyQuestionItem>> qa = new HashMap<Integer,List<SurveyQuestionItem>>();
					List<SurveyQuestionItem> tList =	new ArrayList<SurveyQuestionItem>();
					tList.add(item);
					qa.put(item.getId(), tList);
					result.add(qa);
				}
			}
			for(int i = 0; i<questions.size(); i++){
				SurveyQuestionItem item =	questions.get(i);
				if(null != item.getQuestionid()){		//选项处理
					for(int j = 0; j<result.size(); j++){
						List<SurveyQuestionItem> tempList = null;
						Map<Integer,List<SurveyQuestionItem>> map = result.get(j);
						if(map.containsKey(item.getQuestionid())){
							tempList =map.get(item.getQuestionid());
						}
						if(null != tempList){
							tempList.add(item);
						}
					}
				}
			}
			return result;
		}else{
			return null;
		}
	}
	
	/**
	 * 对整理后的问题项进行排序
	 * @param para
	 */
	private void sortByNo(List<Map<Integer,List<SurveyQuestionItem>>> para){
		if(null != para){
			for(int i=0; i<para.size(); i++){
				Map<Integer,List<SurveyQuestionItem>> map =	para.get(i);
				for(Entry<Integer, List<SurveyQuestionItem>> entry: map.entrySet()){
					List<SurveyQuestionItem> tempList = entry.getValue();
					Collections.sort(tempList,new Comparator<SurveyQuestionItem>(){
						@Override
						public int compare(SurveyQuestionItem o1,
								SurveyQuestionItem o2) {
							if(null != o1.getQuestionid() && null ==o2.getQuestionid()){
								return 1;
							}else if(o1.getSortno() < o2.getSortno()){
								return -1;
							}else{
								return 1;
							}
						}
					});
				}
			}
		}
	}
	
	/**
	 * 构造返回结果数据
	 * @param questions
	 * @return
	 */
	private List<Object> initTreeGridData(List<SurveyQuestionItem> questions){
		List<Object> data = new ArrayList<Object>();
		if(null != questions && questions.size() > 0){
			Integer upNodeId = 0;
			for(int i=0; i<questions.size();i++){
				SurveyQuestionItem question = questions.get(i);
				SurveyItem survey = surveyService.findSurveyById(question.getSurveyid());
				Map<String, Object> node = new HashMap<String, Object>();
				if(0 != i){
					node.put("upId", upNodeId);									//允许上移
				}
				if(questions.size() > (i+1)){
					node.put("nextId", questions.get(i + 1).getId());		//允许下移
				}
				node.put("id", question.getId());
				upNodeId = question.getId();
				node.put("sortNumber", i);				//自定义前端排序操作字段
				node.put("content", question.getContent());
				node.put("surveyId", question.getSurveyid());
				node.put("status", survey.getStatus());
				node.put("description", survey.getDescription());
				node.put("type", question.getType());
				node.put("surveyName", survey.getName());
				node.put("iconCls", "icon-question");
				node.put("state", "closed");
				
				if(null == question.getQuestionid()){
					List<Object> chlidren = new ArrayList<Object>();
					
					SurveyQuestionItem optionPara = new SurveyQuestionItem();
					optionPara.setQuestionid(question.getId());
					optionPara.setSurveyid(question.getSurveyid());
					List<SurveyQuestionItem> options =surveyQuestionService.find(optionPara);
					
					if(null != options && options.size() > 0){
						for(int j=0; j<options.size(); j++){
							SurveyQuestionItem cQuestion = options.get(j);
							Map<String, Object> cNode = new HashMap<String, Object>();
							cNode.put("id", cQuestion.getId());
							cNode.put("content", cQuestion.getContent());
							cNode.put("type", cQuestion.getType());
							node.put("iconCls", "icon-option");
							node.put("state", "closed");
							chlidren.add(cNode);
						}
					}
					node.put("children", chlidren);
				}
				data.add(node);
			}
		}
		return data;
	}
	
}
