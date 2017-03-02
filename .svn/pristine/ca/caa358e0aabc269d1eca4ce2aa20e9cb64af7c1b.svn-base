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
		
		boolean flag = false;
		try {
			
			InputStream in = uploadExcel.getInputStream();
			String fileName = uploadExcel.getFileItem().getName();
			
	        String area = (String) request.getSession().getAttribute("area")==null?"南沙区":(String) request.getSession().getAttribute("area");
	        mapOfKeyName = changeMapKey((Map<String, Object>) request.getServletContext().getAttribute(River.class.getName()));
	        List beanList = getBeanFromExcel(in,fileName);
	        for(Object obj:beanList){
	        	ResponsibilityBean bean = (ResponsibilityBean) obj;
	        	bean.setAreaName(area);
	        }
			respService.saveList(beanList);
			flag = true;
			
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
		
		return bean;
	}
	
}
