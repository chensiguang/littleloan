package com.hexin.pettyLoan.archives.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.archives.model.UserArchivesAuthorityItem;
import com.hexin.pettyLoan.archives.service.ArchivesCommonService;
import com.hexin.pettyLoan.archives.service.UserArchivesAuthorityService;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.common.ErrorCode;


@Controller
public class UserArchivesAuthorityController {
	@Resource(name="userArchivesAuthorityService")
	private UserArchivesAuthorityService userArchivesAuthorityService; 
	@Resource(name="archivesCommonService")
	private ArchivesCommonService archivesCommonService;
	
	@RequestMapping("/userArchivesAuthority/userList.do")
	public @ResponseBody String userList(){
		return null;
	}
	
	@RequestMapping("/userArchivesAuthority/page.do")
	public @ResponseBody String page(UserArchivesAuthorityItem params, String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<UserArchivesAuthorityItem> list = userArchivesAuthorityService.getUserArchivesAuthorityPage(params);
		Integer count = userArchivesAuthorityService.getCount(params);
		
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/userArchivesAuthority/insert.do")
	public @ResponseBody String insert(UserArchivesAuthorityItem params, String callback){
		JsonResult result = new JsonResult();
		try {
			UserArchivesAuthorityItem getone = userArchivesAuthorityService.getOneUserArchivesAuthority(params);
			if(getone != null && getone.getIsvalid() == 1){
				throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001);
			}else if(getone != null && getone.getIsvalid() == 0){
				result = new JsonResult(1, "修改成功", userArchivesAuthorityService.updateUserArchivesAuthority(getone));
			}else{
				result = new JsonResult(1, "创建成功", userArchivesAuthorityService.insertUserArchivesAuthority(params));
			}
		} catch (ErrorCodeException ex) {
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/userArchivesAuthority/delete.do")
	public @ResponseBody String delete(UserArchivesAuthorityItem params, String callback){
		JsonResult result = new JsonResult();
		try {
			UserArchivesAuthorityItem getone = userArchivesAuthorityService.getOneUserArchivesAuthority(params);
			if(getone == null || getone.getIsvalid() == 0){
				throw new ErrorCodeException("ARC00002", ErrorCode.ARC00002);
			}else{
				result = new JsonResult(1, "解除成功", userArchivesAuthorityService.deleteUserArchivesAuthority(getone));
			}
		} catch (ErrorCodeException ex) {
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		return null;
	}
}
