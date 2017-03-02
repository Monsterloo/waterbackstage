package com.hhh.fund.waterwx.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhh.fund.util.HttpUtil;
import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjEvaluation;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.model.AccessToken;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAttachmentService;
import com.hhh.fund.waterwx.service.SwjEvaluationService;
import com.hhh.fund.waterwx.service.SwjQuestionItemService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjUcenterRoleService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.util.CacheDataUtils;
import com.hhh.fund.waterwx.util.ComplainConstants;
import com.hhh.fund.waterwx.util.Constants;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.util.JsSDKConfig;
import com.hhh.fund.waterwx.util.SendMessageUtil;
import com.hhh.fund.waterwx.util.StringUtil;
import com.hhh.fund.waterwx.util.WeiXinDownloadUtil;
import com.hhh.fund.waterwx.webmodel.ComplainListJsonBean;
import com.hhh.fund.waterwx.webmodel.ListJsonBean;
import com.hhh.fund.waterwx.webmodel.MyReplyBean;
import com.hhh.fund.waterwx.webmodel.PhotoListBean;
import com.hhh.fund.waterwx.webmodel.PictureUrlBean;
import com.hhh.fund.waterwx.webmodel.RepayJsonBean;
import com.hhh.fund.waterwx.webmodel.RepayJsonBeanList;
import com.hhh.fund.waterwx.webmodel.ReplyJsonBean;
import com.hhh.fund.waterwx.webmodel.SwjComplainBean;
import com.hhh.fund.waterwx.webmodel.SwjEvaluationBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionItemBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterRoleBean;
import com.hhh.fund.waterwx.webmodel.ToFrontWeiXinUserBean;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.MessageUtil;
import com.hhh.weixin.util.QiyehaoConst;



@Controller
@RequestMapping("/weixinRiverMaster")
public class WeixinRiverMasterController {

	public static final String RETURN_NULL = "RequestSuccessfulNull";
	
	public static final String TESTOPENID = "402882b257663a370157663b3a9d0001";
	
	public static final boolean testOrNot = false;
	
	public static final String ROLE = "swj";
	
	
	@Autowired
	private SwjUserService userService;
	
	@Autowired
	private SwjQuestionService questionService;
	
	@Autowired
	private RiverService riverService;
	
	@Autowired
	private SwjQuestionItemService swjQuestionItemService;
	
	@Autowired
	private SwjUcenterRoleService swjUcenterRoleService;
	
	@Autowired
	private ResponsibilityService responsibilityService;
	@Autowired
	private SwjAttachmentService swjAttachmentService;
	
	
	@Autowired
	private SwjQuestionItemService questionItemService;
	
	@Autowired
	private SwjEvaluationService evaluationService;
	
	@RequestMapping("/toMain")
	public String toMain(){
		return "/rivermaster/demo-home";
	}
	
	 /**
	 * 市级分派区治水办处理并保存行政区域
	 */
	@RequestMapping(value="cityAcceptComplain")
	public  void cityAcceptComlain(String importance,String areaAssignedcode,String areaLimitDate,String complainId,String areaName,String remark,String quesitonType,String ciryAssignedcode,HttpServletRequest request, HttpServletResponse response){
		//String openId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_OpenId));
		String returnStr = "RequestSuccessful0";
		String userId=this.getUserId(request);
		SwjUserBean user = userService.findByUserId(userId);
		try{
				SysUcenterRoleBean roleBean = swjUcenterRoleService.findByName("areaRole");
				SwjQuestion bean = questionService.findQuestionById(complainId);
				String openId = bean.getOpenid();
				bean.setDealmanid(roleBean.getId());
				bean.setArea(areaName);
				bean.setStatus(ComplainConstants.solve);
				bean.setRemark(remark);
				bean.setQuestiontype(quesitonType);
				System.out.println(DateUtils.StringToDate_14a(areaLimitDate));
				bean.setAreaLimitDate(DateUtils.StringToDate_14a(areaLimitDate));
				bean.setCiryAssignedcode(ciryAssignedcode);//局
				bean.setAreaAssignedcode(areaAssignedcode);
				bean.setImportance(importance);
				//bean.setAssignedcode(assignedcode);
				questionService.saveQuestion(bean);
				
				String message="[广州市河涌中心]您好！您的投诉我们已转交"+areaName+"水务部门跟进处理！如有任何疑问可随时与我们沟通联系。";
				
				SwjQuestionItem item = new SwjQuestionItem();
				item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				item.setType("log");
				item.setDataId(complainId);
				item.setStatus(0);
				item.setOpenId("USERID_"+userId);
				item.setToOpenId(openId);
				item.setContent(message);
				swjQuestionItemService.save(item);
				
				SwjQuestionItem items = new SwjQuestionItem();
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle("【广州市河涌中心】分派至【"+areaName+"治水办】处理");
				items.setContent("【广州市河涌中心】受理人【"+user.getName()+"】："+remark);
				swjQuestionItemService.save(items);
				
				
				SendMessageUtil.sendMessage(openId, request);
				returnStr = "RequestSuccessful1";
				
				}catch(Exception e){
					outPrintResult(response, returnStr);
				}finally{
					outPrintResult(response, returnStr);
				}
	}

