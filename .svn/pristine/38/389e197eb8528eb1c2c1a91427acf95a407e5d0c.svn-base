package com.hhh.fund.waterwx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
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
import com.hhh.fund.waterwx.service.PublicSignsBoardInfoService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/publicSignBoardUpload")
public class SwjPublicSignBoardUploadController extends ExcelOperator{

	@Autowired
	private PublicSignsBoardInfoService publicSignsBoardInfoService;
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") CommonsMultipartFile uploadExcel,HttpServletRequest request,HttpServletResponse response){
		
		try {
			InputStream in = uploadExcel.getInputStream();
			Workbook rwb=Workbook.getWorkbook(in);
			Sheet st=rwb.getSheet(0);//读取文件
	        int rowCnt = st.getRows();//所有行
	        String area = (String) request.getSession().getAttribute("area")==null?"天河区":(String) request.getSession().getAttribute("area");
	        Map<String,String>mapOfKeyName = changeMapKey((Map<String, Object>) request.getServletContext().getAttribute(River.class.getName()));
	        
			for(int row=1;row<rowCnt;row++){
				PublicSignsBoardInfoBean bean = getPublicSignBoardInfoBean(row,st,mapOfKeyName);
				bean.setAreaName(area);
				publicSignsBoardInfoService.save(bean);
			}
			
			outPrintResult(response,true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private PublicSignsBoardInfoBean getPublicSignBoardInfoBean(int row, Sheet st, Map<String, String> mapOfKeyName) {

		PublicSignsBoardInfoBean bean = new PublicSignsBoardInfoBean();
//		bean.setId(st.getCell(0,row).getContents());
		
		//TODO
		bean.setRiverCode(mapOfKeyName.get(st.getCell(1,row).getContents()));

		bean.setRiverName(st.getCell(1,row).getContents());
		bean.setLeftOrRightBank(st.getCell(2,row).getContents());
		bean.setPartName(st.getCell(3,row).getContents());
		bean.setPartNameLength(st.getCell(4,row).getContents());
		bean.setBoardPosition(st.getCell(5,row).getContents());
		bean.setDistMgrName(st.getCell(6,row).getContents());
		bean.setDistMgrOrg(st.getCell(7,row).getContents());
		bean.setDistMgrPosition(st.getCell(8,row).getContents());
		
		//TODO
//		bean.setDistMgrTel();
		
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
		
		
		bean.setBoardCode(st.getCell(21,row).getContents());
		bean.setRemark(st.getCell(22,row).getContents());
		bean.setCreateTime(new Date());
		return  bean;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
