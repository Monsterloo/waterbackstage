package com.hhh.fund.waterwx;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhh.fund.config.AppContext;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAreariverpollutionSurvey;
import com.hhh.fund.waterwx.service.SwjAreariverpollutionSurveyService;

import jxl.Sheet;
import jxl.Workbook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContext.class)
public class SwjAreariverpollutionSurveyTest {

	@Autowired
	SwjAreariverpollutionSurveyService swjAreariverpollutionSurveyService;
	
	@Test
	public void test1() throws Exception{
		File file = new File("C:\\Users\\3hljl\\Desktop\\广州市汇总表.xls");
		swjAreariverpollutionSurveyService.getImport(file , 4,"白云区",8,48);
	}
	
}
