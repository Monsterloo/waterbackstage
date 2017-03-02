package com.hhh.fund.waterwx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/response")
public class ResponseUploadController extends ExcelOperator{

	
	private Map<String,String>mapOfKeyName = new HashMap<String,String>();
	
	@Autowired
	private ResponsibilityService respService;
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") CommonsMultipartFile uploadExcel,HttpServletRequest request,HttpServletResponse response){
		
//		System.out.println(request.getParameter("file"));
		boolean flag = false;
		try {
			
			InputStream in = uploadExcel.getInputStream();
			String fileName = uploadExcel.getFileItem().getName();
			
//			Workbook rwb=Workbook.getWorkbook(in);
//			Sheet st=rwb.getSheet(0);//读取文件
//	        int rowCnt = st.getRows();//所有行
	        String area = (String) request.getSession().getAttribute("area")==null?"南沙区":(String) request.getSession().getAttribute("area");
	       mapOfKeyName = changeMapKey((Map<String, Object>) request.getServletContext().getAttribute(River.class.getName()));
	        
	        
	        List beanList = getBeanFromExcel(in,fileName);
	        
	        for(Object obj:beanList){
	        	ResponsibilityBean bean = (ResponsibilityBean) obj;
	        	bean.setAreaName(area);
	        }
	        
			/*for(int row=1;row<rowCnt;row++){
				ResponsibilityBean bean = getResponsibilityBean(row,st);
				bean.setAreaName(area);
				respService.save(bean);
			}*/
			if(respService.saveList(beanList)){
				flag = true;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			outPrintResult(response,flag);
		}
	}

	
	private Map<String, String> changeMapKey(Map<String, Object> riverMap) {
		Map<String, String> map = new HashMap<String, String>();
		
		Set<String>sets = riverMap.keySet();
		for(String item:sets ){
			River river = (River) riverMap.get(item);
			map.put(river.getRiverName(), item);
		}
		
		return map;
	}


	/**
	 * 输出文本
	 * @param response
	 * @param returnStr
	 */
	private void outPrintResult(HttpServletResponse response,Object returnStr) {
		try {
			response.setContentType("text/plain; charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeResponseWriter(response);
		}
	}
	
	private ResponsibilityBean getResponsibilityBean(int row,Sheet st) {
		ResponsibilityBean bean = new ResponsibilityBean();
					
		bean.setRiverName(st.getCell(1,row).getContents());
		bean.setRiverCode(mapOfKeyName.get(st.getCell(1,row).getContents()));
		bean.setPartName(st.getCell(2,row).getContents());
		bean.setLeftRight(st.getCell(3,row).getContents());
		bean.setLeftRightLength(st.getCell(4,row).getContents());
		
		
		bean.setDistMgrName(st.getCell(5,row).getContents());
		bean.setDistMgrOrg(st.getCell(6,row).getContents());
		bean.setDistMgrPosition(st.getCell(7,row).getContents());
		bean.setDistMgrTel(st.getCell(8,row).getContents());
		
		
		bean.setStreetMgrName(st.getCell(9,row).getContents());
		bean.setStreetMgrOrg(st.getCell(10,row).getContents());
		bean.setStreetMgrPosition(st.getCell(11,row).getContents());
		bean.setStreetMgrTel(st.getCell(12,row).getContents());
		
		bean.setVillageMgrName(st.getCell(13,row).getContents());
		bean.setVillageMgrOrg(st.getCell(14,row).getContents());
		bean.setVillageMgrPosition(st.getCell(15,row).getContents());
		bean.setVillageMgrTel(st.getCell(16,row).getContents());
		
		bean.setManageMgrName(st.getCell(17,row).getContents());
		bean.setManageMgrOrg(st.getCell(18,row).getContents());
		bean.setManageMgrPosition(st.getCell(19,row).getContents());
		bean.setManageMgrTel(st.getCell(20,row).getContents());
		
		bean.setRemark(st.getCell(21,row).getContents());
		
		return bean;
	}


	@Override
	public Object cellChangeToEntity(Row row1) {
		ResponsibilityBean bean = new ResponsibilityBean();
		
		bean.setRiverName(row1.getCell(1).getStringCellValue());
		bean.setRiverCode(mapOfKeyName.get(row1.getCell(1).getStringCellValue()));
		bean.setPartName(row1.getCell(2).getStringCellValue());
		bean.setLeftRight(row1.getCell(3).getStringCellValue());
		bean.setLeftRightLength(row1.getCell(4).getStringCellValue());
		
		
		bean.setDistMgrName(row1.getCell(5).getStringCellValue());
		bean.setDistMgrOrg(row1.getCell(6).getStringCellValue());
		bean.setDistMgrPosition(row1.getCell(7).getStringCellValue());
		bean.setDistMgrTel(row1.getCell(8).getStringCellValue());
		
		
		bean.setStreetMgrName(row1.getCell(9).getStringCellValue());
		bean.setStreetMgrOrg(row1.getCell(10).getStringCellValue());
		bean.setStreetMgrPosition(row1.getCell(11).getStringCellValue());
		bean.setStreetMgrTel(row1.getCell(12).getStringCellValue());
		
		bean.setVillageMgrName(row1.getCell(13).getStringCellValue());
		bean.setVillageMgrOrg(row1.getCell(14).getStringCellValue());
		bean.setVillageMgrPosition(row1.getCell(15).getStringCellValue());
		bean.setVillageMgrTel(row1.getCell(16).getStringCellValue());
		
		bean.setManageMgrName(row1.getCell(17).getStringCellValue());
		bean.setManageMgrOrg(row1.getCell(18).getStringCellValue());
		bean.setManageMgrPosition(row1.getCell(19).getStringCellValue());
		bean.setManageMgrTel(row1.getCell(20).getStringCellValue());
		
		bean.setRemark(row1.getCell(21).getStringCellValue());
		
		System.out.println(bean);
		return bean;
	}
	
}
