package com.hhh.weixin.company.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.hhh.weixin.company.entity.BaiduPlace;
import com.hhh.weixin.company.entity.UserLocation;
import com.hhh.weixin.message.resp.Article;
import com.hhh.weixin.message.resp.NewsMessage;
import com.hhh.weixin.message.resp.TextMessage;

//import org.liyufeng.project.servlet.CoreServlet;
//import org.liyufeng.project.pojo.BaiduPlace;
//import org.liyufeng.project.pojo.UserLocation;
//import org.liyufeng.project.util.BaiduMapUtil;
//import org.liyufeng.project.util.MessageUtil;
//import org.liyufeng.project.util.MySQLUtil;
//import org.liyufeng.project.util.OAuth2Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhh.weixin.util.MessageUtil;
import com.hhh.weixin.util.OAuth2Util;

/**
 * 核心服务类
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	private static Logger log = LoggerFactory.getLogger(CoreService.class);
	
	public static String processRequest(String request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = null;
		
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			//消息创建时间
			String createTime=requestMap.get("CreateTime");
			//应用代理ID
			String agentId=requestMap.get("AgentID");
			//回复文本消息
			String eventType = requestMap.get("Event");
			
		log.debug("----eventType:"+eventType+"---msgType:"+msgType+"--fromUserName:"+fromUserName+"---toUserName:"+toUserName+"---agentId:"+agentId);
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("欢迎您，关注");

					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
//					String eventKey = requestMap.get("EventKey");
					
				}
			}
			
			// 当用户发文本消息时
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content").trim();
				if (content.equals("附近")) {
					respContent=OAuth2Util.GetCode();
				}
				// 周边搜索
//				else if (content.startsWith("附近")) {
//					String keyWord = content.replaceAll("附近", "").trim();
//					// 获取用户最后一次发送的地理位置
//					UserLocation location = MySqlUtil.getLastLocation( fromUserName);
//					// 未获取到
//					if (null == location) {
//						respContent = getUsage();
//					} else {
//						// 根据转换后（纠偏）的坐标搜索周边POI
//						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
//						// 未搜索到POI
//						if (null == placeList || 0 == placeList.size()) {
//							respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
//						} else {
//							List<Article> articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
//							// 回复图文消息
//							NewsMessage newsMessage = new NewsMessage();
//							newsMessage.setToUserName(fromUserName);
//							newsMessage.setFromUserName(toUserName);
//							newsMessage.setCreateTime(new Date().getTime());
//							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//							newsMessage.setArticles(articleList);
//							newsMessage.setArticleCount(articleList.size());
//							respXml = MessageUtil.messageToXml(newsMessage);
//						}
//					}
//				} else
//					respContent=ChatService.chat(fromUserName, createTime, content);
			}
			// 用户发送地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 用户发送的经纬度
//				String lng = requestMap.get("Location_Y");
//				String lat = requestMap.get("Location_X");
//				// 坐标转换后的经纬度
//				String bd09Lng = null;
//				String bd09Lat = null;
//				// 调用接口转换坐标
//				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
//				if (null != userLocation) {
//					bd09Lng = userLocation.getBd09Lng();
//					bd09Lat = userLocation.getBd09Lat();
//				}
//				// 保存用户地理位置
//				MySQLUtil.saveUserLocation(fromUserName, lng, lat, bd09Lng, bd09Lat);
//
//				StringBuffer buffer = new StringBuffer();
//				buffer.append("[愉快]").append("成功接收您的位置！").append("\n\n");
//				buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
//				buffer.append("        附近ATM").append("\n");
//				buffer.append("        附近KTV").append("\n");
//				buffer.append("        附近厕所").append("\n");
//				buffer.append("必须以“附近”两个字开头！");
//				respContent = buffer.toString();
			} 
			if (null != respContent) {
				// 设置文本消息的内容
				textMessage.setContent(respContent);
				// 将文本消息对象转换成xml
				respXml = MessageUtil.messageToXml(textMessage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	
}
