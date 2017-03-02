package com.hhh.fund.waterwx.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjAreariverpollutionSurveyDao;
import com.hhh.fund.waterwx.entity.SwjAreariverpollutionSurvey;
import com.hhh.fund.waterwx.service.SwjAreariverpollutionSurveyService;
import com.hhh.fund.waterwx.webmodel.AreaRiverpollutionSurveyListJsonBean;
import com.hhh.fund.waterwx.webmodel.SwjAreariverpollutionSurveyBean;

@Component
@Transactional
public class SwjAreariverpollutionSurveyServiceImpl implements
		SwjAreariverpollutionSurveyService {
	
	@Autowired
	private SwjAreariverpollutionSurveyDao swjAreariverpollutionSurveyDao; 
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * file 文件对象
	 * tableIndex 第几张表
	 * areaName 区名
	 * row1 开始行数
	 * row2 结束行数
	 * (输入的行数实际减一)
	 */
	@Override
	public void getImport(File file , int tableIndex , String areaName , int row1 , int row2) throws Exception{
        Workbook rwb=Workbook.getWorkbook(file);
        Sheet st=rwb.getSheet(tableIndex);//读取文件
        int clos = st.getColumns();//所有列
        int rows = st.getRows();//所有行
        
        
        System.out.println("clos" + clos + " rows:" + rows);  
        for (int i = row1; i < row2; i++) {  
            for (int j = 1; j < clos; j++) {  
               /* // 第一个是列数，第二个是行数  
                String subject = st.getCell(j++, i).getContents();// 默认最左边编号也算一列  
                // 所以这里得j++  
                String phon = st.getCell(j++, i).getContents();  
                System.out.println(" name:" + name + " phon:" + phon); */
            	SwjAreariverpollutionSurvey bean = new SwjAreariverpollutionSurvey();
            	bean.setSubject(areaName);
            	bean.setRiverIndex(st.getCell(j++, i).getContents());
            	bean.setRiverName(st.getCell(j++, i).getContents());
            	bean.setPollutionSourceNumber(toZero(st.getCell(j++, i).getContents()));
            	System.out.print(bean.getRiverIndex()+"  ");
            	
            	bean.setOneSubtotal(toZero(st.getCell(j++, i).getContents()));
            	bean.setOneEnvironment(toZero(st.getCell(j++, i).getContents()));
            	bean.setOneCityManagement(toZero(st.getCell(j++, i).getContents()));
            	bean.setOneFarming(toZero(st.getCell(j++, i).getContents()));
            	bean.setOneOthers(toZero(st.getCell(j++, i).getContents()));
            	System.out.print(bean.getOneOthers()+"  ");
            	
            	bean.setTwoSubtotal(toZero(st.getCell(j++, i).getContents()));
            	bean.setTwoEnvironment(toZero(st.getCell(j++, i).getContents()));
            	bean.setTwoCityManagement(toZero(st.getCell(j++, i).getContents()));
            	bean.setTwoFarming(toZero(st.getCell(j++, i).getContents()));
            	bean.setTwoOthers(toZero(st.getCell(j++, i).getContents()));
            	System.out.print(bean.getTwoOthers()+"  ");
            	
            	bean.setOutfallNumber(toZero(st.getCell(j++, i).getContents()));
            	bean.setOutfallSubtotal(toZero(st.getCell(j++, i).getContents()));
            	bean.setOutfallWaterSupplies(toZero(st.getCell(j++, i).getContents()));
            	bean.setOutfallWaterPurification(toZero(st.getCell(j++, i).getContents()));
            	bean.setOutfallOthers(toZero(st.getCell(j++, i).getContents()));
            	System.out.print(bean.getOutfallOthers()+"  ");
            	
            	bean.setRemarks(st.getCell(j++, i).getContents());
            	System.out.println(bean.getRemarks());
            	System.out.println(bean.toString());
            	swjAreariverpollutionSurveyDao.save(bean);
            }  
        } 
		
	}
	
	public int toZero(String str){
		if("".equals(str) || null == str){
			str = "0";
		}
		return Integer.parseInt(str);
	}

	@Override
	public AreaRiverpollutionSurveyListJsonBean findBySubject(String subject) {
		List<SwjAreariverpollutionSurvey> surveyList = swjAreariverpollutionSurveyDao.findBySubject(subject);
		AreaRiverpollutionSurveyListJsonBean jsonBean = null;
		if(surveyList != null){
			List<SwjAreariverpollutionSurveyBean> jsonList = new ArrayList<SwjAreariverpollutionSurveyBean>();
			for(SwjAreariverpollutionSurvey entity : surveyList){
				SwjAreariverpollutionSurveyBean bean = new SwjAreariverpollutionSurveyBean();
				BeanUtils.copyProperties(entity, bean);
				jsonList.add(bean);
			}
			jsonBean = new AreaRiverpollutionSurveyListJsonBean();
			jsonBean.setAreaRiverpollutionSurveyList(jsonList);
		}
		return jsonBean;
	}

	@Override
	public AreaRiverpollutionSurveyListJsonBean findAllAreaRiverpollutionSurvey() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
