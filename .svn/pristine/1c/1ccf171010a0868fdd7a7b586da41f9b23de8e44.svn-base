package com.hhh.fund.waterwx.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.hhh.weixin.util.QiyehaoConst;
import com.hhh.fund.util.TemplateExcelUtil;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.webmodel.ListJsonBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjQuestionItemService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.service.SwjSignInService;
import com.hhh.fund.waterwx.service.SwjWxexportUserService;
import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.hhh.weixin.util.CommonUtil;


@Controller
@RequestMapping("/waterwx")
public class SwjController {
	private final static String PAGE_SIZE="7";
	
	private final static String pathTemplace="/ceshi.xls";
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
	private SwjWxexportUserService exportUserService;
	
	@Autowired
	private SwjQuestionItemService questionItemService;
	
	@RequestMapping(value="/main")
	public String adminMain(){
		return "waterwx/questionList";
	}
	
	@RequestMapping(value="/allQuestion")
	public String allQuestion(){
		return "waterwx/questionList";
	}
	
	@RequestMapping(value="/exportuser")
	public String exportUser(){
		return "waterwx/exportuserList";
	}
	
	@RequestMapping(value="/insideNotice")
	public String insideNotice(){
		return "waterwx/insideNotice";
	}
	
	@RequestMapping(value="/main1")
	public String main1(int menu,Model model){
		Subject subject = SecurityUtils.getSubject();
		if(!subject.hasRole("streetRole") && !subject.hasRole("villageRole")){
			model.addAttribute("user",1);//区级以上用户，可以有完结功能
	    }
		model.addAttribute("menu",menu);
		return "waterwx/questionList1";
	}
	
	@RequestMapping(value="/monitor")
	public String monitor(int menu,Model model){
		Subject subject = SecurityUtils.getSubject();
		if(!subject.hasRole("streetRole") && !subject.hasRole("villageRole")){
			model.addAttribute("user",1);//区级以上用户，可以有完结功能
	    }
		model.addAttribute("menu",menu);
		return "waterwx/monitorComplaintList";
	}
	@RequestMapping(value="/charts")
	public String charts(){
		return "waterwx/waterEchar";
	}
	@RequestMapping(value="/type")
	public String type(){
		return "waterwx/questiontypeList";
	}
	@RequestMapping(value="/map")
	public String map(){
		return "waterwx/map";
	}
	@RequestMapping(value="/searchquestion")
	public String searchquestion(){
		return "weixin/searchquestion";
	}
	@RequestMapping(value="/searchsignin")
	public String searchsignin(){
		return "weixin/searchsignin";
	}
	@RequestMapping(value="/searchrivers")
	public String searchrivers(){
		return "weixin/searchrivers";
	}
	@RequestMapping(value="/wxQuestionwaterEchar")
	public String wxQuestionwaterEchar(){
		return "waterwx/wxQuestionwaterEchar";
	}
	@RequestMapping(value="/wxComplainEchar")
	public String wxComplainEchar(){
		return "waterwx/wxComplainEchar";
	}
	@RequestMapping(value="/wxAreaQuestionEchar")
	public String wxAreaQuestionEchar(){
		return "waterwx/wxAreaQuestionEchar";
	}
	@RequestMapping(value="/signin" )
	public String sign(Model mode, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String token = CommonUtil.getToken();
		String ticket = CommonUtil.getTicket();
		Map<String, String> map=CommonUtil.getSignature(QiyehaoConst.CorpID, token, ticket, url);
		for (String key : map.keySet()) {
			mode.addAttribute(key, map.get(key));
		}
		/*
		/*String url = request.getRequestURL().toString();
		Map<String, String> map = JsSDKConfig.getSignature(url);
		for (String key : map.keySet()) {
			mode.addAttribute(key, map.get(key));
		}*/
		return "weixin/signin";
	}
	/**
	 * 查询投诉处理监控
	 * @param bean
	 * @param draw
	 * @param start
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	
	@RequestMapping(value="/searchQuestionItemAll")
	public @ResponseBody DataTablesResult<SwjQuestionBean> quesionItemlist(SwjQuestionBean bean,int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize,String startTime,String endTime){
		Subject subject = SecurityUtils.getSubject();
		UserBean user = ShiroUtils.getUser();
		if (subject.hasRole("areaRole")) {//区级河长  
			System.out.println("1234");
			String username = user.getDisplayName();
			bean.setAreaPersonName(username);
			System.out.println(username+"66666");
	    }else if(subject.hasRole("streetRole")){
	    	String name = user.getDisplayName();
	    	bean.setStreetPersonName(name);
	    }else if(subject.hasRole("villageRole")){
	    	String name = user.getDisplayName();
	    	bean.setVillagePersonName(name);
	    }
		int page = questionService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjQuestionBean> records = null;
		System.out.println(bean.getStatus()+"00000000");
			String code = bean.getCode();
			String type = bean.getQuestiontype();
			int status = bean.getStatus();
			String iscross = bean.getIscross();
			String river = bean.getReachname();
			String coast = bean.getLeftRight();
			String reach = bean.getPartName();
			String area = bean.getArea();
			String areaname = bean.getAreaPersonName();
			String streetname = bean.getStreetPersonName();
			String villagename = bean.getVillagePersonName();
			String importance = bean.getImportance();
			System.out.println(startTime+"<<<<<"+endTime);
			System.out.println(code+","+type+","+status+","+river+","+coast+","+reach+","+area+","+areaname+","+streetname+","+villagename+","+importance);
			records = questionService.findBySearch(code,type,status,river,coast,reach,startTime,endTime,area,areaname,streetname,villagename,importance,iscross,pr);
			Integer count = null;
		List list = new ArrayList();
		DataTablesResult<SwjQuestionBean> dtr = new DataTablesResult<SwjQuestionBean>();
		if(records == null){
			dtr.setData(list);
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(1);
			dtr.setRecordsTotal(1);
		}else{
			System.out.println("7890");
			dtr.setData(records.getContent());
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
			dtr.setRecordsTotal(records.getTotalPages());
		}
		return dtr;
	}
	
	@RequestMapping(value="/searchAll")
	public @ResponseBody DataTablesResult<SwjQuestionBean> list(SwjQuestionBean bean,int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize,String startTime,String endTime){
		Subject subject = SecurityUtils.getSubject();
		UserBean user = ShiroUtils.getUser();
		if (subject.hasRole("areaRole")) {//区级河长  
			String username = user.getDisplayName();
			bean.setAreaPersonName(username);
	    }else if(subject.hasRole("streetRole")){
	    	String name = user.getDisplayName();
	    	bean.setStreetPersonName(name);
	    }else if(subject.hasRole("villageRole")){
	    	String name = user.getDisplayName();
	    	bean.setVillagePersonName(name);
	    }
		int page = questionService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjQuestionBean> records = null;
			String code = bean.getCode();
			String type = bean.getQuestiontype();
			int status = bean.getStatus();
			String iscross = bean.getIscross();
			String river = bean.getReachname();
			String coast = bean.getLeftRight();
			String reach = bean.getPartName();
			String area = bean.getArea();
			String areaname = bean.getAreaPersonName();
			String streetname = bean.getStreetPersonName();
			String villagename = bean.getVillagePersonName();
			String importance = bean.getImportance();
			records = questionService.findBySearch(code,type,status,river,coast,reach,startTime,endTime,area,areaname,streetname,villagename,importance,iscross,pr);
			Integer count = null;
		List list = new ArrayList();
		DataTablesResult<SwjQuestionBean> dtr = new DataTablesResult<SwjQuestionBean>();
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
	
	@RequestMapping(value="/searchAnswer")
	public @ResponseBody DataTablesResult<SwjAnswerBean> answerList(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize,String questionId){
		int page = answerService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjAnswerBean> records = answerService.findAnswerByQid(questionId,pr);
		Integer count = null;
		DataTablesResult<SwjAnswerBean> dtr = new DataTablesResult<SwjAnswerBean>();
		List list = new ArrayList();
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
	 * 查询问题
	 * 
	 * */
	@RequestMapping(value="findQuestionByWx")
	public @ResponseBody List<SwjQuestionBean> findQuestionByWx(String code,String reachname,String qtype,String startTime,String endTime,String person) {
		List<SwjQuestionBean> list = questionService.findQuestion(code,reachname,qtype,startTime,endTime,person);
		return list;
	}
	
