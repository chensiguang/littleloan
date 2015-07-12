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
import com.hexin.pettyLoan.system.model.OrgnizationItem;
import com.hexin.pettyLoan.system.service.OrgnizationService;


@Controller
public class OrgnizationController{
	//private final static Log logger = LogFactory.getLog(OrgnizationService.class);
	@Resource(name="orgnizationService")
	private OrgnizationService orgnizationService;
	
	@RequestMapping("/orgnization/getTree.do")
	public @ResponseBody String getNewTree(OrgnizationItem item,String callback){
		List<OrgnizationItem> list = orgnizationService.getFirst(item);
		for(int i=0;i<list.size();i++){
			OrgnizationItem node = list.get(i);
			node.setText(node.getOrgnizationName());
			//查询是否有子节点
			List<OrgnizationItem> childList = orgnizationService.getChildrenOrgnizations(node.getId());
			if(childList.size()>0){
				for(int j=0;j<childList.size();j++){
					OrgnizationItem childNode = childList.get(j);
					List<OrgnizationItem> gChildList = orgnizationService.getChildrenOrgnizations(childNode.getId());
					if(gChildList.size()>0){
						childNode.setChildren(gChildList);
					}
					childList.set(j, childNode);
				}
				node.setChildren(childList);
				System.out.println("node.getChildren()==="+JSONUtil.toJsonString(node.getChildren()));
			}
			list.set(i, node);
		}
		JsonResult result = new JsonResult(1,null,list);
		String json= JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	@RequestMapping("/orgnization/list.do")
	public @ResponseBody String list(OrgnizationItem item,String callback){
		List<OrgnizationItem> list = orgnizationService.getOrgnizationList(item);
		JsonResult result = new JsonResult(1,null,list);
		String json= JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	@RequestMapping("/orgnization/page.do")
	public @ResponseBody String page(OrgnizationItem params,String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<OrgnizationItem> list = orgnizationService.getOrgnizationList(params);
		Integer count = orgnizationService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/orgnization/insert.do")
	public @ResponseBody String insert(OrgnizationItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			result = new JsonResult(1,"创建成功",orgnizationService.insertOrgnization(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(), ex);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		System.out.println("String json===="+json);
		return json;
	}
	@RequestMapping("/orgnization/insertChild.do")
	public @ResponseBody String insertChild(OrgnizationItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"创建成功",orgnizationService.insertChildOrgnization(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(), ex);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/orgnization/update.do")
	public @ResponseBody String update(OrgnizationItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"修改成功",orgnizationService.updateOrgnization(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(), ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		System.out.println("String json===="+json);
		return json;
	}
	@RequestMapping("/orgnization/delete.do")
	public @ResponseBody String delete(OrgnizationItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1,"删除成功",orgnizationService.deleteOrgnization(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
			//logger.error(ex.toMessage(),ex);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/orgnization/getone.do")
	public @ResponseBody String getone(OrgnizationItem item,String callback){
		OrgnizationItem orgnizationItem = orgnizationService.getOneOrgnization(item.getId());
		JsonResult result = new JsonResult(1,null,orgnizationItem);
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
