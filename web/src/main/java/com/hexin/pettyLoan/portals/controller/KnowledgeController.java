package com.hexin.pettyLoan.portals.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.HtmlUtil;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.StringUtil;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.portals.model.AttachmentItem;
import com.hexin.pettyLoan.portals.model.KnowledgeItem;
import com.hexin.pettyLoan.portals.service.AttachmentService;
import com.hexin.pettyLoan.portals.service.KnowledgeService;

@Controller
public class KnowledgeController {
	@Resource(name="knowledgeService")
	private KnowledgeService knowledgeService;
	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	
	@RequestMapping("/knowledge/page.do")
	public @ResponseBody String page(KnowledgeItem params, String callback) {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<KnowledgeItem> list = knowledgeService.getKnowledgeListPage(params);
		for (KnowledgeItem item : list) {
			item.setContent(StringUtil.limit(HtmlUtil.getTextFromHtml(item.getContent()), 40));
		}
		Integer count = knowledgeService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/knowledge/pageByApprove.do")
	public @ResponseBody String pageByApprove(KnowledgeItem params, String callback) {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<KnowledgeItem> list = knowledgeService.getApproveKnowledgeList(params);
		for (KnowledgeItem item : list) {
			item.setContent(StringUtil.limit(HtmlUtil.getTextFromHtml(item.getContent()), 40));
		}
		Integer count = knowledgeService.getApproveCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/knowledge/list.do")
	public @ResponseBody String list(KnowledgeItem params, String callback) {
		List<KnowledgeItem> list = knowledgeService.getKnowledgeListPage(params);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping(value="/knowledge/insert.do")
	public @ResponseBody String insert(KnowledgeItem item, String callback, Integer authorityUserId, String attachIds){
		JsonResult result = new JsonResult();
		try{
			item.setCreater(authorityUserId);
			item = knowledgeService.insertKnowledge(item);
			attachmentService.updateAttachments(attachIds, item.getId());
			result = new JsonResult(1, null, item);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/knowledge/update.do")
	public @ResponseBody String update(KnowledgeItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, knowledgeService.updateKnowledge(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/knowledge/approve.do")
	public @ResponseBody String approve(KnowledgeItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, knowledgeService.approveKnowledge(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/knowledge/delete.do")
	public @ResponseBody String delete(KnowledgeItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, knowledgeService.deleteKnowledge(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/knowledge/getone.do")
	public @ResponseBody String getOne(Integer id, String callback){
		JsonResult result = new JsonResult();
		try{
			KnowledgeItem knowledgeItem = knowledgeService.getOneKnowledge(id);
			AttachmentItem params = new AttachmentItem();
			params.setType("ptl_knowledge");
			params.setTypeid(id);
			List<AttachmentItem> attachList = attachmentService.getAttachmentList(params);
			knowledgeItem.setAttachList(attachList);
			result = new JsonResult(1, null, knowledgeItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
