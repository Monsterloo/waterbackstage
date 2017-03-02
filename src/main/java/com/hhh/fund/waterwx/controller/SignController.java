package com.hhh.fund.waterwx.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.service.SwjSignInService;
import com.hhh.fund.waterwx.service.impl.SwjQuestionServiceImpl;
import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.fund.web.model.FileMeta;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Controller
@RequestMapping("/sign")
public class SignController {
	private final static String PAGE_SIZE="7";
	@Autowired
	private SwjSignInService signInService;
	@Autowired
	private SwjQuestionService questionService;
	
	@RequestMapping(value="/main")
	public String type(){
		return "waterwx/sign";
	}
	/**
	 * 查询所有的巡河记录
	 * @param reachname 河道名称  grade 河道等级  area 行政区域
	 * */
	@RequestMapping(value="/searchSign")
	public @ResponseBody DataTablesResult<SwjWxsigninBean> searchSign(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize,String reachname,String grade,String area){
		int page = signInService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjWxsigninBean> records = signInService.findSignInByWeb(reachname, grade, area, pr);
		Integer count = null;
		List list = new ArrayList();
		DataTablesResult<SwjWxsigninBean> dtr = new DataTablesResult<SwjWxsigninBean>();
		if(records == null){
			dtr.setData(list);
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(1);
			dtr.setRecordsTotal(1);
		}else{
			dtr.setData(records.getContent());
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
			dtr.setRecordsTotal(records.getTotalPages());
		}
		return dtr;
	}
	/**
	 * 获取巡河记录
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody SwjWxsigninBean findById(String id){
		SwjWxsigninBean bean = signInService.findById(id);
		 List<SwjAttachment> list = questionService.getAttchId(id);
		 String[] strs = new String[list.size()];
		 int i=0;
		 for(SwjAttachment a : list){
			 strs[i] = a.getId();
			 i++;
		 }
		 bean.setAttachIds(strs);
		return bean;
	}
}
