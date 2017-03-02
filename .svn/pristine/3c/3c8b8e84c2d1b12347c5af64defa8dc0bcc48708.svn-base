package com.hhh.fund.waterwx.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.HttpUtil;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.util.StringUtils;
import com.hhh.fund.util.WebUtil;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.entity.SwjWxfollowuserinfo;
import com.hhh.fund.waterwx.model.AccessToken;
import com.hhh.fund.waterwx.model.Menu;
import com.hhh.fund.waterwx.service.MenuService;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjSignInService;
import com.hhh.fund.waterwx.service.SwjWeixinUserService;
import com.hhh.fund.waterwx.service.SwjWxexportUserService;
import com.hhh.fund.waterwx.service.SwjWxfollowUserInfoService;
import com.hhh.fund.waterwx.service.WechatService;
import com.hhh.fund.waterwx.util.Constants;
import com.hhh.fund.waterwx.util.EmojiFilter;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.util.JacksonUtils;
import com.hhh.fund.waterwx.util.JsSDKConfig;
import com.hhh.fund.waterwx.util.PlatformUtil;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjWeiXinUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxfollowuserinfoBean;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/weixin")
public class WeixinController {

	@Autowired
	private WechatService wechatService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private SwjQuestionService questService;
	@Autowired
	private SwjSignInService signInService;
	@Autowired
	private SwjAnswerService swjAnswerService;
	@Autowired
	private SwjSignInService signinImgService;
	@Autowired
	private ResponsibilityService responsibilityService;
	@Autowired
	private SwjWxfollowUserInfoService followUserInfoService;
	@Autowired
	private SwjWxexportUserService exportUserService;
	@Autowired
	private UserCenterService ucs;
	@Autowired
	private RiverService riverService;
	@Autowired
	private SwjWeixinUserService weixinUserService;
	
	/**
	 * 导出红包用户刷新
	 * @return 
	 */
	@RequestMapping(value = "/getGiftingMoneyUser")
	public @ResponseBody List<Object> getGiftingMoneyUser() {
		long start = System.currentTimeMillis();
		followUserInfoService.deleteFollowUserTable();
		List<Object> list= new ArrayList<Object>();
		list.add("success");
		String next_openid="";
		boolean result = exportWechatUser(next_openid);
		if(result){
			exportUserService.deleteexportUserTable();
			List<SwjWxfollowuserinfo> list1=followUserInfoService.findGiftingMoneyUser();
			List<SwjQuestion> list2=followUserInfoService.findGiftingMoneyUserQuestionId();
			if(list1.size()==0&&list2.size()==0){
				list.clear();
				list.add("null");
			}
			for (int i = 0; i < list2.size(); i++) {
				SwjWxexportUserBean exportUser = new SwjWxexportUserBean();
				exportUser.setOpenid(list1.get(i).getOpenid());
				exportUser.setNickname(list1.get(i).getNickname());
				exportUser.setHeadimgurl(list1.get(i).getHeadimgurl());
				for (int j = 0; j < list2.size(); j++) {
					exportUser.setQuestionid(list2.get(i).getId());
					followUserInfoService.saveExportUser(exportUser);
				}
			}
		}else{
			list.clear();
			list.add("false");
		}
		long end = System.currentTimeMillis();
		System.out.println("运行时间-------------------->"+(end-start));
		return list;
	}
	
