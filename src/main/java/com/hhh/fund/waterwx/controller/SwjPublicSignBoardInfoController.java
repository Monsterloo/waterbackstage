package com.hhh.fund.waterwx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhh.fund.waterwx.service.PublicSignsBoardInfoService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.util.JacksonUtils;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;

@Controller
@RequestMapping("/publicSignBoardInfo")
public class SwjPublicSignBoardInfoController {
	
	@Autowired
	private PublicSignsBoardInfoService publicSignsBoardInfoService;
	
	@RequestMapping("/toBoardInfoList")
	public String toBoardInfoList(){
		return "waterwx/boardInfoList1";
	}
	
	@RequestMapping("/toBoardInfoStatistics")
	public String toBoardInfoStatistics(){
		return "waterwx/boardInfoStatistics";
	}
	
	
	@RequestMapping("/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response){
		try {
		    
			ServletContext sc = request.getServletContext();
			String realPath = sc.getRealPath("/template")+File.separator+"public_signs_board_info.xls";
			System.out.println(realPath);
			InputStream in = new FileInputStream(realPath);
			
			response.addHeader("Content-Disposition", "attachment;filename=" + new String("public_signs_board_info.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Length", "" + in.available());
			OutputStream os = response.getOutputStream();
			byte[]buff = new byte[1024];
			int i = 0;
			while((i=in.read(buff))!=-1){
				os.write(buff);// 输出文件
				os.flush();
			}
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getBoardInfoList")
	public void getBoardInfoList(HttpServletResponse response,@RequestParam(value="area")String area){
		String returnStr = "RequestSuccessfulNull";
		try {
			List<PublicSignsBoardInfoBean> beanList = publicSignsBoardInfoService.findByAreaName(area);
			if(beanList != null){
				returnStr = "RequestSuccessful"+JacksonUtils.list2josn(beanList);
			}
			System.out.println(returnStr.toString());
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
