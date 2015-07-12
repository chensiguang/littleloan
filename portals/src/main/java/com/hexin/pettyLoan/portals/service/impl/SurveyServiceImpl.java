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

/**
 * 问卷操作实现类
 * @author chenyongzhi
 *
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	@Resource(name="surveyQuestionService")
	SurveyQuestionService surveyQuestionService;
	
	@Resource(name="surveyAnswerService")
	SurveyAnswerService surveyAnswerService;
	
	@Override
	@AccessLog(description="insert survey", operateType=OperateType.Insert, add2DB=Add2DB.Yes)
	public SurveyItem insertSurvey(SurveyItem item) throws ErrorCodeException {
		//是否存在同名问卷
		if(null != item && !StringUtil.isEmpty(item.getName())){
			int count = readDao.count(SurveyItem.NAMESPACE, "countByName", item);
			if(count > 0){
				throw new ErrorCodeException("PTL00304", ErrorCode.PTL00304);
			}
		}
		Integer count = (Integer) writeDao.insert(SurveyItem.NAMESPACE, item);//使用唯一键约束
		return null != count ? item : null;
	}

	@Override
	@AccessLog(description="find survey by id", operateType=OperateType.Query)
	public SurveyItem findSurveyById(Integer id) throws ErrorCodeException {
		//查询问卷
		SurveyItem sItem = new SurveyItem();
		sItem.setId(id);
		sItem = readDao.getOne(SurveyItem.NAMESPACE, "findById", sItem);
		return sItem;
	}

	@Override
	@AccessLog(description="upate survey", operateType=OperateType.Update)
	public Boolean updateSurvey(SurveyItem item) throws ErrorCodeException {
		SurveyItem para = new SurveyItem();
		para.setId(item.getId());
		SurveyItem it = (SurveyItem) readDao.getOne(SurveyItem.NAMESPACE, "findById", para);
		if(null == it){
			throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
		} else if(it.getStatus() ==  2){
			throw new ErrorCodeException("PTL00307", ErrorCode.PTL00307);
		} else if(it.getStatus() ==  3){
			throw new ErrorCodeException("PTL00308", ErrorCode.PTL00308); 
		}else{
			it.setMtime(new Date());
			it.setName(item.getName());
			it.setDescription(item.getDescription());
			int count = (int) writeDao.update(SurveyItem.NAMESPACE, it);
			return count > 0 ? true : false;
		}
	}

	@Override
	public List<SurveyItem> getSurveyList(SurveyItem params)
			throws ErrorCodeException {
		return readDao.query(SurveyItem.NAMESPACE, params);
	}

	@Override
	public Integer getCount(SurveyItem params) throws ErrorCodeException {
		return readDao.count(SurveyItem.NAMESPACE, params);
	}

	@Override
	@AccessLog(description="query check survey", operateType=OperateType.Query)
	public List<SurveyItem> getSurveyListByCheckStatus(SurveyItem params)
			throws ErrorCodeException {
		return readDao.query(SurveyItem.NAMESPACE, "queryByCheckStatus", params);
	}
	
	@Override
	@AccessLog(description="query survey count", operateType=OperateType.Query)
	public Integer getCheckStatusCount(SurveyItem params)
			throws ErrorCodeException {
		return readDao.count(SurveyItem.NAMESPACE, "countByCheckStatus", params);
	}

	@Override
	@AccessLog(description="upate survey", operateType=OperateType.Update)
	public Boolean updateSurveyStatus(Integer id, Short status, Integer userId)
			throws ErrorCodeException {
		SurveyItem para = new SurveyItem();
		para.setId(id);
		SurveyItem item = (SurveyItem) readDao.getOne(SurveyItem.NAMESPACE, "findById", para);
		if(null == item){
			throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
		}else if(null == status){
			throw new ErrorCodeException("PTL00302", ErrorCode.PTL00302);
		}else if(item.getStatus() ==  1){
			throw new ErrorCodeException("PTL00303", ErrorCode.PTL00303);
		}else{
			if(3 == status){
				item.setApproveuserid(userId);
			}
			item.setMtime(new Date());
			item.setStatus(status);
			int count = (int) writeDao.update(SurveyItem.NAMESPACE, item);
			return count > 0 ? true : false;
		}
	}

	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	@AccessLog(description="delete survey", operateType=OperateType.Delete, add2DB=Add2DB.Yes)
	public Boolean deleteSurvey(SurveyItem item) throws ErrorCodeException {
		if(null != item && null != item.getId()){
			List<SurveyItem> its = readDao.query(SurveyItem.NAMESPACE, "findById", item);
			if(null == its){
				throw new ErrorCodeException("PTL00301", ErrorCode.PTL00301);
			}else{
				SurveyItem it = its.get(0);
				try{
					//更新问题列表
					SurveyQuestionItem qIt = new SurveyQuestionItem();
					qIt.setSurveyid(it.getId());
					List<SurveyQuestionItem> questions = surveyQuestionService.find(qIt);
					if(null != questions && questions.size() > 0){
						for(int i=0 ; i<questions.size(); i++){
							SurveyQuestionItem question = questions.get(i);
							question.setIsvalid(Short.valueOf("0"));
							question.setRtime(new Date());
							surveyQuestionService.update(question);
						}
					}
					//更新问卷
					it.setIsvalid(Short.valueOf("0"));
					it.setRtime(new Date());
					int count = (int) writeDao.update(SurveyItem.NAMESPACE, it);
					return count > 0 ? true : false;
				} catch(ErrorCodeException ex){
					throw ex;
				}
			}
		}else{
			throw new ErrorCodeException("PTL00305", ErrorCode.PTL00305);
		}
	}
}
