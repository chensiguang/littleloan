package com.hexin.pettyLoan.archives.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.RequestDeviceUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.pettyLoan.archives.constants.ArchivesConstants;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesItem;
import com.hexin.pettyLoan.archives.service.ElectronicArchivesService;

@Controller
public class ElectronicArchivesController {

	private final static Log logger  = LogFactory.getLog(ElectronicArchivesService.class);
	
	@Resource(name = "electronicArchivesService")
	private ElectronicArchivesService electronicArchivesService;
	
	@Resource
	private HttpServletRequest request;
	
	/**
	 * 建档（保存）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/ElectronicArchives/addArchivesSave.do")
	public String addArchivesSave(ElectronicArchivesItem item, String callback) throws ErrorCodeException {
		
		JsonResult result = new JsonResult();
		try {
			// 获取档案来源（终端）
			item.setSrcTerminalCode(RequestDeviceUtil.isMobileDevice(request)?ArchivesConstants.ARC_SRC_APP:ArchivesConstants.ARC_SRC_PC);
			
			// 档案来源（系统）代码：电子档案系统
			item.setSrcSystemCode(ArchivesConstants.ARC_SRC_ARCHIVES);
			
			result = new JsonResult(1, null, electronicArchivesService.addArchivesSave(item) );
		} catch (ErrorCodeException ex) {
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
