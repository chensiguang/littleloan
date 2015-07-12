package com.hexin.pettyLoan.portals.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.util.StringUtil;
import com.hexin.pettyLoan.portals.model.AttachmentItem;
import com.hexin.pettyLoan.portals.service.AttachmentService;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	public List<AttachmentItem> getAttachmentList(AttachmentItem params)
			throws ErrorCodeException {
		return readDao.query(AttachmentItem.NAMESPACE, params);
	}

	@Override
	public AttachmentItem getOneAttachment(Integer id)
			throws ErrorCodeException {
		return readDao.getOne(AttachmentItem.NAMESPACE, new AttachmentItem(id));
	}
	
	@Override
	public Boolean updateAttachments(String ids, Integer typeid) throws ErrorCodeException {
		if(StringUtil.isBlank(ids)) return true;
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("ids", ids);
		sqlParams.put("typeid", typeid);
		return writeDao.getSqlSessionTemplate().update(AttachmentItem.NAMESPACE+".updateByTypeid", sqlParams) > 0;
	}

	@Override
	public AttachmentItem insertAttachment(AttachmentItem item)
			throws ErrorCodeException {
		writeDao.insert(AttachmentItem.NAMESPACE, item);
		return item;
	}

	@Override
	public Boolean deleteAttachment(AttachmentItem item)
			throws ErrorCodeException {
		return writeDao.delete(AttachmentItem.NAMESPACE, item) > 0;
	}

}
