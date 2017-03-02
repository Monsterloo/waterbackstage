package com.hhh.fund.waterwx.model;
import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hhh.fund.util.CookieUtil;
import com.hhh.fund.util.HttpUtil;
import com.hhh.fund.waterwx.util.Constants;







/*
 * 微信通用接口凭证
 */
public class AccessToken implements Serializable {
    
	private static Log log = LogFactory.getLog(AccessToken.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	// 获取到的凭证

	private String token;  

	// 凭证有效时间，单位：秒

	private int expiresIn;  
	
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 * return suc :{"access_token":"ACCESS_TOKEN","expires_in":7200}/fail:{"errcode":40018,"errmsg":"invalid button name size"}
	 */
	public static AccessToken getAccessToken(HttpServletRequest request,HttpServletResponse response) {
		
		AccessToken accessToken = null;
		
		if(request!=null){
	        Cookie cookie = CookieUtil.getCookieByName(request, "wechatAccessToken"+Constants.APP_ID);
	        if(cookie!=null){
	        	accessToken = new AccessToken();
	        	accessToken.setToken(cookie.getValue());
	        	return accessToken;
	        }
		}

		String requestUrl = Constants.ACCESS_TOKEN_RUL.replace("APPID", Constants.APP_ID).replace("APPSECRET", Constants.APP_SECRET);
		JSONObject jsonObject = HttpUtil.sendHttps(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject&&jsonObject.getString("access_token")!=null) {
			try {
				accessToken = new AccessToken();
				String access =jsonObject.getString("access_token");
				accessToken.setToken(access);
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				
				if(response!=null){
					CookieUtil.addCookie(response, "weiXinAccessToken"+Constants.APP_ID,access,CookieUtil.accessToken_time);
				}
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+";"+jsonObject.getString("errmsg"));
			}
		}
		
		return accessToken;
	}
	
	public String getToken() {  
	   return token;  
	}  

	public void setToken(String token) {  
	   this.token = token;  
    }  

	public int getExpiresIn() {  
	   return expiresIn;  
	}  

	public void setExpiresIn(int expiresIn) {  
	   this.expiresIn = expiresIn;  
	}  
  

}
