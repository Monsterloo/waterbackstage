package com.hhh.fund.waterwx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhh.fund.util.JsonUtil;
import com.hhh.fund.util.StringUtils;
import com.hhh.fund.util.WebUtil;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.util.Constants;

@Controller
@RequestMapping(value = "/weixin/swjquestion")
public class SwjQuestionController {

	@Autowired
	private SwjQuestionService swjQuestionService;
	@Autowired
	private SwjAnswerService swjAnswerService;
	
	/**
	 * 我的投诉
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/complainList")
	public String complainList(HttpServletRequest request,HttpServletResponse response) {
		
		   HttpSession session = request.getSession();
		   String openId=(String)session.getAttribute(Constants.XW_USER_OPEN_ID);
//		   openId="ou_PevmuPH-jqVqqdvZScynVnx08";
		   
		   if(StringUtils.isNotBlank(openId)){
			   int pageno=1;
			   Integer totalPage = 1;
			   String pageNo=request.getParameter("pageNo");
			   String more=request.getParameter("more");
			   if(StringUtils.isNotBlank(pageNo)){
				   pageno = Integer.parseInt(pageNo);
			   }
			   int pageSize=Constants.PAGE_SIZE;
//			   pageSize=1;
			   pageno--;
			   PageRequest page = new PageRequest(pageno, pageSize);
			   
			   Map<String, Object> dataMap = swjQuestionService.findByOpenid(openId, page);
//			   System.err.println("dataMap- -->"+dataMap);
			   Integer total = (Integer)dataMap.get("total");
			   List<SwjQuestion> list =(List<SwjQuestion>)dataMap.get("list");
			   totalPage = total/pageSize;
			   
			   if(total%pageSize>0) totalPage++;
			   
			   if("more".equals(more)){
				   try{
					   String info=JsonUtil.toJson(list);
					   WebUtil.sendJson(response,info);
				   }catch(Exception e){
					   e.printStackTrace();
				   }
				   return null;
			   }else{
				   request.setAttribute("list", list);
				   request.setAttribute("totalPage", totalPage);
				   request.setAttribute("pageSize", pageSize);
				   request.setAttribute("pageNo", pageno+1);
				   return "wx/complainlist";
			   }
		   }else{
			   System.err.println("openId is null!");
			   return null;
		   }
		
	}
	
	/**
	 * 查看投诉的详细信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/questionInfo")
	public String questionInfo(HttpServletRequest request){
	  
	   HttpSession session = request.getSession();
	   String openId=(String)session.getAttribute(Constants.XW_USER_OPEN_ID);
//	   openId="ou_PevmuPH-jqVqqdvZScynVnx08";
	   if(StringUtils.isNotBlank(openId)){
		   
			String id=request.getParameter("id");
			
			SwjQuestion question = swjQuestionService.findByOpenIdAndId(openId,id);
			
			SwjAnswer swjAnswer= null;
			
			Responsibility res=null;
			
			List<SwjAttachment> attList=null;
			List<SwjAnswer> swjAnswerList = null;
			if(question!=null){
				 String qid=question.getId();
				 attList= swjQuestionService.getAttchId(qid);
				 List<Responsibility> resList=swjQuestionService.findByRiverCodeAndPartName(question.getRiverCode(), question.getPartName());
				 swjAnswerList=swjAnswerService.findLastAnswerByQuestionId(qid);
//			     if(swjAnswerList!=null && swjAnswerList.size()>0){
//			    	  swjAnswer= swjAnswerList.get(0);
//			     }
			     if(resList!=null && resList.size()>0){
			    	 res=resList.get(0);
			     }
			}else{
				System.err.println("question is null  openId-->"+openId+" id-->"+id);
			}
			
			request.setAttribute("attList", attList);
			
			request.setAttribute("question", question);
			
			request.setAttribute("res", res);
			
			request.setAttribute("swjAnswer", swjAnswerList);
	   }else{
		   System.err.println("openId is null!");
		   return null;
	   }

	   return "wx/slbh";
		
	}
	
}
