package com.hhh.fund.waterwx.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.FuwuhaoConst;
import com.hhh.weixin.util.MyX509TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PlatformUtil {
	
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	private static final int timeout = 7200 * 1000;
	private static String serviceAccessToken = "";
	private static long accessTokenTime = 0;
	private static String jsapiTicket = "";
	private static long jsapiTickedTime = 0;

	public static String getAccessToken() {
		if (System.currentTimeMillis() - accessTokenTime > timeout) {

			String grant_type = "client_credential";// 获取access_token填写client_credential
			String AppId = FuwuhaoConst.ServiceNumber_APP_ID;// 第三方用户唯一凭证
			String secret = FuwuhaoConst.ServiceNumber_APP_SECRET;// 第三方用户唯一凭证密钥，即appsecret
			// 这个url链接地址和参数皆不能变
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + AppId
					+ "&secret=" + secret;

			try {
				URL urlGet = new URL(url);
				HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
				http.setRequestMethod("GET"); // 必须是get方式请求
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
				System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
				http.connect();
				InputStream is = http.getInputStream();
				int size = is.available();
				byte[] jsonBytes = new byte[size];
				is.read(jsonBytes);
				is.close();
				String message = new String(jsonBytes, "UTF-8");
				JSONObject demoJson = JSONObject.fromObject(message);
				System.out.println("JSON字符串：" + demoJson);
				serviceAccessToken = demoJson.getString("access_token");
				accessTokenTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return serviceAccessToken;
	}
	
	public static String refreshAccessToken() {

			String grant_type = "client_credential";// 获取access_token填写client_credential
			String AppId = FuwuhaoConst.ServiceNumber_APP_ID;// 第三方用户唯一凭证
			String secret = FuwuhaoConst.ServiceNumber_APP_SECRET;// 第三方用户唯一凭证密钥，即appsecret
			// 这个url链接地址和参数皆不能变
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + AppId
					+ "&secret=" + secret;

			try {
				URL urlGet = new URL(url);
				HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
				http.setRequestMethod("GET"); // 必须是get方式请求
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
				System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
				http.connect();
				InputStream is = http.getInputStream();
				int size = is.available();
				byte[] jsonBytes = new byte[size];
				is.read(jsonBytes);
				is.close();
				String message = new String(jsonBytes, "UTF-8");
				JSONObject demoJson = JSONObject.fromObject(message);
				System.out.println("JSON字符串：" + demoJson);
				serviceAccessToken = demoJson.getString("access_token");
				accessTokenTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return serviceAccessToken;
	}

	public static String getTicket(String access_token) {
		if (System.currentTimeMillis() - jsapiTickedTime > timeout) {
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token
					+ "&type=jsapi";// 这个url链接和参数不能变
			try {
				URL urlGet = new URL(url);
				HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
				http.setRequestMethod("GET"); // 必须是get方式请求
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
				System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
				http.connect();
				InputStream is = http.getInputStream();
				int size = is.available();
				byte[] jsonBytes = new byte[size];
				is.read(jsonBytes);
				is.close();
				String message = new String(jsonBytes, "UTF-8");
				JSONObject demoJson = JSONObject.fromObject(message);
				System.out.println("JSON字符串：" + demoJson);
				jsapiTicket = demoJson.getString("ticket");
				jsapiTickedTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsapiTicket;
	}



	/**
	 * 微信服务号获取用户基本信息(单个用户)
	 * @param openid
	 * @return
	 */
	public static JSONObject getUserInfo(final String openid) {
				String accessToken = getAccessToken();
				String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken
						+ "&openid="+openid+"&lang=zh_CN";
				InputStream is = null;
				HttpURLConnection http = null;
				JSONObject jsonObject = null;
				StringBuffer buffer = new StringBuffer();
							try {
								URL urlGet = new URL(url);
								http = (HttpURLConnection) urlGet.openConnection();
								http.setRequestMethod("GET"); // 必须是get方式请求
								http.setRequestProperty("Content-Type", "application/octet-stream");
								http.setDoOutput(true);
								http.setDoInput(true);
								System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
								System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
								http.connect();
								is = http.getInputStream();
								
								InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
								BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
								
								String str = null;
								while ((str = bufferedReader.readLine()) != null) {
								buffer.append(str);
								}
								bufferedReader.close();
								inputStreamReader.close();
								jsonObject = JSONObject.fromObject(buffer.toString());
							} catch (Exception e) {
								e.printStackTrace();
								try {
									Thread.sleep(5 * 1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							} finally {
								try {
									if (is != null) {
										is.close();
										is = null;
									}
									if(http != null){
										http.disconnect();
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							return jsonObject;
						}
	
	/**
	 * 服务号获取已关注用户列表
	 * 
	 * 
	 */
	public static JSONObject getFollowUserInfo(String NEXT_OPENID) {
		String accessToken = getAccessToken();
		JSONObject jsonObject = null;
		HttpURLConnection http = null;
		InputStream is = null;
		String requestUrl=null;
		if("".equals(NEXT_OPENID)){
			requestUrl="https://api.weixin.qq.com/cgi-bin/user/get?"
				+ "access_token="+accessToken+"";
		}else{
			requestUrl="https://api.weixin.qq.com/cgi-bin/user/get?"
					+ "access_token="+accessToken+"&next_openid="+NEXT_OPENID+"";
		}
		try {
			URL request = new URL(requestUrl);
			http = (HttpURLConnection) request.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			

			is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			jsonObject = JSONObject.fromObject(message);
			if(jsonObject.containsKey("errcode")){
				if(Integer.parseInt(jsonObject.getString("errcode"))==40001){
					refreshAccessToken();
				}
			}
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (http != null) {
				http.disconnect();
			}
		}
		return jsonObject;
	}
	
	/**
	 * 微信服务号获取用户基本信息（批量）
	 * @param openid
	 * @return
	 */
	public static JSONObject getFollowUserInfoList(String outputStr) {
		String accessToken = getAccessToken();
		JSONObject jsonObject = null;
		HttpURLConnection http = null;
		InputStream is = null;
		String requestUrl="https://api.weixin.qq.com/cgi-bin/user/info/batchget?"
				+ "access_token="+accessToken+"";
		try {
			URL request = new URL(requestUrl);
			http = (HttpURLConnection) request.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			if (null != outputStr) {
				OutputStream outputStream = http.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			jsonObject = JSONObject.fromObject(message);

		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (http != null) {
				http.disconnect();
			}
		}
		return jsonObject;
	}
	
	
}
