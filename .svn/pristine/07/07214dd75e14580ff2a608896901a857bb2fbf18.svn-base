package com.hhh.fund.util;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;

import com.hhh.fund.waterwx.dao.SwjRiverpollutionSurveyDao;
import com.hhh.fund.waterwx.entity.SwjRiverpollutionSurvey;


public class ImportUtil {

	@Autowired
	private SwjRiverpollutionSurveyDao swjRiverpollutionSurveyDao;
	
	/* public static void main(String[] args) throws Exception {  
	        File file = new File("C:\\Users\\3hljl\\Desktop\\广州市汇总表.xls"); 
	        Workbook rwb=Workbook.getWorkbook(file);
	        Sheet st=rwb.getSheet(0);//读取文件
	        int clos = st.getColumns();//所有列
	        int rows = st.getRows();//所有行
	        
	        
	        System.out.println("clos" + clos + " rows:" + rows);  
	        for (int i = 5; i < 57; i++) {  
	            for (int j = 1; j < clos; j++) {  
	                // 第一个是列数，第二个是行数  
	                String subject = st.getCell(j++, i).getContents();// 默认最左边编号也算一列  
	                // 所以这里得j++  
	                String phon = st.getCell(j++, i).getContents();  
	                System.out.println(" name:" + name + " phon:" + phon); 
	            	SwjRiverpollutionSurvey bean = new SwjRiverpollutionSurvey();
	            	bean.setSubject(st.getCell(j++, i).getContents());
	            	bean.setAllRiverNumber(st.getCell(j++, i).getContents());
	            	bean.setRiverNumber(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setPollutionSourceNumber(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setOneSubtotal(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneEnvironment(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneCityManagement(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneFarming(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneOthers(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setTwoSubtotal(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoEnvironment(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoCityManagement(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoFarming(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoOthers(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setOutfallNumber(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallSubtotal(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallWaterSupplies(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallWaterPurification(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallOthers(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setRemarks(st.getCell(j++, i).getContents());
	            	bean.setPrincipal(st.getCell(j++, i).getContents());
	            	bean.setContacts(st.getCell(j++, i).getContents());
	            	System.out.println(bean.toString());
	            	swjRiverpollutionSurveyDao.save(bean);
	            }  
	            System.out.println();
	        } 
	    }*/
	 
	 public void getImport() throws Exception{
		 	/*Workbook rwb = null;
			try {
				rwb = Workbook.getWorkbook(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Sheet st=rwb.getSheet(0);//读取文件
	        int clos = st.getColumns();//所有列
	        int rows = st.getRows();//所有行
	        
	        
	        System.out.println("clos" + clos + " rows:" + rows);  
	        for (int i = 1; i < rows; i++) {  
	            for (int j = 0; j < clos; j++) {  
	                // 第一个是列数，第二个是行数  
	                String name = st.getCell(j++, i).getContents();// 默认最左边编号也算一列  
	                // 所以这里得j++  
	                String phon = st.getCell(j++, i).getContents();  
	                System.out.println(" name:" + name + " phon:" + phon); 
	                //这里可以分装一需要保存的实体
	            }  
	        }*/ 
		 File file = new File("C:\\Users\\3hljl\\Desktop\\广州市汇总表.xls"); 
	        Workbook rwb=Workbook.getWorkbook(file);
	        Sheet st=rwb.getSheet(0);//读取文件
	        int clos = st.getColumns();//所有列
	        int rows = st.getRows();//所有行
	        
	        
	        System.out.println("clos" + clos + " rows:" + rows);  
	        for (int i = 5; i < 57; i++) {  
	            for (int j = 1; j < clos; j++) {  
	               /* // 第一个是列数，第二个是行数  
	                String subject = st.getCell(j++, i).getContents();// 默认最左边编号也算一列  
	                // 所以这里得j++  
	                String phon = st.getCell(j++, i).getContents();  
	                System.out.println(" name:" + name + " phon:" + phon); */
	            	SwjRiverpollutionSurvey bean = new SwjRiverpollutionSurvey();
	            	bean.setSubject(st.getCell(j++, i).getContents());
	            	bean.setAllRiverNumber(st.getCell(j++, i).getContents());
	            	bean.setRiverNumber(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setPollutionSourceNumber(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setOneSubtotal(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneEnvironment(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneCityManagement(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneFarming(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOneOthers(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setTwoSubtotal(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoEnvironment(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoCityManagement(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoFarming(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setTwoOthers(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setOutfallNumber(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallSubtotal(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallWaterSupplies(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallWaterPurification(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	bean.setOutfallOthers(Integer.parseInt(st.getCell(j++, i).getContents()));
	            	
	            	bean.setRemarks(st.getCell(j++, i).getContents());
	            	bean.setPrincipal(st.getCell(j++, i).getContents());
	            	bean.setContacts(st.getCell(j++, i).getContents());
	            	System.out.println(bean.toString());
	            	swjRiverpollutionSurveyDao.save(bean);
	            }  
	            System.out.println();
	        } 
	 }
}
