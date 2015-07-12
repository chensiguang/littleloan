package com.hexin.pettyLoan.portals.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.base.DaoHelper;
import com.hexin.pettyLoan.portals.model.SurveyAnswerItem;
import com.hexin.pettyLoan.portals.service.SurveyAnswerService;

@Service("surveyAnswerService")
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	@Override
	public List<SurveyAnswerItem> find(SurveyAnswerItem item) {
		List<SurveyAnswerItem> answers = readDao.query(SurveyAnswerItem.NAMESPACE, item);
		return answers;
	}

	@Override
	public Boolean deleteById(SurveyAnswerItem paras) {
		Integer retCount = (Integer)writeDao.delete(SurveyAnswerItem.NAMESPACE, paras);
		return retCount > 0;
	}

}
