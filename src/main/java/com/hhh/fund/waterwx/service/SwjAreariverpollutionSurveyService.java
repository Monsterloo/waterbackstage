package com.hhh.fund.waterwx.service;

import java.io.File;

import com.hhh.fund.waterwx.webmodel.AreaRiverpollutionSurveyListJsonBean;

public interface SwjAreariverpollutionSurveyService {

	public AreaRiverpollutionSurveyListJsonBean findBySubject(String subject);
	
	public AreaRiverpollutionSurveyListJsonBean findAllAreaRiverpollutionSurvey();
	
	public void getImport(File file , int tableIndex , String areaName , int row1 , int row2) throws Exception;
}
