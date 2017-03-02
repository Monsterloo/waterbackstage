package com.hhh.fund.waterwx.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hhh.weixin.util.QiyehaoConst;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjRivermasterboard;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.ListJsonBean;
import com.hhh.fund.waterwx.webmodel.RiverMasterBoardListJsonBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjEvaluationBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
import com.hhh.fund.waterwx.webmodel.SwjRivermasterboardBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjQuestionItemService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.service.SwjRiverMasterBoardService;
import com.hhh.fund.waterwx.service.SwjSignInService;
import com.hhh.fund.waterwx.service.SwjWxexportUserService;
import com.hhh.fund.waterwx.service.impl.SwjQuestionServiceImpl;
import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.fund.web.model.FileMeta;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.hhh.weixin.util.CommonUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Controller
@RequestMapping("/riverMasterBoard")
public class SwjRiverMasterBoardController {
	private final static String PAGE_SIZE="7";
	@Autowired
	private SwjQuestionService questionService;
	@Autowired
	private SwjAnswerService answerService;
	
	@Autowired
	private SwjQuestiontypeService typeService;
	
	@Autowired
	private SwjSignInService signInService;
	
	@Autowired
	private RiverService riverService;
	
	@Autowired
	private ResponsibilityService responsibilityService;
	
	@Autowired
	private SwjQuestionItemService questionItemService;
	
	@Autowired
	private SwjRiverMasterBoardService swjRiverMasterBoardService;
	
	
	
	@RequestMapping(value="/main")
	public String adminMain(){
		return "waterwx/riverMasterBoard";
	}
	
	@RequestMapping("/save")
	public void saveCompain(SwjRivermasterboard rivermasterboard,HttpServletResponse response,HttpServletRequest request){
		String returnStr = "RequestSuccessful0";
		try{
			
			swjRiverMasterBoardService.save(rivermasterboard);
			returnStr = "RequestSuccessful1";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outPrintResult(response, returnStr);
		}
	}
	
	@RequestMapping("/getRiverMasterBoard")
	public void getRiverMasterBoard(String area,HttpServletResponse response, HttpServletRequest request) {
		String returnStr = "RequestSuccessfulNull";
		String Userarea =String.valueOf(request.getSession().getAttribute("area"));
		try {
			RiverMasterBoardListJsonBean returnBean=null;
			if("null".equals(Userarea)){
				returnBean = swjRiverMasterBoardService.findAllRiverMasterBoard();
			}else{
				returnBean = swjRiverMasterBoardService.findByArea(area);
			}
			if (returnBean != null) {
				returnStr = "RequestSuccessful"+ JSONObject.fromObject(returnBean).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outPrintResult(response, returnStr);
		}
	}
	
	
	
	/**
	 * 输出文本
	 * @param response
	 * @param returnStr
	 */
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
