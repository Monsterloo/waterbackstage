package com.hhh.fund.waterwx.controller;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhh.fund.waterwx.service.SwjRiverpollutionSurveyService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.SwjRiverpollutionSurveyListJsonBean;

@Controller
@RequestMapping("/riverPollutionSurvey")
public class SwjRiverpollutionSurveyController {
	
	@Autowired
	private SwjRiverpollutionSurveyService swjRiverpollutionSurveyService;
	
	@RequestMapping("/main")
	public String adminMain() throws Exception{
		return "waterwx/riverPollutionSurvey";
	}
	
	@RequestMapping("/getRiverPollutionSurvey")
	public void getRiverPollutionSurvey(HttpServletResponse response){
		String returnStr = "RequestSuccessfulNull";
		try {
			SwjRiverpollutionSurveyListJsonBean returnBean = swjRiverpollutionSurveyService.findAllRiverpollutionSurvey();
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
