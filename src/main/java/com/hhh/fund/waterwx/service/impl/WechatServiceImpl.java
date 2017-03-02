package com.hhh.fund.waterwx.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhh.fund.waterwx.model.SendTextMessage;
import com.hhh.fund.waterwx.service.WechatService;
import com.hhh.fund.waterwx.util.Constants;
import com.hhh.fund.waterwx.util.MessageUtil;
import com.hhh.fund.waterwx.util.SignUtil;


@Service
@Transactional
public class WechatServiceImpl implements WechatService {


	public String doPost(HttpServletRequest request,HttpServletResponse response) {
	       String respMessage = null;  
	       try {  
	           // 默认返回的文本消息内容  
//	           String respContent = "请求处理异常，请稍候尝试！";  
	           String respContent = null;  
	           // xml请求解析  
	           Map<String, String> requestMap = MessageUtil.parseXml(request);  
	 
	           // 发送方帐号（open_id）  
	           String fromUserName = requestMap.get("FromUserName");  
	           // 公众帐号  
	           String toUserName = requestMap.get("ToUserName");  
	           // 消息类型  
	           String msgType = requestMap.get("MsgType");  
	           
//	           System.err.println("msgType--->"+msgType);
	           
	           // 回复文本消息  
	           SendTextMessage sendTextMessage = new SendTextMessage();  
	           sendTextMessage.setToUserName(fromUserName);  
	           sendTextMessage.setFromUserName(toUserName);  
	           sendTextMessage.setCreateTime(new Date().getTime());  
	           sendTextMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
	           sendTextMessage.setFuncFlag(0);  
	 
	           // 文本消息  
	           if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
	        	     return null;
	           }  
	           // 图片消息  
	           else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
	        	     return null;
	           }  
	           // 地理位置消息  
	           else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
	        	     return null;
	           }  
	           // 链接消息  
	           else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
	        	     return null;
	           }  
	           // 音频消息  
	           else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
	        	     return null;
	           }  
	           // 事件推送  
	           else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
	               // 事件类型  
	               String eventType = requestMap.get("Event");  
	               // 订阅  
	               if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
	                   respContent = "爱水护水齐参与，清水活水美羊城。欢迎关注广州水务官方微信，广州治水，期待你的参与！";
//	                   this.updateUserInfo(fromUserName,request, response);
	               }  
	               // 取消订阅  
	               else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
//	            	   this.cancelFollow(fromUserName);
	               }  
	               // 自定义菜单点击事件  
	               else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
	            	   String eventKey = requestMap.get("EventKey");
	            	   System.out.println("eventKey-->"+eventKey);
	            	   if(Constants.HOT_LINE.equals(eventKey)){   //热线电话
	            		    StringBuffer buffer= new StringBuffer();
	            			buffer.append("供水热线：96968").append("\n");
	            			buffer.append("三防热线：87590388").append("\n");
	            			buffer.append("廉政举报热线：85593023").append("\n");
	            			buffer.append("市政府服务热线：12345").append("\n");
	            			buffer.append("水事违法热线：85641752");
	            			respContent = buffer.toString();
	            	   }
	               }  
	           }  
	 
	           sendTextMessage.setContent(respContent);  
	           respMessage = MessageUtil.textMessageToXml(sendTextMessage);
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	 
	       return respMessage; 
	}

	public String checkDeveloper(HttpServletRequest request) {
	       // 微信加密签名  
	       String signature = request.getParameter("signature");  
	       // 时间戳  
	       String timestamp = request.getParameter("timestamp");  
	       // 随机数  
	       String nonce = request.getParameter("nonce");  
	       // 随机字符串  
	       String echostr = request.getParameter("echostr");  
	       // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
	       System.out.println("signature-->"+signature+"timestamp-->"+timestamp+" nonce-->"+nonce);
	       
	       if (SignUtil.checkSignature(signature, timestamp, nonce)) { 
	           return echostr;
	       } else {
	    	   return null;
	       }
	}
}