	/**
	 * 服务号发红包用户导出
	 * @param next_openid 
	 */
	@SuppressWarnings("unchecked")
	public boolean exportWechatUser(String next_openid) {
		boolean result=true;
		List<Object> list= new ArrayList<Object>();
		SwjWxfollowuserinfoBean fuser = new SwjWxfollowuserinfoBean();
		Map<String, Object> openIdMap = new HashMap<String, Object>();
		JSONObject data = PlatformUtil.getFollowUserInfo(next_openid);
		if(data.containsKey("errcode")){
			System.out.println("获取用户列表错误"+data);
			result=false;
		}else{
		String count= data.getString("count");
		String total= data.getString("total");
		next_openid= data.getString("next_openid");
		if(!"10000".equals(count)&&!"".equals(next_openid)){
			String openidlist= data.getString("data");
			try {
				List<Map<String, Object>> b = new ArrayList<Map<String, Object>>();  
				Map<String,Object> c=new HashMap<String,Object>();
				openIdMap = JacksonUtils.json2map(openidlist);
				list =(List<Object>) openIdMap.get("openid");
				if(list.size()>40){
					getFollowUserInfoList2(list);
				}else{
					for(int i=0;i<list.size();i++){
						String openid=list.get(i).toString();
						Map<String,Object> a=new HashMap<String,Object>();
						a.put("openid", openid);
						a.put("lang", "zh-CN");
						b.add(a);
					}
					c.put("user_list", b);
					String map2json = JacksonUtils.map2josn(c);
					JSONObject jsonObject= PlatformUtil.getFollowUserInfoList(map2json);
					String asd = jsonObject.toString();
					Map<String, Object> user_infoMap = JacksonUtils.json2map(asd);
					List<HashMap<String,Object>> list1=(List<HashMap<String,Object>>) user_infoMap.get("user_info_list");
					for(int i=0;i<list1.size();i++){
						 fuser.setOpenid(list1.get(i).get("openid").toString());
						 fuser.setNickname(EmojiFilter.filterEmoji(list1.get(i).get("nickname").toString()));
						 fuser.setHeadimgurl(list1.get(i).get("headimgurl").toString());
						 followUserInfoService.save(fuser);
					}
				   /* JSONObject jsonObject= PlatformUtil.getUserInfo(openid);
				    String nickname= jsonObject.getString("nickname");
				    String headimgurl= jsonObject.getString("headimgurl");
				    String replacenickname = EmojiFilter.filterEmoji(nickname);
				    fuser.setOpenid(openid);
				    fuser.setNikename(replacenickname);
				    fuser.setHeadimgurl(headimgurl);
				    followUserInfoService.save(fuser);*/
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("10000".equals(count)&&!"".equals(next_openid)){
			String openidlist= data.getString("data");
			try {
				openIdMap = JacksonUtils.json2map(openidlist);
				list = (List<Object>) openIdMap.get("openid");
				getFollowUserInfoList2(list);
				exportWechatUser(next_openid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("已取出所有用户,总计"+total+"个");
			return result;
		}
		return result;
	   }
		return result;
	}
	
	
	/**
	 * 服务号批量用户查询（大于100名）
	 * @param openidlist 
	 */
	@SuppressWarnings("unchecked")
	public void getFollowUserInfoList2(List<Object> list) {
		
		SwjWxfollowuserinfoBean fuser = new SwjWxfollowuserinfoBean();
			try {
					List<Map<String, Object>> b = new ArrayList<Map<String, Object>>();  
					Map<String,Object> c=new HashMap<String,Object>();
					for(int j=0;j<list.size();j++){
						String openid=list.get(j).toString();
						Map<String,Object> a=new HashMap<String,Object>();
						a.put("openid", openid);
						a.put("lang", "zh-CN");
						b.add(a);
						if((j+1)%40==0){
							c.put("user_list", b);
							String map2json = JacksonUtils.map2josn(c);
							JSONObject jsonObject= PlatformUtil.getFollowUserInfoList(map2json);
							String asd = jsonObject.toString();
							Map<String, Object> user_infoMap = JacksonUtils.json2map(asd);
							List<HashMap<String,Object>> list1=(List<HashMap<String,Object>>) user_infoMap.get("user_info_list");
							for(int i=0;i<list1.size();i++){
								 fuser.setOpenid(list1.get(i).get("openid").toString());
								 fuser.setNickname(EmojiFilter.filterEmoji(list1.get(i).get("nickname").toString()));
								 fuser.setHeadimgurl(list1.get(i).get("headimgurl").toString());
								 followUserInfoService.save(fuser);
							}
							b.clear();
							c.clear();
						}
					}
							c.put("user_list", b);
							String map2json = JacksonUtils.map2josn(c);
							JSONObject jsonObject= PlatformUtil.getFollowUserInfoList(map2json);
							String asd = jsonObject.toString();
							Map<String, Object> user_infoMap = JacksonUtils.json2map(asd);
							List<HashMap<String,Object>> list1=(List<HashMap<String,Object>>) user_infoMap.get("user_info_list");
							for(int i=0;i<list1.size();i++){
								 fuser.setOpenid(list1.get(i).get("openid").toString());
								 fuser.setNickname(EmojiFilter.filterEmoji(list1.get(i).get("nickname").toString()));
								 fuser.setHeadimgurl(list1.get(i).get("headimgurl").toString());
								 followUserInfoService.save(fuser);
							}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * 保存服务号关注用户
	 * @return 
	 */
	@RequestMapping(value = "/saveWeixinServerUser")
	public @ResponseBody String saveWeixinServerUser() {
		String next_openid="";
		FollowWeixinUser(next_openid);
		return null;
	}
	
	/**
	 * 服务号已关注用户信息保存
	 * @param next_openid 
	 */
	@SuppressWarnings("unchecked")
	public boolean FollowWeixinUser(String next_openid) {
		boolean result=true;
		List<Object> list= new ArrayList<Object>();
		SwjWeiXinUserBean weixinUser = new SwjWeiXinUserBean();
		Map<String, Object> openIdMap = new HashMap<String, Object>();
		JSONObject data = JsSDKConfig.getFollowUserInfo(next_openid);
		if(data.containsKey("errcode")){
			System.out.println("获取用户列表错误"+data);
			result=false;
		}else{
		String count= data.getString("count");
		String total= data.getString("total");
		next_openid= data.getString("next_openid");
		if(!"10000".equals(count)&&!"".equals(next_openid)){
			String openidlist= data.getString("data");
			try {
				List<Map<String, Object>> b = new ArrayList<Map<String, Object>>();  
				Map<String,Object> c=new HashMap<String,Object>();
				openIdMap = JacksonUtils.json2map(openidlist);
				list =(List<Object>) openIdMap.get("openid");
				int a=0;
				for(int i=0;i<list.size();i++){
				    JSONObject jsonObject= JsSDKConfig.getUserInfo(list.get(i).toString());
				    String openid= jsonObject.getString("openid");
				    String photoHref= jsonObject.getString("headimgurl");
				    if(StringUtils.isBlank(photoHref)){
				    	photoHref="";
				    	photoHref="http://wx1.ccqm.cn/water/assets/Include/image/photo-default.png";
					}
				    String nickname= jsonObject.getString("nickname");
				    String replacenickname = EmojiFilter.filterEmoji(nickname);
				    if(StringUtils.isBlank(replacenickname)){
				    	replacenickname="";
				    	replacenickname="wx_"+System.currentTimeMillis();
					}
				    String city= jsonObject.getString("city");
				    int sex= Integer.parseInt(jsonObject.getString("sex"));
				    weixinUser.setOpenId(openid);
				    weixinUser.setName(replacenickname);
				    weixinUser.setPhotoHref(photoHref);
				    weixinUser.setArea(city);
				    weixinUser.setSex(sex);
				    weixinUserService.save(weixinUser);
				    a+=1;
				}
				System.out.println("已保存"+a+"个用户信息");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("10000".equals(count)&&!"".equals(next_openid)){
			String openidlist= data.getString("data");
			try {
				openIdMap = JacksonUtils.json2map(openidlist);
				list = (List<Object>) openIdMap.get("openid");
				for(int i=0;i<list.size();i++){
				    JSONObject jsonObject= JsSDKConfig.getUserInfo(list.get(i).toString());
				    String headimgurl= jsonObject.getString("headimgurl");
				    if(StringUtils.isBlank(headimgurl)){
				    	headimgurl="";
				    	headimgurl="http://wx1.ccqm.cn/water/assets/Include/image/photo-default.png";
					}
				    String nickname= jsonObject.getString("nickname");
				    String replacenickname = EmojiFilter.filterEmoji(nickname);
				    if(StringUtils.isBlank(replacenickname)){
				    	replacenickname="";
				    	replacenickname="wx_"+System.currentTimeMillis();
					}
				    String city= jsonObject.getString("city");
				    int sex= Integer.parseInt(jsonObject.getString("sex"));
				    weixinUser.setOpenId(list.get(i).toString());
				    weixinUser.setName(replacenickname);
				    weixinUser.setPhotoHref(headimgurl);
				    weixinUser.setArea(city);
				    weixinUser.setSex(sex);
				    weixinUserService.save(weixinUser);
				}
				FollowWeixinUser(next_openid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("已取出所有用户,总计"+total+"个");
			return result;
		}
		return result;
	   }
		return result;
	}
	
	/**
	 * 接收微信的信息
	 */
	@RequestMapping(value = "/executeWechat")
	public void executeWechat(HttpServletRequest request, HttpServletResponse response) {
		String requestMethod = request.getMethod();
		if ("get".equals(requestMethod.toLowerCase())) {
			String result = wechatService.checkDeveloper(request);
			if (!StringUtils.isBlank(result)) {
				WebUtil.wirteJsonDate(response, result);
			}
		} else if ("post".equals(requestMethod.toLowerCase())) {
			String data = wechatService.doPost(request, response);
			if (!StringUtils.isBlank(data)) {
				WebUtil.wirteJsonDate(response, data);
			}
		}
	}

	/**
	 * 要设置微信授权页面域名才行(不用http://、非80要端口?) 认证
	 */
	@RequestMapping(value = "/auth")
	public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String state = request.getParameter("state");
      String code = request.getParameter("code");
      HttpSession session = request.getSession();
      try {
    	    if(!StringUtils.isBlank(code) && !StringUtils.isBlank(state)){
	            String tokenUrl = Constants.TOKEN_URL.replace("CODE", code);
	            JSONObject jsonObject = HttpUtil.sendHttps(tokenUrl, "GET", null);
	            if (jsonObject != null) {
  	              if (jsonObject.getString("openid") == null) { throw new Exception(jsonObject.getString("errmsg")); }
  	              else {
  	                  session.setAttribute(Constants.SESSEION_WECHAT_OPEN_ID, jsonObject.getString("openid"));
  	                  refreshUserInfo(request);
  	              }
	            } else { throw new Exception(" jsonObject is null !");}
    	    } else {
    	        if(request.getRequestURL().indexOf("localhost") != -1 ||
    	                request.getRequestURL().indexOf("192.168.2") != -1) {
    	            //oVeWVuFzf22hsdQXctZ4C9jYX8Ms zeng
    	            //oVeWVuFWL52MPWipFBrgLK09rHi8 chengpao\
    	            //oVeWVuBVMQaxJnphUvBEhin_-DqA
    	        	SwjWeiXinUser i = weixinUserService.findByOpenId("oVeWVuERkuPiQ_qsl4h3xAac12dI");
    	        	session.setAttribute("isNotRemind", i.getIsNotRemind());
    	            session.setAttribute(Constants.SESSEION_WECHAT_OPEN_ID, "oVeWVuERkuPiQ_qsl4h3xAac12dI");
    	            state = "/water/weixin/findbyopenid";
    	        }
    	    }
    	    response.sendRedirect(request.getRequestURL().toString().replace("/water/weixin/auth", state));
      } catch (Exception e) { showException(e,response); }
	}
	
	public void showException(Exception e,HttpServletResponse response){
	    try{
	        System.err.println(e.getMessage());
	        response.sendRedirect(Constants.DOMAIN_NAME + "/error/500.jsp");
	    } catch(Exception ex) { }
	}
	
	public void refreshUserInfo(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    String openId = (String)session.getAttribute(Constants.SESSEION_WECHAT_OPEN_ID);
      JSONObject jsonObject = JsSDKConfig.getUserInfo(openId);
      SwjWeiXinUser userInfo = weixinUserService.getUserByOpenId(openId);
      if(userInfo == null) { userInfo = new SwjWeiXinUser(); }
      
      String headimgurl = jsonObject.getString("headimgurl");
      if( StringUtils.isBlank(headimgurl) ) { headimgurl="http://wx1.ccqm.cn/water/assets/Include/image/photo-default.png"; }
      String nickname= jsonObject.getString("nickname");
      String replacenickname = EmojiFilter.filterEmoji(nickname);
      if( StringUtils.isBlank(replacenickname) ) { replacenickname="wx_"+System.currentTimeMillis(); }
      String city= jsonObject.getString("city");
      int sex= Integer.parseInt(jsonObject.getString("sex"));
      userInfo.setOpenId(openId);
      userInfo.setName(replacenickname);
      userInfo.setPhotoHref(headimgurl);
      userInfo.setArea(city);
      userInfo.setSex(sex);
      session.setAttribute("isNotRemind", userInfo.getIsNotRemind());
      weixinUserService.saveBean(userInfo);  
	}

	/**
	 * 生成微信菜单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/createMenu")
	public void createMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AccessToken token = AccessToken.getAccessToken(request, response);
		Menu menu = menuService.getMenu();
		boolean flag = false;
		int s = menuService.createMenu(menu, token.getToken());
		if (s == 0) {
			flag = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", flag);
		System.err.println("createMenu map---->" + map);

	}
	
	/**
	 * 奖励说明
	 * @return
	 */
	@RequestMapping(value = "/encourage")
	public String encourage() {
		return "weixin/encourage";
	}
	
	/**
	 * 内部通知
	 * @return
	 */
	@RequestMapping(value = "/insidenotice")
	public String insidenotice() {
		return "weixin/insidenotice";
	}

	/**
	 * 我的投诉
	 * @param mode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/complain")
	public String complain(Model mode, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		Map<String, String> map = JsSDKConfig.getSignature(url);
		for (String key : map.keySet()) {
			mode.addAttribute(key, map.get(key));
		}

		return "weixin/index";
	}

	/**
	 * 保存投诉
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/savecomplain")
	public @ResponseBody String saveComplain(HttpServletRequest request, HttpSession session) {
		String serverId = request.getParameter("serverId");
		System.out.println("##############serverId:==" + serverId);
		SwjQuestionBean quest = new SwjQuestionBean();
		JSONObject jsonObject = JsSDKConfig.getUserInfo(String.valueOf(session.getAttribute(Constants.SESSEION_WECHAT_OPEN_ID)));
		String headimgurl= jsonObject.getString("headimgurl");
		String nickname= jsonObject.getString("nickname");
		String replacenickname = EmojiFilter.filterEmoji(nickname);
		quest.setOpenid(String.valueOf(session.getAttribute(Constants.SESSEION_WECHAT_OPEN_ID)));
		quest.setNickname(replacenickname);
		quest.setHeadimgurl(headimgurl);
		quest.setQuestioncontent(request.getParameter("questioncontent"));
		quest.setComplainDate(new Date());
		quest.setX(request.getParameter("x"));
		quest.setY(request.getParameter("y"));
		quest.setArea(request.getParameter("area"));
		quest.setReachname(request.getParameter("reachname"));
		quest.setPhone(request.getParameter("phone"));
		quest.setQuestionposition(request.getParameter("questionposition"));
		quest.setQuestiontype("");
		quest.setStatus(1);
		String id = questService.save(quest);
		JsSDKConfig.download(serverId.split(","), id, questService);
		return "true";
	}
	
	/**
	 * 保存签到
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/savesignin")
	public @ResponseBody String saveSignIn(HttpServletRequest request, HttpSession session) {
		String serverId = request.getParameter("serverId");
		System.out.println("##############serverId:==" + serverId);
		String token = CommonUtil.getToken();
		String url=QiyehaoConst.URL_DOWNLOAD_FILE;
		String userId=String.valueOf(session.getAttribute(QiyehaoConst.Weixin_UserId));
		String signinname=signInService.findUserNameByuserId(userId);
		SwjWxsigninBean signin = new SwjWxsigninBean();
		signin.setUserid(userId);
		signin.setSigninname(signinname);
		signin.setSigninDate(new Date());
		signin.setX(request.getParameter("x"));
		signin.setY(request.getParameter("y"));
		signin.setArea(request.getParameter("area"));
		signin.setGrade(request.getParameter("grade"));
		signin.setReachname(request.getParameter("reachname"));
		signin.setSigninposition(request.getParameter("signinposition"));
		String id = signInService.save(signin);
		signInService.signInImgDownload(serverId.split(","),id,signinImgService);
		return "true";
	}

	/**
	 * 查看照片
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/export/{id}")
	public ResponseEntity<byte[]> export(@PathVariable String id) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "test.jpg");
		SwjAttachment att = questService.getAttachment(id);
		return new ResponseEntity<byte[]>(att.getFile(), headers, HttpStatus.CREATED);
	}

	/**
	 * 查看投诉的详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/questiondetail")
	public String questionInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		SwjQuestionBean question = questService.findById(id);
		// SwjAnswer swjAnswer= null;
		Responsibility res = null;

		List<SwjAttachment> attList = null;
		List<SwjAnswer> swjAnswerList = null;
		if (question != null) {
			String qid = question.getId();
			attList = questService.getAttchId(qid);
			List<Responsibility> resList = questService.findByRiverCodeAndPartName(question.getRiverCode(),
					question.getPartName());
			swjAnswerList = swjAnswerService.findLastAnswerByQuestionId(qid);
			// if(swjAnswerList!=null && swjAnswerList.size()>0){
			// swjAnswer= swjAnswerList.get(0);
			// }
			if (resList != null && resList.size() > 0) {
				res = resList.get(0);
			}

			request.setAttribute("attList", attList);

			request.setAttribute("question", question);

			request.setAttribute("res", res);

			request.setAttribute("swjAnswer", swjAnswerList);
		} else {
			System.err.println("openId is null!");
			return null;
		}
		return "weixin/detail";
	}
	
	/**
	 * 查看签到的详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signindetail")
	public String SigninInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		SwjWxsigninBean signin = signInService.findById(id);
		// SwjAnswer swjAnswer= null;

		List<SwjAttachment> attList = null;
		if (signin != null) {
			String qid = signin.getId();
			attList = questService.getAttchId(qid);
			
			request.setAttribute("attList", attList);

			request.setAttribute("signin", signin);
		} else {
			System.err.println("openId is null!");
			return null;
		}
		return "weixin/signindetail";
	}
	
	/**
	 * 查看河道责任表的详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/responsibilitydetail")
	public String responsibilityDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		String riverCode = request.getParameter("rivercode");
		ResponsibilityBean responsibility = responsibilityService.findById(id);
		//查找出河道信息
		River river=riverService.findByRiverCode(riverCode);
		if (river != null||responsibility!=null) {
			request.setAttribute("responsibility", responsibility);
			request.setAttribute("river", river);
		} else {
			System.err.println("Id is null!");
			return null;
		}

		return "weixin/responsibilitydetail";
	}
	
	/**
	 * 判断微信用户表用户信息是否存在
	 * 
	 * @param request
	 * @return 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/findbyopenid")
	public String FindWeixinUserbyopenId(Model mode,HttpServletRequest request,HttpSession session) throws IOException {
	    String url = request.getRequestURL().toString();
  		Map<String, String> map = JsSDKConfig.getTestSignature(url);
  		for (String key : map.keySet()) { mode.addAttribute(key, map.get(key)); }
  		return "waterindustry/Home";
	}

	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}", method=RequestMethod.GET)
	public @ResponseBody FundPage<RoleBean> listRoles(@PathVariable Integer pageno, @PathVariable Integer pagesize, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		FundPage<RoleBean> page = ucs.findRoleAll(customerId, new PageRequest(pageno, pagesize));
		return page;
	}
	
	/**
	 * 测试返回Signature
	 * @return
	 */
	@RequestMapping(value = "/TestSignature")
	public void TestSignature(HttpServletRequest request,HttpServletResponse response) {
		String url = request.getRequestURL().toString();
		System.out.println(url);
		Map<String, String> map = JsSDKConfig.getTestSignature(url);
		String result=JSONObject.fromObject(map).toString();
		outPrintResult(response, result);
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
