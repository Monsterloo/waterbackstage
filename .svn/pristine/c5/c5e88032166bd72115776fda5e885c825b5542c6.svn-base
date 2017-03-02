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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjFeedbackBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxquestionBean;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjFeedbackService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.service.SwjWxQuestionService;
import com.hhh.fund.waterwx.service.impl.SwjQuestionServiceImpl;
import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.fund.web.model.FileMeta;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.hhh.weixin.util.QiyehaoConst;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Controller
@RequestMapping("/feedback")
public class SwjFeedbackController {
	private final static String PAGE_SIZE="7";
	@Autowired
	private SwjFeedbackService fbService;
	@Autowired
	private SwjQuestionService questionService;
	@Autowired
	private UserCenterService ucs;
	@Autowired
	private SwjUserService userService;
	/**
	 * 查询所有的反馈信息
	 * */
	@RequestMapping(value="/searchAllFeedback")
	public @ResponseBody DataTablesResult<SwjFeedbackBean> searchAllFeedback(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize,String questionId){
		int page = fbService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjFeedbackBean> records = fbService.findListFeedback(questionId, pr);
		Integer count = null;
		DataTablesResult<SwjFeedbackBean> dtr = new DataTablesResult<SwjFeedbackBean>();
		List<SwjFeedbackBean> list = new ArrayList<SwjFeedbackBean>();
		if(records == null){
			dtr.setData(list);
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(0);
			dtr.setRecordsTotal(0);
		}else{
			dtr.setData(records.getContent());
			dtr.setDraw(draw);
			dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
			dtr.setRecordsTotal(records.getTotalPages());
		}
		return dtr;
	}
	/**
	 * 保存反馈
	 * */
	@RequestMapping(value="/saveFeedback")
	public @ResponseBody String saveFeedback(String questionId,String content){
		Subject subject = SecurityUtils.getSubject();
		SwjFeedbackBean bean = new SwjFeedbackBean();
		bean.setQuestionId(questionId);
		bean.setContent(content);
		System.out.println(bean.getQuestionId()+"99999");
		UserBean user = ShiroUtils.getUser();
		bean.setUsername(user.getDisplayName());
		bean.setFeedbackdate(new Date());
		SwjQuestionBean qBean = questionService.findById(questionId);
		if (subject.hasRole("areaRole")) {//区级河长  
			bean.setUserrole("区级河长");
			qBean.setAreaPersonName("");
		}else if(subject.hasRole("streetRole")){
			bean.setUserrole("街镇河长");
			qBean.setStreetPersonName("");
		}else if(subject.hasRole("villageRole")){
			bean.setUserrole("村级河长");
			qBean.setVillagePersonName("");
		}
		fbService.save(bean);
		questionService.save(qBean);
		return "succ";
	}
	/**
	 * 保存反馈
	 * */
	@RequestMapping(value="/saveFeedbackByWeixin")
	public @ResponseBody String saveFeedbackByWeixin(String questionId,String content,HttpSession session){
		SwjFeedbackBean bean = new SwjFeedbackBean();
		bean.setQuestionId(questionId);
		bean.setContent(content);
		System.out.println(bean.getQuestionId()+"99999");
		bean.setFeedbackdate(new Date());
		SwjQuestionBean qBean = questionService.findById(questionId);
		String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
		String role = "";
		SwjUserBean uBean = userService.findByUserId(userId);
		Set<RoleBean> beans = ucs.getUserRole(uBean.getLoginname());
		bean.setUsername(uBean.getName());
		if(beans != null){
			for(RoleBean rBean : beans){
		if (rBean.getName().equals("areaRole")) {//区级河长  
			bean.setUserrole("区级河长");
			qBean.setAreaPersonName("");
			break;
	    }else if(rBean.getName().equals("streetRole")){
	    	bean.setUserrole("镇街河长");
	    	qBean.setStreetPersonName("");
	    	break;
	    }else if(rBean.getName().equals("villageRole")){
	    	bean.setUserrole("村级河长");
	    	qBean.setVillagePersonName("");
	    	break;
	    }
			}}
		fbService.save(bean);
		questionService.save(qBean);
		return "succ";
	}
	/**
	 * 删除反馈
	 * */
	@RequestMapping(value="/deleteFeedback")
	public @ResponseBody String deleteWx(String id){
		fbService.delete(id);
		return "succ";
	}
	/**
	 * c查询一条回复
	 * @param id 问题id
	 * */
	@RequestMapping(value="/findFeedback")
	public @ResponseBody String findFeedback(String id1){
		SwjFeedbackBean bean = fbService.findById(id1);
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
}
