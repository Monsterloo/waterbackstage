package com.hhh.fund.waterwx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.entity.OutfallPolluateResource;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.SwjOutfallPolluateResourceService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/outFallUpload")
public class OutFallUploadController extends ExcelOperator{

	private Map<String,String>dictMap = new HashMap<String,String>();
	
	private Map<String,String>riverMap = new HashMap<String,String>();
	
	
	@Autowired
	private ResponsibilityService responseService;
	
	@Autowired
	private SwjOutfallPolluateResourceService outfallService;
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") CommonsMultipartFile uploadExcel,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		
		try {
			InputStream in = uploadExcel.getInputStream();
			String fileName = uploadExcel.getFileItem().getName();
			
			List<SysUcenterDict> unitList= responseService.findDict("responsibilityUnit");
			changeToMap(unitList,dictMap);
			
			ServletContext sc = request.getServletContext();
			Map<String,Object>riversMap = (Map<String, Object>) sc.getAttribute(River.class.getName());
			changeMapKey(riversMap,riverMap);
			
			List beanList = getBeanFromExcel(in,fileName);
			
			
			if(outfallService.saveList(beanList)){
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			outPrintResult(response,flag);
		}
	}
	
	private void changeMapKey(Map<String, Object> sourceMap, Map<String, String> returnMap) {
		Set<String> set = sourceMap.keySet();
		
		for(String str:set){
			returnMap.put(((River)sourceMap.get(str)).getRiverName(), ((River)sourceMap.get(str)).getRiverCode());
		}
		
	}

	private void changeToMap(List<SysUcenterDict> list, Map<String, String> map) {
		for(SysUcenterDict dict:list){
			map.put(dict.getName(), dict.getCode());
		}
	}
	
	@Override
	public Object cellChangeToEntity(Row row1) {
		
		OutfallPolluateResourceBean resource = new OutfallPolluateResourceBean();
		resource.setRivername(row1.getCell(1).getStringCellValue());
		resource.setRivercode(riverMap.get(row1.getCell(1).getStringCellValue()));
		resource.setArea(row1.getCell(2).getStringCellValue());
		resource.setLeftorrightbank(row1.getCell(3).getStringCellValue());
		resource.setOutfalltype(row1.getCell(4).getStringCellValue());
		resource.setOutfallcode(row1.getCell(5).getStringCellValue());
		resource.setSecondaryunit(row1.getCell(6).getStringCellValue());
		resource.setStreetname(row1.getCell(7).getStringCellValue());
		resource.setStreetmanager(row1.getCell(8).getStringCellValue());
		resource.setVillage(row1.getCell(9).getStringCellValue());
		resource.setVillagemanager(row1.getCell(10).getStringCellValue());
		resource.setPosition(row1.getCell(11).getStringCellValue());
		resource.setCoordinate(row1.getCell(12).getStringCellValue());
		resource.setOutfallsize(row1.getCell(13).getStringCellValue());
		resource.setOutfallshape(row1.getCell(14).getStringCellValue());
		resource.setPolldescription(row1.getCell(15).getStringCellValue());
		
		
		resource.setRectificationmeasures(row1.getCell(16).getStringCellValue());
		resource.setDrainageTo(row1.getCell(17).getStringCellValue());
		resource.setTherectificationresponsibilityunit(StringUtils.isBlank(row1.getCell(18).getStringCellValue())?"-1":dictMap.get(row1.getCell(18).getStringCellValue()));
		resource.setTimeofcompletion(row1.getCell(19).getStringCellValue());
		resource.setRemark(row1.getCell(20).getStringCellValue());
		resource.setCreateTime(new Date());
		
		return resource;
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

}
