package com.hhh.fund.waterwx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhh.fund.waterwx.entity.SwjRivermastercensus;
import com.hhh.fund.waterwx.service.SwjRiverMasterCensusService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.RiverMasterCensusListJsonBean;

@Controller
@RequestMapping("/riverMasterCensus")
public class SwjRiverMasterCensusController {
	
	@Autowired
	private SwjRiverMasterCensusService swjRiverMasterCensusService;
	
	@RequestMapping("/main")
	public String adminMain(){
		return "waterwx/riverMasterCensus";
	}
	
	@RequestMapping("/getRiverMasterCensus")
	public void getRiverMasterCensus(String area, HttpServletRequest request, HttpServletResponse response){
		String returnStr = "RequestSuccessfulNull";
		String userArea = String.valueOf(request.getSession().getAttribute("area"));
		try {
			RiverMasterCensusListJsonBean returnBean = new RiverMasterCensusListJsonBean(); 
			if("null".equals(userArea)){
				returnBean = swjRiverMasterCensusService.findAllRiverMasterBoard();
			}else{
				returnBean = swjRiverMasterCensusService.findByArea(area);
			}
			if(returnBean != null){
				returnStr = "RequestSuccessful"+JSONObject.fromObject(returnBean).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			outPrintResult(response, returnStr);
		}
	}
	
	@RequestMapping("/save")
	public void saveComponent(SwjRivermastercensus swjRivermastercensus, HttpServletResponse response){
		String returnStr = "RequestSuccessful0";
		try {
			swjRiverMasterCensusService.save(swjRivermastercensus);
			returnStr = "RequestSuccessful1";
		} catch (Exception e) {
			// TODO: handle exception
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
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeResponseWriter(response);
		}
	}
}
