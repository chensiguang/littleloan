package com.hexin.pettyLoan.portals.controller;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.hexin.core.util.UploadPathManager;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.AttachmentItem;
import com.hexin.pettyLoan.portals.service.AttachmentService;

@Controller
public class AttachmentController {
	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	
	@RequestMapping("/attachment/list.do")
	public @ResponseBody String list(AttachmentItem params, String callback) {
		List<AttachmentItem> list = attachmentService.getAttachmentList(params);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/attachment/delete.do")
	public @ResponseBody String delete(AttachmentItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, attachmentService.deleteAttachment(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/attachment/getone.do")
	public @ResponseBody String getOne(Integer id, String callback){
		JsonResult result = new JsonResult();
		try{
			AttachmentItem AttachmentItem = attachmentService.getOneAttachment(id);
			result = new JsonResult(1, null, AttachmentItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/attachment/download.do")
	public void download(Integer id, String callback, final HttpServletResponse response){
		URL file = null;
		HttpURLConnection conn = null;
		BufferedInputStream in = null;
		ServletOutputStream out =  null;
		try{
			AttachmentItem item = attachmentService.getOneAttachment(id);
			file = new URL(UploadPathManager.getUploadWebPath()+item.getPath());
			conn = (HttpURLConnection)file.openConnection();
			conn.connect();
			in = new BufferedInputStream(conn.getInputStream());
			byte[] fileBytes = new byte[1024];
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(item.getFilename(), "UTF-8"));
			out = response.getOutputStream();
			int len = -1;
			while ((len = in.read(fileBytes, 0, 1024)) != -1){
				out.write(fileBytes, 0, len);
			}
			in.close();
			out.close();
			conn.disconnect();
		}
		catch(Exception ex){
			throw new ErrorCodeException("PTL00401", ErrorCode.PTL00401, ex);
		}
	}
}
