package com.hhh.fund.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hhh.fund.waterwx.util.Constants;



import net.sf.json.JSONObject;


public class HttpUtil {
	
	private static Log log = LogFactory.getLog(HttpUtil.class);
	
	
	/**

     * 发起https请求并获取结果

     * 

     * @param requestUrl 请求地址

     * @param requestMethod 请求方式（GET、POST）

     * @param outputStr 提交的数据

     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)

     */

    public static JSONObject sendHttps(String requestUrl, String requestMethod, String outputStr) {  

	        JSONObject jsonObject = null;  
	        
	        StringBuffer buffer = new StringBuffer();  
	
	    try {  
	
	   // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	
	            TrustManager[] tm = { new TrustAnyTrustManager() };  
	
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	
	            sslContext.init(null, tm, new java.security.SecureRandom());  
	
	  // 从上述SSLContext对象中得到SSLSocketFactory对象
	
	            SSLSocketFactory ssf = sslContext.getSocketFactory();  
	
	            URL url = new URL(requestUrl);  
	
	            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
	
	            httpUrlConn.setSSLSocketFactory(ssf);  
	
	            httpUrlConn.setDoOutput(true);  
	
	            httpUrlConn.setDoInput(true);  
	
	            httpUrlConn.setUseCaches(false);  
	
	// 设置请求方式（GET/POST）
	
	            httpUrlConn.setRequestMethod(requestMethod);  
	
	if ("GET".equalsIgnoreCase(requestMethod))  
	
	                httpUrlConn.connect();  
	
	// 当有数据需要提交时
	
	if (null != outputStr) {  
	
	                OutputStream outputStream = httpUrlConn.getOutputStream();  
	
	// 注意编码格式，防止中文乱码
	
	                outputStream.write(outputStr.getBytes("UTF-8"));  
	
	                outputStream.close();  
	
	            }  
	
	// 将返回的输入流转换成字符串
	
	            InputStream inputStream = httpUrlConn.getInputStream();  
	
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	
	            String str = null;  
	
	while ((str = bufferedReader.readLine()) != null) {  
	
	                buffer.append(str);  
	
	            }  
	
	            bufferedReader.close();  
	
	            inputStreamReader.close();  
	// 释放资源
	            inputStream.close();  
	
	            inputStream = null;  
	
	            httpUrlConn.disconnect();  
	            
	            jsonObject = JSONObject.fromObject(buffer.toString());  
	
	        } catch (ConnectException ce) {  
	
	            log.error("Weixin server connection timed out.");  
	
	        } catch (Exception e) {  
	            log.error("https request error:{}", e);  
	
	        }  
	
	          return jsonObject;  
	
	    } 
    /** 
     * 发送http请求取得返回的输入流 
     *  
     * @param requestUrl 请求地址 
     * @return InputStream 
     */ 
    @SuppressWarnings("unchecked")
    private static InputStream httpRequestReturnStream(String requestUrl) {  
        InputStream inputStream = null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            // 获得返回的输入流  
            inputStream = httpUrlConn.getInputStream();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return inputStream;  
    }  
  
    /** 
     * utf编码 
     *  
     * @param source 
     * @return 
     */  
    public static String urlEncodeUTF8(String source) {  
        String result = source;  
        try {  
            result = java.net.URLEncoder.encode(source, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
        	String urlNameString =url;
        	if(param!=null&&!"".equals(param)){
        		urlNameString = url + "?" + param;
        	}
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }  
    
    
        /*
         * 创建菜单
         * return suc :{"errcode":0,"errmsg":"ok"}/fail:{"errcode":40018,"errmsg":"invalid button name size"}
         */
        public static JSONObject  createMenu(String accessToken){
        	 String menuStr =  "{"+
		        	 		       "\"button\":[{\"type\":\"view\",\"name\":\"360汽车网\",\"url\":\"www.360qc.com\" },"
		        	 		       +"{\"name\":\"店铺管理\",\"sub_button\":"+
	        	 		              "[{\"type\":\"click\",\"name\":\"账户绑定\",\"key\":\"M1001\"},{\"type\":\"click\",\"name\":\"账户解绑\",\"key\":\"M1002\"}]},"
		        	 		       +"{\"type\":\"click\",\"name\":\"我的店铺\",\"key\":\"M3001\"}]" 
        	 		       		+"}";
       	   String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
       	   
       	   return HttpUtil.sendHttps(url, "POST", menuStr);
       	   
        }
}