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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
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

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.entity.SysUcenterDepartment;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.util.JacksonUtils;
import com.hhh.fund.waterwx.webmodel.ListJsonBean;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjFeedbackBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionItemsBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxquestionBean;
import com.hhh.fund.waterwx.webmodel.ToFrontWeiXinUserBean;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjFeedbackService;
import com.hhh.fund.waterwx.service.SwjQuestionItemService;
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
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Controller
@RequestMapping("/complain")
public class WeixinComplainController {
	private final static String PAGE_SIZE="7";
	public static final String RETURN_NULL = "RequestSuccessfulNull";
	
	@Autowired
	private SwjQuestionService questionService;
	@Autowired
	private SwjAnswerService answerService;
	
	@Autowired
	private SwjQuestiontypeService typeService;
	
	@Autowired
	private RiverService riverService;
	
	@Autowired
	private SwjQuestionItemService swjQuestionItemService;
	
	@Autowired
	private SwjWxQuestionService wxService;
	@Autowired
	private SwjFeedbackService fbService;
	@Autowired
	private UserCenterService ucs;
	@Autowired
	private SwjUserService userService;
	
	@RequestMapping(value="/main")
	public String adminMain(HttpSession session,Model model){
		String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
		String role = "";
		SwjUserBean uBean = userService.findByUserId(userId);
		Set<RoleBean> beans =ucs.getUserRole(uBean.getLoginname());
		if(beans != null){
			for(RoleBean rBean : beans){
				if (rBean.getName().equals("areaRole")) {//区级河长  
					role="1";
					break;
			    }else if(rBean.getName().equals("streetRole")){
			    	role="1";
			    	break;
			    }else if(rBean.getName().equals("villageRole")){
			    	role="1";
			    	break;
			    }
			}
		}
		model.addAttribute("role12", role);
		return "complainweixin/complainMenu";
	}
	
	/**
	 * 获取对应等级的对应投诉列表
	 * @Param complainId 投诉id
	 * */
	@RequestMapping(value="/getQuestionListByType")
	public @ResponseBody void getQuestionListByType(String type,HttpServletResponse response,HttpSession session){
		String returnStr = RETURN_NULL;
		String userId = String.valueOf(session.getAttribute("userId"));
		String grade = String.valueOf(session.getAttribute("grade"));
		String area = String.valueOf(session.getAttribute("area"));
		try{
		if(!StringUtils.isBlank(grade)){
			if(grade.equals("1")){
				
			}
			
		}else{
			returnStr="sessionmiss";
		}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				outPrintResult(response,returnStr);
			}
	}
	
	
	
	
	
	/**
	 * 获取投诉回复列表
	 * @Param complainId 投诉id
	 * */
	@RequestMapping(value="/getReplyList")
	public @ResponseBody void getReplyList(String complainId,HttpServletResponse response){
		String returnStr = RETURN_NULL;
		try{
			List<SwjQuestionItems> items = swjQuestionItemService.getReplyList(complainId,"reply");
			ListJsonBean bean = new ListJsonBean();
			bean.setCount(items.size());
			bean.setList(items);
			returnStr = "RequestSuccessful"+JSONObject.fromObject(bean).toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			outPrintResult(response,returnStr);
		}
	}
	
	/**
	 * 通过行政区域获取河涌列表
	 * @Param complainId 投诉id
	 * */
	@RequestMapping(value="/getRiverListByAreaName")
	public @ResponseBody void getRiverListByAreaName(String area,HttpServletResponse response){
		String returnStr = RETURN_NULL;
		try{
			List<River> river = riverService.getRiverListByAreaName(area);
			ListJsonBean bean = new ListJsonBean();

			bean.setCount(river.size());
			bean.setList(river);
			returnStr = "RequestSuccessful"+JSONObject.fromObject(bean).toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			outPrintResult(response, returnStr);
		}
	}
	
