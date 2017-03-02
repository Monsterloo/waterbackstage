package com.hhh.fund.waterwx.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.weixin.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsSDKConfig {

	private static final int timeout = 7000 * 1000;
	private static String accessToken = "";
	private static long accessTokenTime = 0;
	private static String jsapiTicket = "";
	private static long jsapiTickedTime = 0;

	public static String getAccessToken() {
		if (System.currentTimeMillis() - accessTokenTime > timeout) {

			String grant_type = "client_credential";// 获取access_token填写client_credential
			String AppId = Constants.APP_ID;// 第三方用户唯一凭证
			String secret = Constants.APP_SECRET;// 第三方用户唯一凭证密钥，即appsecret
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
				accessToken = demoJson.getString("access_token");
				accessTokenTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return accessToken;
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

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static Map<String, String> getSignature(String url) {
		Map<String, String> map = new HashMap<String, String>();
		// 1、获取AccessToken
		String accessToken = getAccessToken();

		// 2、获取Ticket
		String jsapi_ticket = getTicket(accessToken);

		// 3、时间戳和随机字符串
		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);// 随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);// 时间戳

		map.put("appId", Constants.APP_ID);
		map.put("timestamp", timestamp);
		map.put("noncestr", noncestr);
		System.out.println("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\n时间戳：" + timestamp
				+ "\n随机字符串：" + noncestr);

		// 5、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
				+ url;

		// 6、将字符串进行sha1加密
		String signature = SHA1(str);
		System.out.println("参数：" + str + "\n签名：" + signature);
		map.put("signature", signature);
		return map;
	}
	
	public static Map<String, String> getTestSignature(String url) {
		Map<String, String> map = new HashMap<String, String>();
		// 1、获取AccessToken
		String accessToken = getAccessToken();

		// 2、获取Ticket
		String jsapi_ticket = getTicket(accessToken);

		// 3、时间戳和随机字符串
		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);// 随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);// 时间戳

		map.put("appId", Constants.APP_ID);
		map.put("timestamp", timestamp);
		map.put("noncestr", noncestr);
		System.out.println("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\n时间戳：" + timestamp
				+ "\n随机字符串：" + noncestr);

		// 5、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
				+ url;

		// 6、将字符串进行sha1加密
		String signature = SHA1(str);
		map.put("signature", signature);
		return map;
	}

	/**
	 * 微信下载图片文件
	 * 
	 * @param mediaIds
	 * @param id
	 */
	public static void download(final String[] mediaIds, final String id, final SwjQuestionService quest) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String accessToken = getAccessToken();
				String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken
						+ "&media_id=";
				ByteArrayOutputStream bos = null;
				InputStream is = null;
				HttpURLConnection http = null;
				for (String mid : mediaIds) {
					if (mid != null && !"".equals(mid)) {
						int k = 0;
						while (true && k < 5) {
							k++;
							try {
								SwjAttachment att = new SwjAttachment();
								System.out.println("URL=" + url + mid);
								URL urlGet = new URL(url + mid);
								http = (HttpURLConnection) urlGet.openConnection();
								http.setRequestMethod("GET"); // 必须是get方式请求
								http.setRequestProperty("Content-Type", "application/octet-stream");
								http.setDoOutput(true);
								http.setDoInput(true);
								System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
								System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
								http.connect();
								is = http.getInputStream();
								byte[] buffer = new byte[1024];

								int len = 0, tlen = 0, clen = http.getContentLength();
								bos = new ByteArrayOutputStream();
								while ((len = is.read(buffer)) != -1 && tlen < clen) {
									bos.write(buffer, 0, len);
									tlen += len;
								}
								bos.close();
								att.setFile(bos.toByteArray());
								// System.out.println("############长度：" +
								// http.getContentLengthLong() + "====");
								is.close();
								att.setQuestionId(id);
								quest.saveAttachment(att);
								http.disconnect();
								System.out.println("下载完成");
								break;
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
									}
									if(bos != null){
										bos.close();
									}
									if(http != null){
										http.disconnect();
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}).start();
	}
	
	/**
	 * 微信订阅号获取关注列表
	 * @param openid
	 * @return
	 */
	public static JSONObject getFollowUserInfo(String NEXT_OPENID) {
		String accessToken = getAccessToken();
		JSONObject jsonObject = null;
		HttpURLConnection http = null;
		InputStream is = null;
		String requestUrl=null;
		StringBuffer buffer = new StringBuffer();
		if("".equals(NEXT_OPENID)){
			requestUrl="https://api.weixin.qq.com/cgi-bin/user/get?"
				+ "access_token="+accessToken+"";
		}else{
			requestUrl="https://api.weixin.qq.com/cgi-bin/user/get?"
					+ "access_token="+accessToken+"&next_openid="+NEXT_OPENID+"";
		}
		try {
			URL urlGet = new URL(requestUrl);
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
	 * 微信订阅号获取用户基本信息
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
	 * 微信订阅号获取用户基本信息
	 * @param openid
	 * @return
	 */
	public static JSONObject getUserInfoNext(String openid) {
		String accessToken = getAccessTokenNext();
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

	private static String getAccessTokenNext() {

			String grant_type = "client_credential";// 获取access_token填写client_credential
			String AppId = Constants.APP_ID;// 第三方用户唯一凭证
			String secret = Constants.APP_SECRET;// 第三方用户唯一凭证密钥，即appsecret
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
				accessToken = demoJson.getString("access_token");
				accessTokenTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return accessToken;
	}
		
	
}
