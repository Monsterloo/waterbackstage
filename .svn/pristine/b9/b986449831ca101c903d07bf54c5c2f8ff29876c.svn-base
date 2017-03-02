package com.hhh.weixin.company.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.waterwx.util.Constants;
import com.hhh.weixin.company.service.CoreService;
import com.hhh.weixin.company.service.MenuManager;
import com.hhh.weixin.menu.Menu;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.MenuUtil;
import com.hhh.weixin.util.QiyehaoConst;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/weixinqy")
public class CoreController {
	
	private Logger log = LoggerFactory.getLogger(CoreController.class);

	/**
	 * 企业号回调URL验证,开启应用的回调模式
	 * 
	 * @param msg_signature
	 *            微信加密签名
	 * @param timestam
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            加密的随机字符串
	 * @param response
	 */
	@RequestMapping(value = "/backcall", method=RequestMethod.GET)
	public void verifyurl(String msg_signature, String timestamp, String nonce, String echostr,
			 HttpServletResponse response) {
		String sEchostr = "";
		PrintWriter out = null;
		log.debug(msg_signature+"==="+timestamp+"==="+nonce+"==="+echostr);
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(QiyehaoConst.Token, QiyehaoConst.EncodingAESKey, QiyehaoConst.CorpID);
			sEchostr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
			log.debug("==============Echostr:" + sEchostr);
			out = response.getWriter();
			out.write(sEchostr);
		} catch (AesException | IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
			out = null;
		}
	
	}
	
	/**
	 * 企业号回调URL验证, 使用回调模式
	 * @param msg_signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/backcall", method=RequestMethod.POST)
	public void verifyService(String msg_signature, String timestamp, String nonce, 
			HttpServletRequest request, HttpServletResponse response){
		
		String encryptMsg = "";
		PrintWriter out = null;
		try {
			InputStream inputStream = request.getInputStream();
			String Post = IOUtils.toString(inputStream, "UTF-8");
			// Post打印结果
			log.debug("######################post:"+Post);
			
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(QiyehaoConst.Token, QiyehaoConst.EncodingAESKey, QiyehaoConst.CorpID);
			//解密消息
			String Msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, Post);
			// Msg打印结果
			log.debug("Msg打印结果：" + Msg);
			
			// 调用核心业务类接收消息、处理消息
			String respMessage = CoreService.processRequest(Msg);
			
			// respMessage打印结果
			log.debug("respMessage打印结果：" + respMessage);
			if(respMessage != null)
				encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
			out = response.getWriter();
		} catch (AesException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.print(encryptMsg);
		out.close();
	}
	
	/**
	 * OAuth2授权回调
	 * @param code 通过成员授权获取到的code，每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期 
	 * @param state 重定向后会带上state参数，
	 */
	@RequestMapping(value = "/authorize", method=RequestMethod.GET)
	public void authorize(String code, String state, HttpSession session, 
			HttpServletResponse response){
		log.debug("########code:"+code);
		if(code != null && !code.isEmpty()){
			JSONObject json = CommonUtil.getUserInfo(code);
			if(json.has("UserId")){
				session.setAttribute(QiyehaoConst.Weixin_UserId, json.getString("UserId"));
			}else if(json.has("OpenId")){
				session.setAttribute(QiyehaoConst.Weixin_OpenId, json.getString("OpenId"));
			}
			
			String redirectUrl = CommonUtil.urlDecodeUTF8(state);
			redirectUrl = Constants.DOMAIN_NAME + redirectUrl;
			
			try {
				response.sendRedirect(redirectUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * OAuth2授权回调(针对已授权用户)
	 * @param code 通过成员授权获取到的code，每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期 
	 * @param state 重定向后会带上state参数，
	 */
	@RequestMapping(value = "/authorizes", method=RequestMethod.GET)
	public void authorizes(String code, String state, HttpSession session, 
			HttpServletResponse response){
		System.out.println("authorizes-------->");
		log.debug("########code:"+code);
		if(code != null && !code.isEmpty()){
			JSONObject json = CommonUtil.getUserInfo(code);
			System.out.println("UserId--------->"+json.getString("UserId"));
			session.setAttribute(QiyehaoConst.Weixin_UserId, json.getString("UserId"));
			JSONObject userJson = CommonUtil.convertToOpenId(json.getString("UserId"), null);
			session.setAttribute(QiyehaoConst.Weixin_OpenId, userJson.getString("openid"));
			String redirectUrl = CommonUtil.urlDecodeUTF8(state);
			redirectUrl = Constants.DOMAIN_NAME + redirectUrl;
			System.out.println(redirectUrl);
			try {
				response.sendRedirect(redirectUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/createmenu/{agentid}", method=RequestMethod.GET)
	public @ResponseBody String createMenu(String agentid){
		Menu menu = MenuManager.getMenu();
		boolean bl = MenuUtil.createMenu(menu, 1);
		return bl?"menu create success" : "menu create failure";
	}
	
	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public void test(){
		
	}
}