	/**
	 * 查询所有投诉
	 * menu :菜单 1:待受理投诉 2:已受理投诉 0:已回复投诉 35:完结投诉
	 * */
	@RequestMapping(value="/searchByMenu")
	public @ResponseBody List<SwjQuestionBean> list(String menu,HttpSession session){
		int status;
		if(menu.equals("1")){
			status = 1;
		}else if(menu.equals("2")){
			status = 2;
		}else if(menu.equals("35")){
			status = 35;
		}else if(menu.equals("0")){
			status = 0;
		}else{
			status = 4;
		}
		String token = CommonUtil.getToken();
		String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
		String role = "";
		String areaName = "";
		String streetName = "";
		String villageName = "";
		SwjUserBean uBean = userService.findByUserId(userId);
		Set<RoleBean> beans =ucs.getUserRole(uBean.getLoginname());
		if(beans != null){
			for(RoleBean rBean : beans){
				if (rBean.getName().equals("areaRole")) {//区级河长  
					System.out.println("1234");
					areaName = uBean.getName();
					System.out.println(role+"66666"+areaName);
					break;
			    }else if(rBean.getName().equals("streetRole")){
			    	role = "streetRole";
			    	streetName = uBean.getName();
			    	break;
			    }else if(rBean.getName().equals("villageRole")){
			    	role = "villageRole";
			    	villageName = uBean.getName();
			    	break;
			    }
			}
		}
		List<SwjQuestionBean> list = questionService.getComplainList(status,areaName,streetName,villageName);
		return list;
	}
	/**
	 * 查看投诉的详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/questiondetail")
	public String questionInfo(HttpServletRequest request,HttpSession session) {
		String id = request.getParameter("id");
		SwjQuestionBean question = questionService.findById(id);
		// SwjAnswer swjAnswer= null;
		Responsibility res = null;

		List<SwjAttachment> attList = null;
		List<SwjAnswer> swjAnswerList = null;
		if (question != null) {
			String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
			String role = "";
			SwjUserBean uBean = userService.findByUserId(userId);
			Set<RoleBean> beans =ucs.getUserRole(uBean.getLoginname());
			if(beans != null){
					for(RoleBean rBean : beans){
			if (rBean.getName().equals("areaRole")) {//区级河长  
				role = "areaRole";
				System.out.println(role+"66666");
				break;
		    }else if(rBean.getName().equals("streetRole")){
		    	role = "streetRole";
		    	break;
		    }else if(rBean.getName().equals("villageRole")){
		    	role = "villageRole";
		    	break;
		    }
					}
			}
			String qid = question.getId();
			attList = questionService.getAttchId(qid);
			List<Responsibility> resList = questionService.findByRiverCodeAndPartName(question.getRiverCode(),
					question.getPartName());
			swjAnswerList = answerService.findLastAnswerByQuestionId(qid);
			if (resList != null && resList.size() > 0) {
				res = resList.get(0);
			}

			request.setAttribute("attList", attList);
			request.setAttribute("role", role);
			request.setAttribute("question", question);
			request.setAttribute("username", uBean.getName());
			request.setAttribute("res", res);

			request.setAttribute("swjAnswer", swjAnswerList);
		} else {
			System.err.println("openId is null!");
			return null;
		}
		return "complainweixin/complainDetail";
	}
	/**
	 * 查询回复
	 * @Param questionId 投诉id
	 * */
	@RequestMapping(value="/searchAllAnswer")
	public @ResponseBody List<SwjAnswerBean> searchAllAnswer(String questionId){
		List<SwjAnswerBean> list = answerService.findAllAnswer(questionId);
		return list;
	}
	/**
	 * 查询无效投诉分类
	 * @Param questionId 投诉id
	 * */
	@RequestMapping(value="/searchAllWx")
	public @ResponseBody List<SwjWxquestionBean> searchAllWx(){
		List<SwjWxquestionBean> list = wxService.getAllWxByWeixin();
		return list;
	}
	/**
	 * 查询返回上级处理的投诉
	 * @Param questionId 投诉id
	 * */
	@RequestMapping(value="/searchAllFeedback")
	public @ResponseBody List<SwjFeedbackBean> searchAllFeedback(String qId){
		List<SwjFeedbackBean> list = fbService.findAllFeedbackByWeixin(qId);
		return list;
	}
	/**
	 * 保存回复
	 * @Param questionId 投诉id
	 * */
	@RequestMapping(value="/saveAnswerWeixin")
	public @ResponseBody String saveAnswerWeixin(String qId,String content,HttpSession session){
		SwjAnswerBean bean = new SwjAnswerBean();
		bean.setQuestionId(qId);
		bean.setAnswercontent(content);
		bean.setAnswerDate(new Date());
		String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
		SwjUserBean uBean = userService.findByUserId(userId);
		bean.setUsername(uBean.getName());
		questionService.updateStatus(qId, 0);
		answerService.saveAnswer(bean);
		return "succ";
	}
	/**
	 * 保存
	 * */
	@RequestMapping(value="/saveQuestion")
	public @ResponseBody String saveQuestion(String type,String content,String id,String river2,String coast2,String reach2,String area2,String position,String areaname,String streetname,String villagename,String limitTime,HttpSession session){
		SwjQuestionBean bean = questionService.findById(id);
		bean.setQuestiontype(type);
		bean.setQuestioncontent(content);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date;
		try {
			date = format.parse(limitTime);
			String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
			String role = "";
			SwjUserBean uBean = userService.findByUserId(userId);
			Set<RoleBean> beans =ucs.getUserRole(uBean.getLoginname());
			if(beans != null){
				System.out.println("123");
				for(RoleBean rBean : beans){
					if (rBean.getName().equals("areaRole")) {//区级河长  
						bean.setStreetLimitDate(date);
						if(villagename != null && !villagename.equals("")){
							bean.setIscross("0");
						}
						break;
					}else if(rBean.getName().equals("streetRole")){
						bean.setVillageLimitDate(date);
						break;
					}else if(rBean.getName().equals("villageRole")){
						role = "villageRole";
						break;
					}else{
						bean.setAreaLimitDate(date);
					}
				}
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
	 *是否为越级下发的投诉
	 *@param qId  问题id
	 * */
	@RequestMapping(value="getIscross")
	public @ResponseBody String getIscross(String qId,HttpSession session){
		String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
		SwjUserBean uBean = userService.findByUserId(userId);
		Set<RoleBean> beans =ucs.getUserRole(uBean.getLoginname());
		if(beans != null){
			for(RoleBean rBean : beans){
				if(rBean.getName().equals("streetRole")){
					String isFlag = questionService.getIscross(qId);
					if(isFlag.equals("succ")){
						return "succ";
					}
				}
			}
		}
		return "fail";
	}
	
	/**
	 * 返回字符串中出现指定字符的次数
	 * @param one 要查找的字符串
	 * @param tow  需要统计的字符串
	 * */
	public int stringFindString(String one, String two) {
        int count = 0;  
        //一共有str的长度的循环次数  
        for(int i=0; i<one.length() ; ){  
            int c = -1;  
            c = one.indexOf(two);  
            //如果有S这样的子串。则C的值不是-1.  
            if(c != -1){  
                //这里的c+1 而不是 c+ s.length();这是因为。如果str的字符串是“aaaa”， s = “aa”，则结果是2个。但是实际上是3个子字符串  
                //将剩下的字符冲洗取出放到str中  
            	one = one.substring(c + 1);  
                count ++;  
            }  
            else {  
                System.out.println("没有");  
                break;  
            }  
        }  
        System.out.println(count);
        return count;
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
			System.out.println(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeResponseWriter(response);
		}
	}
}
