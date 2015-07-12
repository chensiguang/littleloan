package com.hexin.pettyLoan.portals.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.util.StringUtil;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.SurveyItem;
import com.hexin.pettyLoan.portals.model.SurveyQuestionItem;
import com.hexin.pettyLoan.portals.service.SurveyAnswerService;
import com.hexin.pettyLoan.portals.service.SurveyQuestionService;
import com.hexin.pettyLoan.portals.service.SurveyService;

@Service("surveyQuestionService")
public class SurveyQuestionServiceImpl implements SurveyQuestionService {
	
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	@Resource(name="surveyAnswerService")
	SurveyAnswerService surveyAnswerService;
	
	@Resource(name="surveyService")
	SurveyService surveyService; 

	
	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="insert question", operateType=OperateType.Insert, add2DB=Add2DB.Yes)
	public SurveyQuestionItem insertSurveyQuestion(SurveyQuestionItem item, String[] options)
			throws ErrorCodeException {
		if(null == item){
			throw new ErrorCodeException("PTL00305", ErrorCode.PTL00305);
		}else{
			SurveyItem survey =	surveyService.findSurveyById(item.getSurveyid());
			if(null == survey){
				throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
			}else if(2 == survey.getStatus()){
				throw new ErrorCodeException("PTL00315", ErrorCode.PTL00315);
			}else if(3 == survey.getStatus()){
				throw new ErrorCodeException("PTL00316", ErrorCode.PTL00316);
			}else{
				Integer count = null;
				item.setCtime(new Date());
				count = (Integer) writeDao.insert(SurveyQuestionItem.NAMESPACE, item);
				if(!StringUtil.isEmpty(item.getType()) && !"3".equals(item.getType())){		//如果问题类型为单选或多选时添加选项
					if(null != options && options.length > 0){
						for(int i=0; i<options.length; i++){
							SurveyQuestionItem option = new SurveyQuestionItem();
							option.setContent(options[i]);
							option.setCreater(item.getCreater());
							option.setCtime(new Date());
							option.setIsvalid(Short.valueOf("1"));
							option.setQuestionid(item.getId());
							option.setSortno(Short.valueOf((i+1) + ""));
							option.setSurveyid(item.getSurveyid());
							option.setType(null);
							writeDao.insert(SurveyQuestionItem.NAMESPACE, option);
						}
					}
				}
				return null != count ? item : null;
			}
		}
	}

	@Override
	@AccessLog(description="find question by id", operateType=OperateType.Query, add2DB=Add2DB.Yes)
	public SurveyQuestionItem findById(Integer id) throws ErrorCodeException {
		SurveyQuestionItem sItem = new SurveyQuestionItem();
		sItem.setId(id);
		sItem = readDao.getOne(SurveyQuestionItem.NAMESPACE, "findById", sItem);
		return sItem;
	}

	@Override
	@AccessLog(description="find question", operateType=OperateType.Query, add2DB=Add2DB.Yes)
	public List<SurveyQuestionItem> find(SurveyQuestionItem item)
			throws ErrorCodeException {
		List<SurveyQuestionItem> questions = readDao.query(SurveyQuestionItem.NAMESPACE, item);
		return questions;
	}

	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="upate question content", operateType=OperateType.Update, add2DB=Add2DB.Yes)
	public Boolean updateForContent(SurveyQuestionItem item, String[] options) {
		if(null != item && null != item.getId()){
			SurveyQuestionItem para = new SurveyQuestionItem();
			para.setId(item.getId());
			para.setIsvalid(Short.valueOf("1"));
			SurveyQuestionItem question = readDao.getOne(SurveyQuestionItem.NAMESPACE, "findById", para);
			if(null == question){
				throw new ErrorCodeException("PTL00309", ErrorCode.PTL00309);
			}else{
				question.setType(item.getType());
				question.setMtime(new Date());
				question.setContent(item.getContent());
				readDao.update(SurveyQuestionItem.NAMESPACE, question);		//更新问题描述
				if("3".equals(item.getType())){
					SurveyQuestionItem si = new SurveyQuestionItem();
					si.setQuestionid(question.getId());
					readDao.delete(SurveyQuestionItem.NAMESPACE, si);
				}
				if(!StringUtil.isEmpty(question.getType()) && !"3".equals(question.getType())){		//如果问题类型为单选或多选时更新选项
					SurveyQuestionItem optionPara = new SurveyQuestionItem();
					optionPara.setQuestionid(question.getId());
					List<SurveyQuestionItem> tempOptions = readDao.query(SurveyQuestionItem.NAMESPACE, optionPara);
					if(null != options && options.length > 0){
						for(int i=0;  i< options.length; i++){
							if(null !=tempOptions && tempOptions.size() > 0 && null != tempOptions.get(i)){
								SurveyQuestionItem option = tempOptions.get(i);
								option.setContent(options[i]);
								option.setMtime(new Date());
								readDao.update(SurveyQuestionItem.NAMESPACE, option);	
							}else{
								SurveyQuestionItem option = new SurveyQuestionItem();
								option.setContent(options[i]);
								option.setCreater(question.getCreater());
								option.setCtime(new Date());
								option.setIsvalid(Short.valueOf("1"));
								option.setQuestionid(question.getId());
								option.setSortno(Short.valueOf((i+1) + ""));
								option.setSurveyid(question.getSurveyid());
								option.setType(null);
								writeDao.insert(SurveyQuestionItem.NAMESPACE, option);
							}
						}
					}
				}
				return true;
			}
		}else{
			throw new ErrorCodeException("PTL00305", ErrorCode.PTL00305);
		}
	}

	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="delete survey question", operateType=OperateType.Delete, add2DB=Add2DB.Yes)
	public Boolean deleteById(Integer id) throws ErrorCodeException {
		if(null != id){
			SurveyQuestionItem para = new SurveyQuestionItem();
			para.setId(id);
			para.setIsvalid(Short.valueOf("1"));
			SurveyQuestionItem item = readDao.getOne(SurveyQuestionItem.NAMESPACE, "findById", para);
			if(null == item){
				throw new ErrorCodeException("PTL00309", ErrorCode.PTL00309);
			} else {
				SurveyItem survey =	surveyService.findSurveyById(item.getSurveyid());
				if(null == survey){
					throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
				}else if(2 == survey.getStatus()){
					throw new ErrorCodeException("PTL00310", ErrorCode.PTL00310);
				}else if(3 == survey.getStatus()){
					throw new ErrorCodeException("PTL00311", ErrorCode.PTL00311);
				}else{
					int count = 0;
					if(null != item.getQuestionid()){	//如果是问题选项则直接删除，否则删除问题及相关选项
						item.setIsvalid(Short.valueOf("0"));
						item.setRtime(new Date());
						count = (int) writeDao.update(SurveyQuestionItem.NAMESPACE, "updateById", item);
					}else{
						count = (int) writeDao.update(SurveyQuestionItem.NAMESPACE, "deleteForQuestion", item);
					}
					return count > 0 ? true : false;
				}
			}
		}else{
			throw new ErrorCodeException("PTL00305", ErrorCode.PTL00305);
		}
	}

	@Override
	@AccessLog(description="find all question", operateType=OperateType.Query, add2DB=Add2DB.Yes)
	public List<SurveyQuestionItem> findForQuestion(SurveyQuestionItem item)
			throws ErrorCodeException {
		List<SurveyQuestionItem> questions = readDao.query(SurveyQuestionItem.NAMESPACE,"queryAllQuestion", item);
		return questions;
	}

	@Override
	@AccessLog(description="find all question count", operateType=OperateType.Query, add2DB=Add2DB.Yes)
	public Integer getCountForQuestion(SurveyQuestionItem params)
			throws ErrorCodeException {
		return readDao.count(SurveyQuestionItem.NAMESPACE,"countQueryAllQuestion", params);
	}

	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="change survey question sort", operateType=OperateType.Update, add2DB=Add2DB.Yes)
	public Boolean changeSort(Integer id1, Integer id2)
			throws ErrorCodeException {
		if(null != id1 && null != id2){
			SurveyQuestionItem para = new SurveyQuestionItem();
			para.setId(id1);
			para.setIsvalid(Short.valueOf("1"));
			SurveyQuestionItem item1 = readDao.getOne(SurveyQuestionItem.NAMESPACE, "findById", para);
			if(null == item1){
				throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
			}
			//判断问卷状态是否可以排序
			SurveyItem survey =	surveyService.findSurveyById(item1.getSurveyid());
			if(2 == survey.getStatus()){
				throw new ErrorCodeException("PTL00312", ErrorCode.PTL00312);
			}else if(3 == survey.getStatus()){
				throw new ErrorCodeException("PTL00313", ErrorCode.PTL00313);
			}
			para.setId(id2);
			SurveyQuestionItem item2 = readDao.getOne(SurveyQuestionItem.NAMESPACE, "findById", para);
			if(null == item2){
				throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
			}
			//不是同一个问卷不能进行排序
			if(item1.getSurveyid() != item2.getSurveyid()){
				throw new ErrorCodeException("PTL00314", ErrorCode.PTL00314);
			}
			Short sort1= item1.getSortno();
			Short sort2= item2.getSortno();
			item1.setSortno(sort2);
			item2.setSortno(sort1);
			int count = 0;
			count = (int) readDao.update(SurveyQuestionItem.NAMESPACE, item1);
			if(count <=0){
				throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
			}
			count = (int) readDao.update(SurveyQuestionItem.NAMESPACE, item2);
			if(count <=0){
				throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
			}
			return count > 0 ? true : false;
		}else{
			throw new ErrorCodeException("PTL00305", ErrorCode.PTL00305);
		}
	
	}

	@Override
	@AccessLog(description="upate question", operateType=OperateType.Update, add2DB=Add2DB.Yes)
	public Boolean update(SurveyQuestionItem item) throws ErrorCodeException {
		if(null != item){
			int count = (int) readDao.update(SurveyQuestionItem.NAMESPACE, item);
			return count > 0 ? true : false;
		}
		return false;
	}

}
