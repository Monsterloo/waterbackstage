package com.hhh.fund.waterwx.controller;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhh.fund.waterwx.service.SwjAreariverpollutionSurveyService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.AreaRiverpollutionSurveyListJsonBean;
import com.hhh.fund.waterwx.webmodel.SwjRiverpollutionSurveyListJsonBean;

@Controller
@RequestMapping("/areaRiverpollutionSurvey")
public class SwjAreariverpollutionSurveyController {
	
	@Autowired
	private SwjAreariverpollutionSurveyService swjAreariverpollutionSurveyService;
	
	@RequestMapping("/main")
	public String adminMain() throws Exception{
		return "waterwx/areaRiverpollutionSurvey";
	}
	
	@RequestMapping("/getAreaRiverPollutionSurvey")
	public void getRiverPollutionSurvey(HttpServletResponse response,@RequestParam(value="area")String subject){
		String returnStr = "RequestSuccessfulNull";
		try {
			AreaRiverpollutionSurveyListJsonBean returnBean = swjAreariverpollutionSurveyService.findBySubject(subject);
			if(returnBean != null){
				returnStr = "RequestSuccessful"+JSONObject.fromObject(returnBean).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			outPrintResult(response, returnStr);
		}
	}
	
	private void outPrintResult(HttpServletResponse response,String returnStr) {
		try {
			response.setContentType("text/plain; charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(returnStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeResponseWriter(response);
		}
	}
}
