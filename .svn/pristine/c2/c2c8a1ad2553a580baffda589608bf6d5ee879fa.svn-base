package com.hhh.fund.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
  
  /**
   * 关闭连接
   * @param response
   */	
  public static void colseWrite(HttpServletResponse response){
	 try{
		 PrintWriter writer =getWriter(response);
		 if(writer!=null){
			 writer.close();
		 }
	 }catch(IOException e){
		 e.printStackTrace();
	 }
  }
  public static void alert(HttpServletResponse response,String content){
	  if(content==null){
		  content="";
	  }
	  content = "<script type='text/javascript'>alert('"+content+"');</script>";
	  try{
		 getWriter(response).print(content);
	  }catch(IOException e){
		 e.printStackTrace();
	  }
  }
  public static void alert2(HttpServletResponse response,String content){
	  if(content==null){
		  content="";
	  }
	  content = "<script>alert('"+content+"');window.history.go(-1);</script>";
	  try{
		 getWriter(response).print(content);
	  }catch(IOException e){
		 e.printStackTrace();
	  }
  }
  public static void location(HttpServletResponse response ,String url){
	  if(url!=null&&!"".equals(url)){
		 url = "<script type='text/javascript'>window.location.href='"+url+"';</script>";
		 try{
			 getWriter(response).print(url);
		 }catch(IOException e){
			 e.printStackTrace();
		 }
	  }
	 
  }
  public static void wirte(HttpServletResponse response ,String content){
	  if(content!=null&&!"".equals(content)){
		  content = "<script type='text/javascript'>"+content+"</script>";
			 try{
				 getWriter(response).print(content);
			 }catch(IOException e){
				 e.printStackTrace();
			 }
	  }
  }
  public static void wirteJsonDate(HttpServletResponse response ,String data){
	  if(data!=null&&!"".equals(data)){
			 try{
				 getXMLWriter(response).write(data);
			 }catch(IOException e){
				 e.printStackTrace();
			 }
	  }
  }
  public static void sendJson(HttpServletResponse response ,String data){
	  if(data!=null&&!"".equals(data)){
			 try{
				  response.setContentType("application/json;charset=utf-8");    
				  response.setHeader("Cache-Control","no-cache");
				  response.getWriter().write(data);
			 }catch(IOException e){
				 e.printStackTrace();
			 }
	  }
  }
  
  public static PrintWriter getWriter(HttpServletResponse response) throws IOException{
	  response.setCharacterEncoding("utf-8");
	  return response.getWriter();
  }
  public static PrintWriter getXMLWriter(HttpServletResponse response) throws IOException{
	  response.setContentType("text/xml;charset=utf-8");    
	  response.setHeader("Cache-Control","no-cache");
	  return response.getWriter();
  }
	/**
	 * A getHttpUrlByServer function
	 * <p> 得到当前服务器域名：http://www.baidu.com </p>
	 * @return
	 */
	public static String getHttpServerName(HttpServletRequest request){
		StringBuffer sbImgPath = new StringBuffer();
		String protocol = request.getProtocol();
		protocol = protocol.toLowerCase(); 
		if(protocol.indexOf("http")>=0){
			sbImgPath.append("http");
		}else if(protocol.indexOf("https")>=0){
			sbImgPath.append("https");
		}else if(protocol.indexOf("ftp")>=0){
			sbImgPath.append("ftp");
		}
		sbImgPath.append("://");
		sbImgPath.append(request.getServerName());
		if(request.getLocalPort()!=80){
			if(request.getLocalPort()==81){   //本地的
				sbImgPath.append(":").append(request.getLocalPort());
			}
		}
		sbImgPath.append(request.getContextPath());
		return sbImgPath.toString();
	}
	public static void message(HttpServletRequest request,String message){
		request.getSession().setAttribute("message", message);
	}
	//对参数编码（注意 不能是一个URL）
	public static String encode(String param){
		if(StringUtils.isNotBlank(param)){
			try{
				param = URLEncoder.encode(param, "utf-8");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return param;
	}
	//对参数解码
	public static String decode(String param){
		if(StringUtils.isNotBlank(param)){
			try{
				param = URLDecoder.decode(param, "utf-8");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return param;
	}
	  /**
	   * URL 进行编码
	   * @param url
	   * @return
	   */
	  public static String URLEncoder(String url){
		try{
			url= URLEncoder.encode(url,"utf-8");  //  解码URLDecoder.decode(url,   "utf-8");   
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		  return url;
	  }
}
