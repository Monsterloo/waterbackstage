package com.hhh.fund.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil  {
	
	 private static final int cookieMaxAge=3*24*60*60;    //3天
	 
	 private static final String webRoot="/";             //项目根目录
	 
	 public static final int accessToken_time=7000;      //微信accessToke 最多2个小时有效2*60*60!
	 
		
	 public static void addCookie(HttpServletResponse response,String name,String value){
		 addCookie(response,name,value,cookieMaxAge);
	  }
		
	 /**
	  * 设置cookie
	  * @param response
	  * @param name  cookie名字
	  * @param value cookie�?
	  * @param maxAge cookie生命周期  以秒为单
	  */	
	 public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		    Cookie cookie = new Cookie(name,value);
		    cookie.setPath(webRoot);
		    cookie.setMaxAge(maxAge);
		    response.addCookie(cookie);
	 }
		
	 
	 /**
	  * 根据名字获取cookie
	  * @param request
	  * @param name cookie名字
	  * @return
	  */
	 public static Cookie getCookieByName(HttpServletRequest request,String name){
	     Map<String,Cookie> cookieMap = ReadCookieMap(request);
	     if(cookieMap.containsKey(name)){
	         Cookie cookie = (Cookie)cookieMap.get(name);
	         return cookie;
	     }else{
	         return null;
	     }  
	 }
	 /**
	  * 将cookie封装到Map里面
	  * @param request
	  * @return
	  */
	 private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){ 
	     Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	     Cookie[] cookies = request.getCookies();
	     if(null!=cookies){
	         for(Cookie cookie : cookies){
	             cookieMap.put(cookie.getName(), cookie);
	         }
	     }
	     return cookieMap;
	 }
	
}
