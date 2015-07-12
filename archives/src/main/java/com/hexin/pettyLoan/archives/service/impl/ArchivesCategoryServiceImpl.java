package com.hexin.pettyLoan.archives.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.ArchivesCategoryItem;
import com.hexin.pettyLoan.archives.service.ArchivesCategoryService;
import com.hexin.pettyLoan.common.ErrorCode;
@Service("archivesCategoryService")
public class ArchivesCategoryServiceImpl implements ArchivesCategoryService {
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;

	@Override
	@AccessLog(description="query children archivesCategory",operateType=OperateType.Query)
	public List<ArchivesCategoryItem> getRoot(ArchivesCategoryItem params)
			throws ErrorCodeException {
		List<ArchivesCategoryItem> result;
		result = readDao.query(ArchivesCategoryItem.NAMESPACE, "getRoot",params);
		return result;
	}

	@Override
	public boolean hasChildren(Integer id) throws ErrorCodeException {
		ArchivesCategoryItem params = new ArchivesCategoryItem();
		params.setId(id);
		int number = readDao.count(ArchivesCategoryItem.NAMESPACE, "countChildren", params);
		if(number != 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@AccessLog(description="query children archivesCategory",operateType=OperateType.Query)
	public List<ArchivesCategoryItem> getChildren(Integer id)
			throws ErrorCodeException {
		ArchivesCategoryItem params = new ArchivesCategoryItem();
		params.setId(id);
		List<ArchivesCategoryItem> list1=readDao.query(ArchivesCategoryItem.NAMESPACE, "getChildren", params);
		for(int i=0;i<list1.size();i++){
			ArchivesCategoryItem child = list1.get(i);
			child.setText(child.getName());
			if(hasChildren(child.getId())){
				List<ArchivesCategoryItem> list2 = getChildren(child.getId());
				child.setChildren(list2);
			}
		}
		return list1;
	}

	@Override
	@AccessLog(description="insert a archivesCategory",operateType=OperateType.Insert,add2DB=Add2DB.Yes)
	public ArchivesCategoryItem insertArchivesCategory(ArchivesCategoryItem item ,Integer authorityUserId) throws ErrorCodeException {
		try{
			writeDao.insert(ArchivesCategoryItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){ 
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		return item;
	}


	@Override
	@AccessLog(description="delete one archivesCategory",operateType=OperateType.Delete,add2DB=Add2DB.Yes)
	public Boolean deleteArchivesCategory(ArchivesCategoryItem item) throws ErrorCodeException {
		Integer retCount = (Integer) writeDao.delete(ArchivesCategoryItem.NAMESPACE, item);
		return retCount>0;
	}

	@Override
	@AccessLog(description="update one archivesCategory",operateType=OperateType.Update,add2DB=Add2DB.Yes)
	public ArchivesCategoryItem updateArchivesCategory(ArchivesCategoryItem item)
			throws ErrorCodeException {
		try{
			writeDao.update(ArchivesCategoryItem.NAMESPACE, item);
		}catch(ErrorCodeException ex){
			throw new ErrorCodeException("ARC00001", ErrorCode.ARC00001, ex);
		}
		 return item;
	}

}