	/**
	 * 查询签到
	 * 
	 * */
	@RequestMapping(value="findSignInByWx")
	public @ResponseBody List<SwjWxsigninBean> findSignInByWx(String reachname,String grade,String area) {
		List<SwjWxsigninBean> list = signInService.findSignIn(reachname,grade,area);
		return list;
	}
	
	
	/**
	 * c查询一条回复
	 * @param id 问题id
	 * */
	@RequestMapping(value="/findAnswer")
	public @ResponseBody String findAnswer(String id1){
		SwjAnswerBean bean = answerService.findById(id1);
		UserBean user = ShiroUtils.getUser();
		String username = user.getDisplayName();
		String answerUser = bean.getUsername();
		System.out.println(username.equals(answerUser));
		if(username.equals(answerUser)){
			return "succ";
		}else{
			return "fail";
		}
	}
	/**
	 * 删除回复
	 * */
	@RequestMapping(value="/deleteAnswer")
	public @ResponseBody String deleteAnswer(String id){
		answerService.deleteAnswer(id);
		return "succ";
	}
	/**
	 * 保存回复
	 * */
	@RequestMapping(value="/answer")
	public String answer(String qId,String con,MultipartHttpServletRequest request,Model model){
		SwjAnswerBean bean = new SwjAnswerBean();
		bean.setAnswercontent(con);
		System.out.println("77777"+qId);
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		bean.setAnswerDate(new Date());
		bean.setQuestionId(qId);
		Subject subject = SecurityUtils.getSubject();
		UserBean user = ShiroUtils.getUser();
		bean.setUsername(user.getDisplayName());
		String answerId = answerService.saveAnswer(bean);
		questionService.updateStatus(qId, 0);
		Iterator<String> itr =  request.getFileNames();
		System.out.println(itr.hasNext()+"666");
        MultipartFile mpf = null;
        //2.获得其中一个文件
        while(itr.hasNext()){
        	SwjAttachment attch = new SwjAttachment();
            mpf = request.getFile(itr.next()); 
            String name = mpf.getOriginalFilename();
            if(name == null){
            	return "文件上传有误!";
            }
            attch.setAnswerId(answerId);
            try {
				attch.setFile(mpf.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String anserid = attch.getAnswerId();
            System.out.println(anserid+"123");
            questionService.saveAttachment(attch);
 		         //判断文件是否存在mongo中
        }
        model.addAttribute("menu", 2);
        return "waterwx/questionList1";
	}
	/**
	 * 保存回复
	 * */
	//@RequestMapping(value="/saveAnswer")
	public @ResponseBody String saveAnswer(String qId,String content,MultipartHttpServletRequest request){
		SwjAnswerBean bean = new SwjAnswerBean();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = ShiroUtils.getUser();
		if (subject.hasRole("areaRole")) {//区级河长  
			String username = user.getDisplayName();
			bean.setUsername(username);
			System.out.println(username+"66666");
	    }else if(subject.hasRole("streetRole")){
	    	String name = user.getDisplayName();
	    	bean.setUsername(name);
	    }else if(subject.hasRole("villageRole")){
	    	String name = user.getDisplayName();
	    	bean.setUsername(name);
	    }
		bean.setAnswercontent(content);
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		bean.setAnswerDate(new Date());
		bean.setQuestionId(qId);
		answerService.saveAnswer(bean);
		questionService.updateStatus(qId, 0);
		Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf = null;
        //2.获得其中一个文件
        while(itr.hasNext()){
        	SwjAttachment attch = new SwjAttachment();
            mpf = request.getFile(itr.next()); 
            String name = mpf.getOriginalFilename();
            if(name == null){
       		 return "文件上传有误!";
            }
            attch.setAnswerId(bean.getId());
            try {
				attch.setFile(mpf.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            questionService.saveAttachment(attch);
 		         //判断文件是否存在mongo中
        }
		return "succ";
	}
	/**
	 * 获取问题类型
	 * */
	@RequestMapping(value="/getquestiontype")
	public @ResponseBody List<SwjQuestiontype> getQuestionType(){
		List<SwjQuestiontype> list = questionService.getAllQuestionType();
		return list;
	}
	/**
	 * 获取河段
	 * */
	@RequestMapping(value="/getRiver")
	public @ResponseBody List<Responsibility> getRiver(){
		System.out.println("123");
		List<Responsibility> list = questionService.getAllRiver();
		System.out.println(list.size()+"111");
		return list;
	}
	/**
	 * 获取左岸还是右岸
	 * */
	@RequestMapping(value="/getCoast")
	public @ResponseBody List<Responsibility> getCoast(String rivername){
		List<Responsibility> list = questionService.getCoast(rivername);
		return list;
	}
	/**
	 * 获取河段
	 * */
	@RequestMapping(value="/getReach")
	public @ResponseBody List<Responsibility> getReach(String rivername,String lr){
		List<Responsibility> list = questionService.getReach(rivername, lr);
		System.out.println(rivername+","+lr);
		System.out.println(list.size()+"reach");
		return list;
	}
	/**
	 * 按地区统计河涌数量
	 * */
	@RequestMapping(value="getRiverGroupByArea")
	public @ResponseBody List<River> getRiverGroupByArea(){
		List<River> list= riverService.getRiverGroupByArea();
		return list;
	}
	/**
	 * 获取问题
	 * */
	@RequestMapping(value="/getQuestion")
	public @ResponseBody SwjQuestionBean getQuestion(String qId){
		SwjQuestionBean bean = questionService.findById(qId);
		System.out.println(bean.getComplainDate()+"date");
		 List<SwjAttachment> list = questionService.getAttchId(qId);
		 String[] strs = new String[list.size()];
		 int i=0;
		 for(SwjAttachment a : list){
			 strs[i] = a.getId();
			 i++;
		 }
		 bean.setAttachIds(strs);
		return bean;
	}
	
	/**
	 * 
	 * 获取投诉处理基本详情
	 * **/
	@RequestMapping(value="/getQuestionItem")
	public @ResponseBody SwjQuestionBean getQuestionItem(String qId){
		System.out.println(qId);
		SwjQuestionBean bean = questionService.findById(qId);
		
		System.out.println(bean.getComplainDate()+"date");
		//获取图片
		List<SwjAttachment> list = questionService.getAttchId(qId);
		 String[] strs = new String[list.size()];
		 int i=0;
		 for(SwjAttachment a : list){
			 strs[i] = a.getId();
			 i++;
		 }
		 //获取市分派情况
		 String returnStr = "";
		 try{
			 	List<SwjQuestionItems> items = questionItemService.findByDataId(qId,"1");
				ListJsonBean  bean1= new ListJsonBean();
				changeTimeForm(items);
				bean1.setList(items);
				bean1.setCount(items.size());
				returnStr = JSONObject.fromObject(bean1).toString();
				bean.setCitycontent(returnStr);
				System.err.println(returnStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		 
		  //获取区受理情况
		 String returnStr1 = "";
		 try{
			 	List<SwjQuestionItems> items = questionItemService.findByDataId(qId,"2");
				ListJsonBean bean1 = new ListJsonBean();
				changeTimeForm(items);
				bean1.setList(items);
				bean1.setCount(items.size());
				returnStr1 = JSONObject.fromObject(bean1).toString();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				 bean.setAreacontent(returnStr1);
			}
		 
		 bean.setAttachIds(strs);
		return bean;
	}
	
	private void changeTimeForm(List<SwjQuestionItems> items) {
		for(SwjQuestionItems item:items){
			item.setCreateTime(DateUtils.changeToTimeDesc_CN(item.getCreateTime()));
		}
	}
	/**
	 * 保存
	 * */
	@RequestMapping(value="/saveQuestion")
	public @ResponseBody String saveQuestion(String type,String content,String id,String river2,String coast2,String reach2,String area2,String position,String areaname,String streetname,String villagename,String limitTime){
		SwjQuestionBean bean = questionService.findById(id);
		bean.setQuestiontype(type);
		bean.setQuestioncontent(content);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Subject subject = SecurityUtils.getSubject();
		UserBean user = ShiroUtils.getUser();
		Date date;
		try {
			date = format.parse(limitTime);
			if (subject.hasRole("areaRole")) {//区级河长  
				bean.setStreetLimitDate(date);
				if(villagename != null && !villagename.equals("")){
					bean.setIscross("0");
				}
			}else if(subject.hasRole("streetRole")){
				bean.setVillageLimitDate(date);
			}else if(subject.hasRole("villageRole")){
			}else{
				bean.setAreaLimitDate(date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String reachname = river2;
		River river = null;
		String riverCode = "";
		if(reachname != null && !reachname.equals("") && !reachname.equals("请选择")){
			river= questionService.getRiverByRiverName(reachname);
		}
		String length = "";
		if(river != null){
			length = river.getLength();
			riverCode = river.getRiverCode();
		}
		bean.setLength(length);
		bean.setRiverCode(riverCode);
		if(coast2.equals("请选择")){
			coast2 = "";
		}
		bean.setLeftRight(coast2);
		if(reach2.equals("请选择")){
			reach2 = "";
		}
		bean.setPartName(reach2);
		if(reachname.equals("请选择")){
			reachname = "";
		}
		bean.setReachname(reachname);
		bean.setArea(area2);
		bean.setStatus(2);
		bean.setQuestionposition(position);
		bean.setAreaPersonName(areaname);
		bean.setStreetPersonName(streetname);
		bean.setVillagePersonName(villagename);
		questionService.saveQuestionByWeb(bean);
		return "succ";
	}
	/**
	 * 获取所有的问题
	 * */
	@RequestMapping(value="findAllQuestion")
	public @ResponseBody List<SwjQuestion> findAllQuestion(){
		List<SwjQuestion> list = questionService.findAll();
		return list;
	}
	/**
	 * 查询所有地区的投诉统计
	 * */
	@RequestMapping(value="findTj")
	public @ResponseBody List<River> findTj(String startTime,String endTime){
		List<River> list = questionService.getTj(startTime,endTime);
		/*for(River r:list){
			System.out.println(r.getArea()+"555");
		}*/
		return list;
	}
	
	/**
	 * 查询所有地区的投诉统计2
	 * */
	@RequestMapping(value="getAreaQuestionTj")
	public @ResponseBody List<SwjQuestion> getAreaQuestionTj(String startTime,String endTime){
		List<SwjQuestion> list = questionService.getAreaQuestionTj(startTime,endTime);
		/*for(River r:list){
			System.out.println(r.getArea()+"555");
		}*/
		return list;
	}
	
	/**
	 * 查询所有地区的问题类型
	 * */
	@RequestMapping(value="getTypeByArea")
	public @ResponseBody List<SwjQuestion> getTypeByArea(String startTime,String endTime){
		List<SwjQuestion> list= questionService.getAreaQuestion(startTime,endTime);
		return list;
	}
	/*@RequestMapping(value="getArea")
	public @ResponseBody Map getArea(){
		Map map = questionService.getQuestionByArea();
		return map;
	}*/
	/**
	 * 查询所有的地区
	 * */
	@RequestMapping(value="findAllArea")
	public @ResponseBody List<River> findAllArea(){
		List<River> list= questionService.getAllArea();
		return list;
	}
	/**
	 * 查询所有的地区
	 * */
	@RequestMapping(value="findQuestionAllArea")
	public @ResponseBody List<SwjQuestion> findQuestionAllArea(){
		List<SwjQuestion> list= questionService.findQuestionAllArea();
		return list;
	}
	/**
	 * 查询所有问题类型
	 * */
	@RequestMapping(value="findAllTypeByTj")
	public @ResponseBody List<SwjQuestiontype> findAllTypeByTj(){
		List<SwjQuestiontype> list= questionService.getQuestionTypeByTj();
		return list;
	}
	/**
	 * 按照月份统计已处理的投诉
	 * */
	@RequestMapping(value="getYcl")
	public @ResponseBody List<SwjQuestion> getYcl(){
		List<SwjQuestion> list= questionService.getYcl();
		return list;
	}
	/**
	 * 按照月份统计未处理的投诉
	 * */
	@RequestMapping(value="getWcl")
	public @ResponseBody List<SwjQuestion> getWcl(){
		List<SwjQuestion> list= questionService.getWcl();
		return list;
	}
	/**
	 * 查询地区的河段
	 * @param area 地区
	 * */
	@RequestMapping(value="getRiverByArea")
	public @ResponseBody List<River> getRiverByArea(String area){
		List<River> list= questionService.getRiversByArea(area);
		System.out.println(list.size()+"999");
		return list;
	}
	/**
	 * 查询河段的等级
	 * @param area 地区
	 * */
	@RequestMapping(value="getGradeByRiverName")
	public @ResponseBody List<River> getGradeByRiverName(String rivername){
		List<River> list= riverService.getGradeByRiverName(rivername);
		return list;
	}
	/**
	 * 查询地区的河段 统计这个地区的问题
	 * @param area 地区
	 * */
	@RequestMapping(value="getRiverByAreaToTj")
	public @ResponseBody List<SwjQuestion> getRiverByAreaToTj(String area,String startTime,String endTime){
		List<SwjQuestion> list = null;
		if(area.equals("请选择")){
			list= questionService.getAreaQuestion(startTime,endTime);
		}else{
			list= questionService.getRiversByAreaToTj(area,startTime,endTime);
		}
		return list;
	}
	
	/**
	 * 按地区查询相关信息
	 * @param area 地区
	 * */
	@RequestMapping(value="getReDataByArea")
	public @ResponseBody List<Responsibility> getReDataByArea(String area){
		List<Responsibility> list= responsibilityService.getReDataByArea(area);
		return list;
	}
	
	/**
	 * 按河段名查询相关信息
	 * @param area 地区
	 * */
	@RequestMapping(value="getReByRiverName")
	public @ResponseBody List<Responsibility> getReByRiverName(String reachname,String area){
		List<Responsibility> list= responsibilityService.getReByRiverName(reachname,area);
		return list;
	}
	/**
	 * 查询河段 统计这个河段的问题
	 * @param area 地区
	 * */
	@RequestMapping(value="getRiversByRiverToTj")
	public @ResponseBody List<SwjQuestion> getRiversByRiverToTj(String rivername){
		List<SwjQuestion> list= questionService.getRiversByRiverToTj(rivername);
		return list;
	}
	/**
	 * 无效投诉
	 * @param area 地区
	 * */
	@RequestMapping(value="wxQuestion")
	public @ResponseBody String wxQuestion(String id,String content){
		questionService.saveWxQuestion(id,content);
		return "succ";
	}
	/**
	 * 查询所有问题 地区分类
	 * */
	@RequestMapping(value="getCountByAreaAll")
	public @ResponseBody List<SwjQuestion> getCountByAreaAll(String startTime,String endTime){
		List<SwjQuestion> list = questionService.getCountByAreaAll(startTime,endTime);
		return list;
	}
	
	/**
	 * 查询所有问题 地区分类(不关联河流)
	 * */
	@RequestMapping(value="getQuestionCountByAreaAll")
	public @ResponseBody List<SwjQuestion> getQuestionCountByAreaAll(String startTime,String endTime){
		List<SwjQuestion> list = questionService.getQuestionCountByAreaAll(startTime,endTime);
		return list;
	}
	
	/**
	 * 查询已处理问题 (不关联河流)
	 * */
	@RequestMapping(value="getQuestionCountByAreaYcl")
	public @ResponseBody List<SwjQuestion> getQuestionCountByAreaYcl(String startTime,String endTime){
		List<SwjQuestion> list = questionService.getQuestionCountByAreaYcl(startTime,endTime);
		return list;
	}
	
	/**
	 * 查询已处理问题 地区分类
	 * */
	@RequestMapping(value="getCountByAreaYcl")
	public @ResponseBody List<SwjQuestion> getCountByAreaYcl(String startTime,String endTime){
		List<SwjQuestion> list = questionService.getCountByAreaYcl(startTime,endTime);
		return list;
	}
	/**
	 * 查询已处理问题 地区分类
	 * */
	@RequestMapping(value="getCountAreaZzcl")
	public @ResponseBody List<SwjQuestion> getCountAreaZzcl(String area){
		List<SwjQuestion> list = questionService.getCountAreaZzcl(area);
		return list;
	}
	/**
	 * 查询已处理问题 地区分类
	 * */
	@RequestMapping(value="getCountAreaYcl")
	public @ResponseBody List<SwjQuestion> getCountAreaYcl(String area){
		List<SwjQuestion> list = questionService.getCountAreaYcl(area);
		return list;
	}
	/**
	 * 根据地区和河段获取所有问题
	 * */
	@RequestMapping(value="getTypeByRiverNameAndAreaTj")
	public @ResponseBody List<SwjQuestion> getTypeByRiverNameAndArea(String rivername,String area,String startTime,String endTime){
		List<SwjQuestion> list = null;
		if(rivername.equals("请选择")){
			list= questionService.getRiversByAreaToTj(area,startTime,endTime);
		}else{
			list = questionService.getTypeByRiverNameAndArea(rivername, area,startTime,endTime);
		}
		return list;
	}
	
	@RequestMapping(value="/collect")
	public String collect(HttpServletRequest request){
		
		return "weixin/collect";
	}
	/**
	 * 查询所有的问题类型
	 * */
	@RequestMapping(value="/searchAlltype")
	public @ResponseBody DataTablesResult<SwjQuestiontypeBean> searchAlltype(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize){
		int page = questionService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjQuestiontypeBean> records = typeService.findTypeAll(pr);
		Integer count = null;
		DataTablesResult<SwjQuestiontypeBean> dtr = new DataTablesResult<SwjQuestiontypeBean>();
		dtr.setData(records.getContent());
		dtr.setDraw(draw);
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}
	/**
	 * 保存问题类型
	 * */
	@RequestMapping(value="/saveType")
	public @ResponseBody String saveType(SwjQuestiontypeBean bean){
		System.out.println(bean.getOrdernumber()+"666");
		typeService.save(bean);
		return "succ";
	}
	/**
	 * 删除问题类型
	 * */
	@RequestMapping(value="/deleteType")
	public @ResponseBody String deleteType(String id){
		typeService.delete(id);
		return "succ";
	}
	/**
	 * 上传回复图片
	 * */
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        //1.获取所有文件的迭代器
         Iterator<String> itr =  request.getFileNames();
         response.setContentType("text/plain");
         MultipartFile mpf = null;
         //2.获得其中一个文件
         while(itr.hasNext()){
             mpf = request.getFile(itr.next()); 
             String name = mpf.getOriginalFilename();
             if(name == null){
        		 return "文件上传有误!";
             }
         }
         return null;
    }
	/**
	 * 查询问题
	 * */
	@RequestMapping(value="getQuestionByWx")
	public @ResponseBody List<SwjQuestion> getQuestionByWx(String riverName,String area,String questiontype){
		List<SwjQuestion> list = questionService.getQuestionByRiverNameAndArea(riverName,area,questiontype);
		return list;
	}
	
	/**
	 * 查询问题
	 * */
	@RequestMapping(value="getResponseByLf")
	public @ResponseBody List<Responsibility> getResponseByAreaAndRivernameAndLf(String rivername,String area,String lr){
		List<Responsibility> list = questionService.getAllResponsibility(area, rivername, lr);
		System.out.println(list.size()+","+area+"777");
		return list;
	}
	/**
	 * 查询问题
	 * */
	@RequestMapping(value="getResponseByTwo")
	public @ResponseBody List<Responsibility> getResponseByThree(String rivername,String area){
		List<Responsibility> list = questionService.getAllResponseByAreaAndRiverName(area, rivername);
		System.out.println(list.size()+","+area+"777");
		return list;
	}
	/**
	 * 查询问题
	 * */
	@RequestMapping(value="getResponseByFour")
	public @ResponseBody List<Responsibility> getResponseByFour(String rivername,String area,String lr,String reach){
		List<Responsibility> list = questionService.getAllResponseByFour(area, rivername,lr,reach);
		System.out.println(list.size()+","+area+"777");
		return list;
	}
	
	/**
	 * 查询河流
	 * */
	@RequestMapping(value="getRiverToWx")
	public @ResponseBody List<River> getRiverToWx(String area,String rivername){
		List<River> list = questionService.getRiverToWx(rivername, area);
		System.out.println(list.size()+","+area+"777");
		return list;
	}
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	/*@RequestMapping(value="/exportCSV")
	public  String exportCSV(HttpServletRequest request,String codeExport, String qtyteExport,String riverExport,String coastExport,String reachExport,String contentExport,String areaExport,String startExport,String endExport,HttpServletResponse response){
		Date sdate = null, edate = null;
		System.out.println(riverExport+","+coastExport+"777777");
		List<SwjQuestionBean> list = questionService.getAllToExport(codeExport,qtyteExport,0,riverExport,coastExport,reachExport,startExport,endExport,areaExport);
		//List<SwjQuestionBean> list = questionService.getAllToExport(codeExport,qtyteExport,statusExport,riverExport,coastExport,reachExport,startExport,endExport,areaExport);
		Date da= new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(da);
		String path = request.getSession().getServletContext().getRealPath("waterwx/exportCsv");// path表示创建文件的路径
		//File file = new File("d:/fenfa/download/export"+date+".csv");
		String fileName = "export"+date+".csv";
		File file = new File(path+"/"+fileName);
		 File parent = file.getParentFile();  
         if (parent != null && !parent.exists()) {  
             parent.mkdirs();  
         }  
         System.out.println(list.size()+"99999");
         String content = contentExport.replaceAll("undefined", "");
		boolean is = questionService.exportCsv1(file, list,content);
		//if(is == true){
			String message = downloadCsv(request,response,fileName);
			System.out.println(message);
			return message;
		//}else{
		//	return null;
		//}
	}*/
	
	/**
	 * 导出为Excel格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * @throws IOException 
	 * */
	@RequestMapping(value="/exportExcel")
	public  String exportExcel(HttpServletRequest request,String startTime, String endTime,HttpServletResponse response) throws IOException{
		Date sdate = null, edate = null;
		
		System.out.println(startTime+","+endTime+"777777");
		List<Map<Integer, Object>> list = questionService.getAllToExcel(startTime,endTime);
        TemplateExcelUtil excel = new TemplateExcelUtil();
        //Map<String, Object> datalist = questionService.getTotalToExcel(startTime,endTime);
        Map<String, Object> datalist=this.getTotalList(list);
        
        List<Map<Integer, Object>> replyList = questionService.getAllReplyExcel(startTime,endTime);
        
        Map<String, Object> datareplylist=this.getTotalCount(replyList);
        
		Date da= new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(da);
		String path = request.getSession().getServletContext().getRealPath("template");// path表示创建文件的路径
		String tempFilePath=path+"/ceshi.xls";
		//File file = new File("d:/fenfa/download/export"+date+".csv");
		String name="广州水务"+DateUtils.changeToTimeDesc_11date(startTime)+"至"+DateUtils.changeToTimeDesc_11date(endTime)+"微信问题数量汇总";
		String fileName = name+".xls";
		File file = new File(path+"/"+fileName);
		OutputStream os = new FileOutputStream(file);
		 File parent = file.getParentFile();  
         if (parent != null && !parent.exists()) {  
             parent.mkdirs();  
         }
         
         
         /*startTime=DateUtils.changeToTimeDesc_11date(startTime);
         endTime=DateUtils.changeToTimeDesc_12mmdd(endTime);*/
         endTime=DateUtils.StringToDateTime_yMdhms(0);
		 startTime=DateUtils.StringToDateTime_yMdhms(7);
         System.out.println(startTime+"-"+endTime);
         Map<String, Object> dataMap = new HashMap<String, Object>();
         dataMap.put("C3", startTime+"-"+endTime);
         dataMap.put("C5", datalist.get("C5"));
         dataMap.put("D5", datalist.get("D5"));
         dataMap.put("E5", datalist.get("E5"));
         excel.writeData(tempFilePath, dataMap, 0);
         //System.out.println(list.size()+"99999");
         String[] heads = new String[]{"C5","D5","E5"};   //必须为列表头部所有位置集合，   输出 数据单元格样式和头部单元格样式保持一致
         excel.writeDateList(tempFilePath,heads,list,0);
        
         //第二个
         Map<String, Object> replyDataMap = new HashMap<String, Object>();
         replyDataMap.put("C3", startTime+"-"+endTime);
         replyDataMap.put("C5", datareplylist.get("C5"));
         replyDataMap.put("D5", datareplylist.get("D5"));
         replyDataMap.put("F5", datareplylist.get("F5"));
         replyDataMap.put("G5", datareplylist.get("G5"));
         excel.writeData(tempFilePath, replyDataMap, 1);
         String[] replyHeads = new String[]{"C5","D5","F5","G5"};   //必须为列表头部所有位置集合，   输出 数据单元格样式和头部单元格样式保持一致
         
         excel.writeDateList(tempFilePath,replyHeads,replyList,1);
		/*boolean is = questionService.exportCsv1(file, list,content);
		*/
       //写到输出流并移除资源
         excel.writeAndClose(tempFilePath, os);
         String message = downloadExcel(request,response,fileName);
         os.flush();
         os.close();
         System.out.println("结束");
		return message;

	}
	private Map<String, Object> getTotalList(List<Map<Integer, Object>> list){
		Map<String, Object> data = new HashMap<String,Object>();
		int num=0,num1=0,num2=0;
		for (int i = 0; i < list.size(); i++) {
			Map<Integer,Object> m =list.get(i);
			num+=Integer.valueOf(m.get(1).toString());
			num1+=Integer.valueOf(m.get(2).toString());
			num2+=Integer.valueOf(m.get(3).toString());
		}
		data.put("C5", num);
		data.put("D5", num1);
		data.put("E5", num2);
		return data;
	}
	private Map<String, Object> getTotalCount(List<Map<Integer, Object>> list){
		Map<String, Object> data = new HashMap<String,Object>();
		int num=0,num1=0,num2=0,num3=0;
		for (int i = 0; i < list.size(); i++) {
			Map<Integer,Object> map =list.get(i);
			num+=Integer.valueOf(map.get(1).toString());
			num1+=Integer.valueOf(map.get(2).toString());
			num2+=Integer.valueOf(map.get(3).toString());
			num3+=Integer.valueOf(map.get(4).toString());
		}
		data.put("C5", num);
		data.put("D5", num1);
		data.put("F5", num2);
		data.put("G5", num3);
		return data;
	}
	private String downloadExcel(HttpServletRequest request, HttpServletResponse response,  String fileName) {
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			// 获取项目根目录
			String ctxPath = request.getSession().getServletContext().getRealPath("template");
			
			// 获取文件名
			String downLoadPath = ctxPath + "/" + fileName;

			// 获取文件
			File file = new File(downLoadPath);
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
				return "您要下载的资源已被删除！！";
			}
			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
			//给投诉表用户设置状态
			exportUserService.changequestiontype();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错了！请联系管理员";
		}
	}
	private String downloadCsv(HttpServletRequest request, HttpServletResponse response,  String fileName) {
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			// 获取项目根目录
			String ctxPath = request.getSession().getServletContext().getRealPath("waterwx/exportCsv");

			// 获取文件名
			String downLoadPath = ctxPath + "/" + fileName;

			// 获取文件
			File file = new File(downLoadPath);
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
				return "您要下载的资源已被删除！！";
			}
			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错了！请联系管理员";
		}
	}
	/**
	 * 获取区级，街道，村级河长，姓名及联系电话
	 * */
	@RequestMapping(value="getPersonName")
	public @ResponseBody List<Responsibility> getPersonName(String area,String rivername,String lr,String reach){
		System.out.println(area+"area111"+rivername+","+lr+","+reach);
		List<Responsibility> list = questionService.getPersonName(area, rivername, lr, reach);
		return list;
	}
	/**
	 * 获取区级，街道，村级河长，姓名及联系电话
	 * */
	@RequestMapping(value="getVillagePersonName")
	public @ResponseBody List<Responsibility> getVillagePersonName(String area,String rivername){
		List<Responsibility> list = questionService.getVillagePersonName(area, rivername);
		return list;
	}
	/**
	 * 获取区级河长联系电话
	 * */
	@RequestMapping(value="getTelByAreaName")
	public @ResponseBody List<Responsibility> getTelByAreaName(String areaname){
		
		List<Responsibility> list = questionService.getTelByAreaName(areaname);
		return list;
	}
	/**
	 * 获取街道联系电话
	 * */
	@RequestMapping(value="getTelByStreetName")
	public @ResponseBody List<Responsibility> getTelByStreetName(String streetName){
		List<Responsibility> list = questionService.getTelByStreetName(streetName);
		return list;
	}
	/**
	 * 获取村级河长联系电话
	 * */
	@RequestMapping(value="getTelByVillageName")
	public @ResponseBody List<Responsibility> getTelByVillageName(String villageName){
		List<Responsibility> list = questionService.getTelByVillageName(villageName);
		return list;
	}
	/**
	 *完结功能
	 *@param qId  问题id
	 * */
	@RequestMapping(value="endQuestion")
	public @ResponseBody String endQuestion(String qId){
		questionService.endQuestion(qId);
		return "succ";
	}
	/**
	 *是否为越级下发的投诉
	 *@param qId  问题id
	 * */
	@RequestMapping(value="getIscross")
	public @ResponseBody String getIscross(String qId){
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("streetRole")){
			String isFlag = questionService.getIscross(qId);
			if(isFlag.equals("succ")){
				return "succ";
			}
	    }
		return "fail";
	}
	
	/**
	 * 获取所有的问题
	 * */
	@RequestMapping(value="getAllQuestion")
	public @ResponseBody List<SwjQuestionBean> getAllQuestion(String area,String sTime,String eTime){
		Date edate = null;
		Date sdate = null;
		System.out.println(area+","+sTime+","+eTime+"1234");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(eTime == null || eTime.equals("")){
			edate = new Date();
			if(sTime == null || sTime.equals("")){
				Calendar cal = Calendar.getInstance();
				//下面的就是把当前日期加一个月
				cal.add(Calendar.MONTH, -1);
				
				System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
				System.out.println("yesterday is:" + format.format(cal.getTime()));
				sdate = cal.getTime();
			}else{
				try {
					sdate = format.parse(sTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			try {
				edate = format.parse(eTime);
				sdate = format.parse(sTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<SwjQuestionBean> list = questionService.getAllQuestion(area,sdate,edate);
		System.out.println(list.size()+"5555");
		return list;
	}
	
	/**
	 * 查询导出表所有用户
	 * */
	@RequestMapping(value="/searchAllExportUser")
	public @ResponseBody DataTablesResult<SwjWxexportUserBean> searchAllExportUser(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize){
		int page = questionService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjWxexportUserBean> records = exportUserService.findExportUserAll(pr);
		Integer count = null;
		List list = new ArrayList();
		DataTablesResult<SwjWxexportUserBean> dtr = new DataTablesResult<SwjWxexportUserBean>();
		if(records == null){
			dtr.setData(list);
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(1);
			dtr.setRecordsTotal(1);
		}else{
			List<SwjWxexportUserBean> content = records.getContent();
			for (int i = 0; i < content.size(); i++) {
				content.get(i).setId(i+1);
			}
			dtr.setData(records.getContent());
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
			dtr.setRecordsTotal(records.getTotalPages());
		}
		return dtr;
	}
	
	/**
	 * 红包数据导出为txt格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	@RequestMapping(value="/exportTXT")
	public  String exportTXT(HttpServletRequest request,HttpServletResponse response){
		Date sdate = null, edate = null;
		List<SwjWxexportUser> list = exportUserService.getAllToExport();
		Date da= new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(da);
		String path = request.getSession().getServletContext().getRealPath("waterwx/exportGiftMoneyTxt");// path表示创建文件的路径
		//File file = new File("d:/fenfa/download/export"+date+".csv");
		String fileName = "giftmoney"+date+".txt";
		File file = new File(path+"/"+fileName);
		 File parent = file.getParentFile();  
         if (parent != null && !parent.exists()) {  
             parent.mkdirs();  
         }  
		boolean is = exportUserService.exportTxt(file, list);
		//if(is == true){
			String message = downloadTxt(request,response,fileName);
			return message;
		//}else{
		//	return null;
		//}
	}
	private String downloadTxt(HttpServletRequest request, HttpServletResponse response,  String fileName) {
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			// 获取项目根目录
			String ctxPath = request.getSession().getServletContext().getRealPath("waterwx/exportGiftMoneyTxt");
			
			// 获取文件名
			String downLoadPath = ctxPath + "/" + fileName;

			// 获取文件
			File file = new File(downLoadPath);
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
				return "您要下载的资源已被删除！！";
			}
			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
			//给投诉表用户设置状态
			exportUserService.changequestiontype();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错了！请联系管理员";
		}
	}
	/**
	 * 统计河道数量
	 * @param area 行政区域 startTime 开始时间 endTime 结束时间
	 * */
	@RequestMapping(value="/getCountRiverByAreaTj")
	public @ResponseBody List<SwjQuestion> getCountRiverByAreaTj(String area,String startTime,String endTime){
		List<SwjQuestion> list = questionService.getCountByAreaTj(area, startTime, endTime);
		return list;
	}
	
	
}
