package com.hhh.fund.waterwx.service;

import com.hhh.fund.waterwx.entity.SwjRiverpollutionSurvey;
import com.hhh.fund.waterwx.webmodel.SwjRiverpollutionSurveyListJsonBean;

public interface SwjRiverpollutionSurveyService {
	
	public void save(SwjRiverpollutionSurvey swjRiverpollutionSurvey);
	
	public SwjRiverpollutionSurveyListJsonBean findAllRiverpollutionSurvey();
	
	public void getImport() throws Exception;
}