	/**
	 * 用于投诉对应item表所有信息
	 * @param complainId  投诉Id
	 * @return  json，值：RequestSuccessful
	 */
	@RequestMapping("/getRemark")
	public void getRemark(String complainId,HttpServletResponse response){
		String returnStr = RETURN_NULL;
		try{
			List<SwjQuestionItems> items = questionItemService.findByDataId(complainId);
			ListJsonBean bean = new ListJsonBean();
			changeTimeForm(items);
			bean.setList(items);
			bean.setCount(items.size());
			returnStr = JSONObject.fromObject(bean).toString();
			System.err.println(returnStr);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			outPrintResult(response, "RequestSuccessful"+returnStr);
		}
	}
	
	private void changeTimeForm(List<SwjQuestionItems> items) {
		for(SwjQuestionItems item:items){
			item.setCreateTime(DateUtils.changeToTimeDesc_CN(item.getCreateTime()));
		}
	}

	 /**
	 * 区级受理 并保存河涌、河段、左右岸
	 * @param action :  next 分派镇街河长； cityDepartment 移交市职能； areaDepartment  移交区职能  ；acceptSelf 本级处理 
	 */
	@RequestMapping(value="areaAcceptComplain")
	public  void areaAcceptComplain(String complainId,String action,String riverName,String riverCode,String reachName,String riverBankName,String department,String remark,HttpServletRequest request, HttpServletResponse response){
		String userId=this.getUserId(request);
		SwjUserBean user = userService.findByUserId(userId);
		SwjQuestionItem item = new SwjQuestionItem();
		SwjQuestionItem items = new SwjQuestionItem();
		String returnStr = "RequestSuccessful0";
		SwjQuestion bean = questionService.findQuestionById(complainId);
		String area=bean.getArea();
		String openId = bean.getOpenid();
		String setStreetPersonName="";
		//HttpSession session = request.getSession();
		//String openId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_OpenId));
		try{
			if(action.equals("next")){
					setStreetPersonName= responsibilityService.findByParameter(riverCode,reachName,riverBankName);
				if(StringUtils.isBlank(setStreetPersonName)||setStreetPersonName==null){
					returnStr="RequestSuccessful该河涌对应河段尚未添加河长，不能分派!";
				}else{
					//String setStreetPersonName = responsibility.get(0).toString();
					//SysUcenterRoleBean roleBean = swjUcenterRoleService.findByName("streetRole");
					List<SwjUserBean> userBean = userService.getAllToExport(bean.getArea(),setStreetPersonName);
					if(userBean==null||userBean.size()==0){
						returnStr="RequestSuccessful该河涌对应河段河长尚未关注三级河长公众号，不能分派!";
					}else{
					String roleId = swjUcenterRoleService.findByName("areaRole").getId();
					bean.setReachname(riverName);
					bean.setPartName(reachName);
					bean.setRiverCode(riverCode);
					bean.setLeftRight(riverBankName);
					bean.setStatus(ComplainConstants.solve);
					bean.setAreaPersonId(roleId);
					//bean.setStreetPersonId(userBean==null||userBean.size()==0?"":userBean.get(0).getUserid());
					//bean.setStreetPersonName(setStreetPersonName);
					bean.setDealmanid(userBean.get(0).getUserid());
					bean.setRemark(remark);
					questionService.saveQuestion(bean);
					
					String message="["+bean.getArea()+"治水办]您好！您反映的问题我们已到现场核查，该问题我们已经安排"+riverName+""
							+ "河长负责跟进处理(或回复工作计划),计划20天内处理完毕,感谢您对广州治水工作的支持。";
					
					item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
					item.setType("log");
					item.setDataId(complainId);
					item.setStatus(0);
					item.setOpenId("USERID_"+userId);
					item.setToOpenId(openId);
					item.setContent(message);
					swjQuestionItemService.save(item);
					
					items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
					items.setType("remark");
					items.setDataId(complainId);
					items.setStatus(0);
					items.setOpenId("USERID_"+userId);
					items.setToOpenId(null);
					items.setTitle("【"+area+"治水办】分派至【镇街河长"+setStreetPersonName+"】处理");
					items.setContent("【"+area+"治水办】受理人【"+user.getName()+"】："+remark);
					swjQuestionItemService.save(items);
					
	
					
					SendMessageUtil.sendMessage(openId, request);
					returnStr = "RequestSuccessful1";
					}
				}
			}
			if(action.equals("cityDepartment")){
				//SysUcenterRoleBean roleBean = swjUcenterRoleService.findByName("cityDepartment");
				String roleId = swjUcenterRoleService.findByName("cityDepartment").getId();
				bean.setStatus(ComplainConstants.follow);
				bean.setDealmanid(roleId);
				bean.setDepartmentPersonId(roleId);
				bean.setRiverCode(riverCode);
				bean.setReachname(riverName);
				bean.setPartName(reachName);
				bean.setLeftRight(riverBankName);
				bean.setDepartmentPersonName(department);
				bean.setRemark(remark);
				questionService.saveQuestion(bean);
				
				String message="["+bean.getArea()+"治水办]您好！您反映的问题我们已经联系"+department+"处理，感谢您对广州治水工作的支持。";
				
				item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				item.setType("log");
				item.setDataId(complainId);
				item.setStatus(0);
				item.setOpenId("USERID_"+userId);
				item.setToOpenId(openId);
				item.setContent(message);
				swjQuestionItemService.save(item);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle("【"+area+"治水办】分派至【"+department+"】处理");
				items.setContent("【"+area+"治水办】受理人【"+user.getName()+"】："+remark);
				swjQuestionItemService.save(items);
				
				SendMessageUtil.sendMessage(openId, request);
				returnStr = "RequestSuccessful1";
			}
			if(action.equals("areaDepartment")){
				//SysUcenterRoleBean roleBean = swjUcenterRoleService.findByName("areaDepartment");
				String roleId = swjUcenterRoleService.findByName("areaDepartment").getId();
				String arearoleId = swjUcenterRoleService.findByName("areaRole").getId();
				bean.setStatus(ComplainConstants.follow);
				bean.setAreaPersonId(arearoleId);
				bean.setDepartmentPersonId(roleId);
				bean.setDealmanid(roleId);
				bean.setDepartmentPersonId(roleId);
				bean.setDepartmentPersonName(department);
				bean.setRiverCode(riverCode);
				bean.setReachname(riverName);
				bean.setPartName(reachName);
				bean.setLeftRight(riverBankName);
				bean.setRemark(remark);
				questionService.saveQuestion(bean);
				
				String message="["+bean.getArea()+"治水办]您好！您反映的问题我们已经联系"+department+"处理，感谢您对广州治水工作的支持。";
				
				item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				item.setType("log");
				item.setDataId(complainId);
				item.setStatus(0);
				item.setOpenId("USERID_"+userId);
				item.setToOpenId(openId);
				item.setContent(message);
				swjQuestionItemService.save(item);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle("【"+area+"治水办】分派至【"+department+"】处理");
				items.setContent("【"+area+"治水办】受理人【"+user.getName()+"】："+remark);
				swjQuestionItemService.save(items);
				
				SendMessageUtil.sendMessage(openId, request);
				returnStr = "RequestSuccessful1";
			}
			if(action.equals("acceptSelf")){
				String roleId = swjUcenterRoleService.findByName("areaRole").getId();
				bean.setAreaPersonId(roleId);
				bean.setDealmanid("");
				bean.setRemark(remark);
				questionService.saveQuestion(bean);
				
				String message="["+bean.getArea()+"治水办]您好！您反映的问题正在进行处理，计划10天内处理完毕，感谢您对广州治水工作的支持。";
				
				item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				item.setType("log");
				item.setDataId(complainId);
				item.setStatus(0);
				item.setOpenId("USERID_"+userId);
				item.setToOpenId(openId);
				item.setContent(message);
				swjQuestionItemService.save(item);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle("【"+area+"治水办】本级处理");
				items.setContent("【"+area+"治水办】受理人【"+user.getName()+"】："+remark);
				swjQuestionItemService.save(items);
				
				SendMessageUtil.sendMessage(openId, request);
				returnStr = "RequestSuccessful1";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			outPrintResult(response, returnStr);
		}
	}

	/**
	 * 退回受理
	 * @param action:  cityBack 市打回群众,  areaBack 区打回市  streetBack  镇街打回区  villageBack 村居打回区 departmentBack 区属职能部门打回区
	 * @param remark 打回备注
	 */
	@RequestMapping(value="acceptByBack")
	public void acceptByBack(String complainId,String action,String remark,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		SwjQuestionItem item = new SwjQuestionItem();
		SwjQuestionItem items = new SwjQuestionItem();
		String userId=this.getUserId(request);
		SwjUserBean user = userService.findByUserId(userId);
		String openId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_OpenId));
		SwjQuestion bean = questionService.findQuestionById(complainId);
		String comOpenId=bean.getOpenid();
		String returnStr = "RequestSuccessful0";
		try{
			if(action.equals("cityBack")){
				String message="[广州市河涌中心]您好！您的投诉已被退回。原因："+remark;
				SysUcenterRoleBean roleBean = swjUcenterRoleService.findByName("swj");
				String role = roleBean.getId();
				questionService.updateStatus(complainId,ComplainConstants.end);
				item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				item.setType("log");
				item.setDataId(complainId);
				item.setStatus(0);
				item.setOpenId("USERID_"+userId);
				item.setToOpenId(comOpenId);
				item.setContent(message);
				swjQuestionItemService.save(item);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle("【广州市河涌中心】退回投诉至投诉人");
				items.setContent("【广州市河涌中心】受理人【"+user.getName()+"】："+remark);
				swjQuestionItemService.save(items);
				
				SendMessageUtil.sendMessage(comOpenId, request);
				returnStr = "RequestSuccessful1";
			}
			if(action.equals("areaBack")){
				String roleId = swjUcenterRoleService.findByName("swj").getId();
				bean.setArea("");
				bean.setAreaPersonId("");
				bean.setStatus(ComplainConstants.accept);
				bean.setDealmanid(roleId);
				questionService.saveQuestion(bean);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle("【"+user.getArea()+"区治水办】退回投诉至广州市河涌中心");
				items.setContent("【"+user.getArea()+"区治水办】受理人【"+user.getName()+"】："+remark);
				swjQuestionItemService.save(items);
				
				returnStr = "RequestSuccessful1";
			}
			if(action.equals("streetBack")){
				String role = swjUcenterRoleService.findByName("areaRole").getId();
				bean.setDealmanid(role);
				questionService.saveQuestion(bean);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle(user.getArea()+"镇街河长退回至区治水办");
				items.setContent(user.getName()+"备注："+remark);
				swjQuestionItemService.save(items);
				
				returnStr = "RequestSuccessful1";
			}
			if(action.equals("villageBack")){
				String roleId = swjUcenterRoleService.findByName("areaRole").getId();
				bean.setDealmanid(roleId);
				bean.setVillagePersonId("");
				bean.setStreetPersonId("");
				questionService.saveQuestion(bean);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle(user.getArea()+"村居河长退回至区治水办");
				items.setContent(user.getName()+"备注："+remark);
				swjQuestionItemService.save(items);
				
				returnStr = "RequestSuccessful1";
			}
			if(action.equals("departmentBack")){
				String role = swjUcenterRoleService.findByName("areaRole").getId();
				String departmentPersonName = bean.getDepartmentPersonName();
				bean.setDealmanid(role);
				questionService.saveQuestion(bean);
				
				items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
				items.setType("remark");
				items.setDataId(complainId);
				items.setStatus(0);
				items.setOpenId("USERID_"+userId);
				items.setToOpenId(null);
				items.setTitle(user.getArea()+departmentPersonName+"退回至区治水办");
				items.setContent(remark);
				swjQuestionItemService.save(items);
				
				returnStr = "RequestSuccessful1";
			}
		}catch(Exception e){
			outPrintResult(response, returnStr);
		}finally{
			outPrintResult(response, returnStr);
		}
	}
	
	/**
	 * 用于保存完结申请
	 * 
	 * @param complainId
	 *            投诉Id
	 * @param imgWinxinId
	 *            图片在微信服务器的Id
	 * @param isJoinActivity
	 *            是否参与活动
	 * @return string，值：RequestSuccessful1/0 描述：1表示成功 0表示失败
	 */
	@RequestMapping("/saveFinishApply")
	public void saveFinishApply(String complainId, String serverId,String content, HttpServletResponse response,HttpServletRequest request) {
		System.out.println(complainId + ":" + serverId + ":" + content);
		String returnStr = "RequestSuccessful0";
		SwjQuestionItem items = new SwjQuestionItem();
		String userId = this.getUserId(request);
		SwjUserBean user = userService.findByUserId(userId);
		try {
			String openId = "";
			HttpSession session = request.getSession();

			// 防止重复提交
			String isReapetSubmit = (String) request.getSession().getAttribute("IS_REAPET_SUBMIT");// title_serverId
			serverId = serverId == null ? "" : serverId;
			complainId = complainId == null ? "" : complainId;
			if ((serverId + "_" + complainId).equals(isReapetSubmit)) {
				return;
			} else {
				request.getSession().setAttribute("IS_REAPET_SUBMIT",(serverId + "_" + complainId));
			}

			// 判断角色
			SysUcenterRoleBean RoleBean = swjUcenterRoleService.findRoleByUserId(userId);
			String name = RoleBean.getName();
			String desp = RoleBean.getDesp();
			if (("swj").equals(name) || ("areaRole").equals(name)|| ("areaDepartment").equals(name)) {
				openId = RoleBean.getId();
			} else {
				openId = getOpenId(request);
			}

			// 修改投诉状态
			SwjQuestion bean = questionService.findQuestionById(complainId);
			bean.setStatus(ComplainConstants.finish);
			bean.setDealmanid(swjUcenterRoleService.findByName("areaRole").getId());
			questionService.saveQuestion(bean);

			// 评价
			SwjEvaluation eval = new SwjEvaluation();
			eval.setCreateTime(new Date());
			eval.setQuestionId(complainId);
			eval.setRemark(content);
			eval.setStatus(0);
			eval.setApplicationPersonId(userId);
			String evalId = evaluationService.save(eval);

			if (!StringUtil.isBlank(serverId)) {
				String[] servers = serverId.split(",");

				System.out.println("上传servierId:" + servers);

				// 从微信下载图片到磁盘，到数据库
				for (String ser_id : servers) {saveFileIntoDataBaseAndDisk(ser_id, request, response, "",evalId);
				}
			}

			items.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
			items.setType("remark");
			items.setDataId(complainId);
			items.setStatus(0);
			items.setOpenId("USERID_" + userId);
			items.setToOpenId(null);
			items.setTitle("【" + user.getArea() + desp + "】投诉申请完结");
			items.setContent("【" + user.getArea() + desp + "】申请人【"+ user.getName() + "】：" + content);
			swjQuestionItemService.save(items);

			returnStr = "RequestSuccessful1";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outPrintResult(response, returnStr);
		}

	}

	/**
	 * 用于获取完结申请信息
	 * 
	 * @param complainId
	 *            投诉Id
	 * @return json，值：RequestSuccessful{}
	 */
	@RequestMapping("/getFinishApplyInfo")
	public void getFinishApplyInfo(String complainId,
			HttpServletResponse response, HttpServletRequest request) {
		String returnStr = RETURN_NULL;
		try {
			SwjEvaluationBean bean = evaluationService.findByFinishAyyplyId(complainId, request);
			if (bean != null) {
				returnStr = "RequestSuccessful"+ JSONObject.fromObject(bean).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outPrintResult(response, returnStr);
		}
	}

	/**
	 * 从微信下载图片到磁盘，到数据库
	 */
	private boolean saveFileIntoDataBaseAndDisk(String ser_id,
			HttpServletRequest request, HttpServletResponse response,
			String questionId, String applicationId) {
		try {
			String path = request.getRealPath("/img/pics");
			String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+ CommonUtil.getToken() + "&media_id=" + ser_id;
			System.out.println("url>>>>>>>>>>>>>" + url);
			byte[] bufs = WeiXinDownloadUtil.getBytes((WeiXinDownloadUtil.getInputStream(CommonUtil.getToken(), ser_id)));
			SwjAttachment attachment = new SwjAttachment();
			if (StringUtils.isNotBlank(questionId)) {
				attachment.setQuestionId(questionId);
			}
			if (StringUtils.isNotBlank(applicationId)) {
				attachment.setApplicationId(applicationId);
			}

			attachment.setFile(bufs);
			String attaId = swjAttachmentService.save(attachment);
			System.out.println("保存图片成功！！！");
			// 生成缩略图
			IOUtils.generateSmallImage(bufs, path + File.separator + (attaId)+ ".jpeg", attaId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 读取缩略图
	 */
	@RequestMapping("/getLittlePicById")
	public void getAttchmentImageByIdFromDisk(HttpServletResponse response,String id, HttpServletRequest request) {
		String path = request.getRealPath(SwjAttachment.DiskPath);
		File file = new File(path + File.separator + id + ".jpeg");
		OutputStream out = null;
		if (file.exists()) {
			try {
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				int size = in.available();
				byte[] fileBytes = new byte[size];
				int endflag = 0;
				while ((endflag = in.read(fileBytes)) != -1) {
					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);
					response.setContentType("image/jpeg");
					out = response.getOutputStream();
					IOUtils.byte2image(fileBytes, out);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			IOUtils.byte2image(new byte[] {}, out);
		}
	}

	private String getOpenId(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(
				QiyehaoConst.Weixin_OpenId) == null ? "" : (String) request.getSession().getAttribute(QiyehaoConst.Weixin_OpenId);
	}


	/**
	 * 用于保存回复
	 * 
	 * @param complainId
	 *            投诉Id
	 * @param reply
	 *            评论内容
	 * @return 值：RequestSuccessful1/0 1表示成功 0表示失败
	 */
	@RequestMapping("/saveReply")
	public String saveReply(String questionId, String replyContent,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			// String openId = this.getOpenId(request);
			SwjQuestion question = questionService.findById_(questionId);
			SwjQuestionItem item = new SwjQuestionItem();
			item.setDataId(questionId);
			item.setCreateTime(DateUtils.DateToString_14a(new java.util.Date()));
			item.setType("reply");
			item.setStatus(0);
			item.setOpenId("USERID_"+ String.valueOf(request.getSession().getAttribute(QiyehaoConst.Weixin_UserId)));
			item.setToOpenId(question.getOpenid());
			item.setContent(replyContent);
			swjQuestionItemService.saveReply(item);

			SendMessageUtil.sendMessage(question.getOpenid(), request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/weixinRiverMaster/getRepay?complainId=" + questionId;

	}

	/**
	 * 用于获取评论列表
	 * 
	 * @param complainId
	 *            投诉Id
	 * @return json，值：RequestSuccessful
	 */
	@RequestMapping("/getRepay")
	public void getRepay(String complainId, HttpServletResponse response,
			HttpServletRequest request) {
		String returnStr = RETURN_NULL;
		try {
			List<SwjQuestionItem> items = swjQuestionItemService.findByDataIdAndType(complainId, "reply");

			List<RepayJsonBean> list = new ArrayList<RepayJsonBean>();
			RepayJsonBeanList beanList = new RepayJsonBeanList();
			String access_token = CommonUtil.getToken();
			for (SwjQuestionItem item : items) {
				list.add(changeQuestionItemToRepayJsonBean(item, access_token));
			}
			beanList.setList(list);
			beanList.setCount(list.size());
			returnStr = "RequestSuccessful"+ JSONObject.fromObject(beanList).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outPrintResult(response, returnStr);
		}
	}

	private RepayJsonBean changeQuestionItemToRepayJsonBean(SwjQuestionItem item, String access_token) throws Exception {
		RepayJsonBean bean = new RepayJsonBean();
		bean.setComplainDate(DateUtils.changeToTimeDesc_CN(item.getCreateTime()));
		bean.setLevel(ToFrontWeiXinUserBean.changeScoreToLevel(item.getWeixinUser() == null ? "0" : item.getWeixinUser().getScore()));

		// 如果item表中open_id也就是回复人，包含USERID，说明是治水办责任人
		if (StringUtils.isNotBlank(item.getOpenId())&& StringUtils.isContains(item.getOpenId(), "USERID_")) {
			String userId = item.getOpenId().replace("USERID_", "");
			String weixin_info_url = Constants.GET_RIVERRESPONSIBLE_WEIXIN_INFO.replace("ACCESS_TOKEN", access_token).replace("USERID",userId);
			JSONObject jsonObject = HttpUtil.sendHttps(weixin_info_url, "GET",null);
			String name = jsonObject.getString("name");
			String headImgHref = jsonObject.getString("avatar");
			String mobile = jsonObject.getString("mobile");

			bean.setName(StringUtils.isBlank(userService.getDepartmentByUserId(userId)) ? name : "["+ userService.getDepartmentByUserId(userId) + "]" + name);
			bean.setPhoto(headImgHref);
			bean.setPhoneNum(mobile);
			bean.setShowPhone("1");
			// bean.setPhoneHtml("<a href=\"http://tel:'+json.mobile+'/><span  class=\"complain-message-reply-item-name\" style=\"width:20px;height:20px;\"><img style=\"width:20px;height:20px;\" src=\"http://wx1.ccqm.cn/water/assets/Include/image/op-call.png\" class=\"flow-currentpeople-op-img\"/></span></a>");
		} else {
			bean.setName(item.getWeixinUser() == null ? "" : item.getWeixinUser().getName());
			bean.setPhoto(item.getWeixinUser() == null ? "" : item.getWeixinUser().getPhotoHref());
		}
		bean.setQuestionId(item.getDataId());
		bean.setReplay(item.getContent());
		bean.setReplayId(item.getId());

		String createTime = item.getCreateTime();
		bean.setTimeDesc(DateUtils.changeToTimeDesc(createTime));
		bean.setOpenId(item.getOpenId());
		return bean;
	}

	private Map<String, String> getMyActionJsonMap(String str) {
		// 就是这条数据
		// {"questionId":"fdjsfkdsjfdsj","openId":"oVeWVuKUYvroXvcliGUhUoMD76Co","myAttention":"1","myPraise":"1","myRepost":"1"}
		JSONObject obj = JSONObject.fromObject(str);
		// String[] datalines = str.split(",");
		String myAttention = obj.getString("myAttention").trim();
		String myPraise = obj.getString("myPraise").trim();
		String myRepost = obj.getString("myRepost").trim();
		String questionIdStr = obj.getString("questionId").trim();
		String createTime = obj.getString("createTime").trim();
		String openId = obj.getString("openId").trim();
		Map<String, String> userAction = new HashMap<String, String>();
		userAction.put("myAttention", myAttention);
		userAction.put("myPraise", myPraise);
		userAction.put("myRepost", myRepost);
		userAction.put("questionId", questionIdStr);
		userAction.put("createTime", createTime);
		userAction.put("openId", openId);

		return userAction;
	}

	public String getUserId(HttpServletRequest request){
		return String.valueOf(request.getSession().getAttribute(QiyehaoConst.Weixin_UserId));
		
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

