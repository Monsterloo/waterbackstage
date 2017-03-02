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
import com.hhh.fund.waterwx.entity.PollutantSource;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.SwjOutfallPolluateResourceService;
import com.hhh.fund.waterwx.service.SwjPollutantSourceService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;

@Controller
@RequestMapping("/pollResourceUpload")
public class PollResourceUploadController extends ExcelOperator{

	private Map<String,String>dictMap = new HashMap<String,String>();
	
	private Map<String,String>riverMap = new HashMap<String,String>();
	
	@Autowired
	private ResponsibilityService responseService;
	@Autowired
	private SwjPollutantSourceService pollSourceService;
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") CommonsMultipartFile uploadExcel,
			HttpServletRequest request,
			HttpServletResponse response){
		
		List<SysUcenterDict> list= responseService.findDict("pollResType");
		List<SysUcenterDict> unitList= responseService.findDict("responsibilityUnit");
		
		changeToMap(list,dictMap);
		changeToMap(unitList,dictMap);
		
		ServletContext sc = request.getServletContext();
		Map<String,Object>riversMap = (Map<String, Object>) sc.getAttribute(River.class.getName());
		changeMapKey(riversMap,riverMap);
		
		boolean flag = false;
		
		try {
			InputStream in = uploadExcel.getInputStream();
			String fileName = uploadExcel.getFileItem().getName();
			List beanList = getBeanFromExcel(in,fileName);
			pollSourceService.saveList(beanList);
			flag = true;
			
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
    	PollutantSourceBean source = new PollutantSourceBean();
		source.setRivername(row1.getCell(1).getStringCellValue());
		source.setRivercode(riverMap.get(row1.getCell(1).getStringCellValue()));
		source.setArea(row1.getCell(2).getStringCellValue());
		source.setPollsourcename(row1.getCell(3).getStringCellValue());
		source.setStreetname(row1.getCell(4).getStringCellValue());
		source.setStreetmanager(row1.getCell(5).getStringCellValue());
		source.setVillage(row1.getCell(6).getStringCellValue());
		source.setVillagemanager(row1.getCell(7).getStringCellValue());
		
		//TODO污染源类型   数据字典
		source.setPollsourcetype(StringUtils.isBlank(row1.getCell(8).getStringCellValue())?"-1":dictMap.get(row1.getCell(8).getStringCellValue()));
		
		source.setOutfalltype(row1.getCell(9).getStringCellValue());
		source.setOutfallcode(row1.getCell(10).getStringCellValue());
		source.setPosition(row1.getCell(11).getStringCellValue());
		source.setCoordinate(row1.getCell(12).getStringCellValue());
		source.setPolldescription(row1.getCell(13).getStringCellValue());
		source.setDrainageto(row1.getCell(14).getStringCellValue());
		source.setPolldischarginglicense(row1.getCell(15).getStringCellValue());
		source.setDrainaglicense(row1.getCell(16).getStringCellValue());
		source.setHasmeasures(row1.getCell(17).getStringCellValue());
		source.setRectificationmeasures(row1.getCell(18).getStringCellValue());
		source.setTherectificationresponsibilityunit(StringUtils.isBlank(row1.getCell(19).getStringCellValue())?"-1":dictMap.get(row1.getCell(19).getStringCellValue()));
		source.setTimeofcompletion(row1.getCell(20).getStringCellValue());
		source.setIndustryoragriculture(row1.getCell(21).getStringCellValue());
		source.setRemark(row1.getCell(22).getStringCellValue());
		source.setCreateTime(new Date());
		return source;
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

	private Map<String, String> changeMapKey(Map<String, Object> riverMap) {
		Map<String, String> map = new HashMap<String, String>();
		
		Set<String>sets = riverMap.keySet();
		for(String item:sets ){
			River river = (River) riverMap.get(item);
			map.put(river.getRiverName(), item);
		}
		
		return map;
	}

}
