package com.hexin.pettyLoan.system.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hexin.core.model.JsonResult;
import com.hexin.core.util.DateUtil;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.UploadPathManager;
import com.hexin.pettyLoan.portals.model.AttachmentItem;
import com.hexin.pettyLoan.portals.service.AttachmentService;

@Controller
public class FileUploadController {
	public static void isExist(String path) {
		File file =new File(path);
		if(!file.exists() && !file.isDirectory()) {
		    file.mkdirs();
		} 
	}
	
	Properties props=System.getProperties();
	String fileMiddle = props.getProperty("file.separator");
	@RequestMapping(value="/file/fileupload.do", method=RequestMethod.POST)
	public @ResponseBody String fileupload(@RequestParam("upFileName") MultipartFile mFileName,Integer authorityUserId,
			HttpServletRequest request,HttpServletResponse response){
		String fileType = "";
		String fileName = "";
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);
			//按用户ID+年月的文件目录                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			String saveUserPath = authorityUserId.toString() + fileMiddle + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH);
			String httpUserPath = authorityUserId.toString() + "/" + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + "/";
			
			String path = UploadPathManager.getUploadPhysicalPath() + fileMiddle + saveUserPath;
			isExist(path);
			fileType = mFileName.getOriginalFilename().substring(mFileName.getOriginalFilename().lastIndexOf(".")+1);
			fileName = DateUtil.formatDateTime(new Date(), "yyyyMMddHHmmssSSS");
			String saveFile = path + fileMiddle + fileName + "." + fileType;
			File file=new File(saveFile);
			int i=0;
			while(file.exists()){
				fileName = fileName+i;
				saveFile = path + fileMiddle + fileName + "." + fileType;
				i++;
				file = new File(saveFile);
			}
			String url = UploadPathManager.getUploadWebPath() + httpUserPath + fileName+"."+fileType;
			FileCopyUtils.copy(mFileName.getBytes(),new File(saveFile));
			return JSONUtil.toJsonString(new JsonResult(1,null,url));
		}
		catch(Exception ex){
			ex.printStackTrace();
			return JSONUtil.toJsonString(new JsonResult(-1,"上传失败："+ex.getMessage(),null));
		}
	}
	
	@RequestMapping(value="/file/fileupload4kindedit.do", method=RequestMethod.POST)
	public String fileupload4kindedit(@RequestParam("imgFile") MultipartFile mFileName,Integer authorityUserId,String domain,
			HttpServletRequest request,HttpServletResponse response){
		String fileType = "";
		String fileName = "";
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);
			//按用户ID+年月的文件目录                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			String saveUserPath = authorityUserId.toString() + fileMiddle + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH);
			String httpUserPath = authorityUserId.toString() + "/" + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + "/";
			
			String path = UploadPathManager.getUploadPhysicalPath() + fileMiddle + saveUserPath;
			isExist(path);
			fileType = mFileName.getOriginalFilename().substring(mFileName.getOriginalFilename().lastIndexOf(".")+1);
			fileName = DateUtil.formatDateTime(new Date(), "yyyyMMddHHmmssSSS");
			String saveFile = path + fileMiddle + fileName + "." + fileType;
			File file=new File(saveFile);
			int i=0;
			while(file.exists()){
				fileName = fileName+i;
				saveFile = path + fileMiddle + fileName + "." + fileType;
				i++;
				file = new File(saveFile);
			}
			String url = UploadPathManager.getUploadWebPath() + httpUserPath + fileName+"."+fileType;
			FileCopyUtils.copy(mFileName.getBytes(),new File(saveFile));
			return "redirect:"+ domain +"html/controller/redirect.html?okmsg=" +JSONUtil.toJsonString(new JsonResult(1,null,url));
		}
		catch(Exception ex){
			ex.printStackTrace();
			return JSONUtil.toJsonString(new JsonResult(-1,"上传失败："+ex.getMessage(),null));
		}
	}
	
	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	@RequestMapping(value="/file/fileupload2db.do", method=RequestMethod.POST)
	public @ResponseBody String fileupload2db(@RequestParam("upFileName") MultipartFile mFileName,Integer authorityUserId,
			String type, Integer typeid, 
			HttpServletRequest request,HttpServletResponse response){
		String fileType = "";
		String fileName = "";
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);
			//按用户ID+年月的文件目录                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			String saveUserPath = authorityUserId.toString() + fileMiddle + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH);
			String httpUserPath = authorityUserId.toString() + "/" + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + "/";
			
			String path = UploadPathManager.getUploadPhysicalPath() + fileMiddle + saveUserPath;
			isExist(path);
			fileType = mFileName.getOriginalFilename().substring(mFileName.getOriginalFilename().lastIndexOf(".")+1);
			fileName = DateUtil.formatDateTime(new Date(), "yyyyMMddHHmmssSSS");
			String saveFile = path + fileMiddle + fileName + "." + fileType;
			File file=new File(saveFile);
			int i=0;
			while(file.exists()){
				fileName = fileName+i;
				saveFile = path + fileMiddle + fileName + "." + fileType;
				i++;
				file = new File(saveFile);
			}
			FileCopyUtils.copy(mFileName.getBytes(),new File(saveFile));
			
			AttachmentItem dbItem = new AttachmentItem();
			dbItem.setType(type);
			dbItem.setTypeid(typeid);
			dbItem.setFilename(mFileName.getOriginalFilename());
			dbItem.setFiletype(fileType);
			dbItem.setFilesize(mFileName.getSize());
			dbItem.setServer(UploadPathManager.getUploadWebPath());
			dbItem.setPath(httpUserPath + fileName+"."+fileType);
			attachmentService.insertAttachment(dbItem);
			
			return JSONUtil.toJsonString(new JsonResult(1,null,dbItem));
		}
		catch(Exception ex){
			ex.printStackTrace();
			return JSONUtil.toJsonString(new JsonResult(-1,"上传失败："+ex.getMessage(),null));
		}
	}
}
