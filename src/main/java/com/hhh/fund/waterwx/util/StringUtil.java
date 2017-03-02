package com.hhh.fund.waterwx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author sunjl
 * 
 */
public class StringUtil extends StringUtils {
	
	public static boolean isBlank(String str){
	     int strLen;
	     if ((str == null) || ((strLen = str.length()) == 0) || str.toLowerCase().equals("null"))
	       return true;
	     for (int i = 0; i < strLen; ++i) {
	       if (!(Character.isWhitespace(str.charAt(i)))) {
	         return false;
	       }
	     }
	     return true;
   }

	/**
	 * 解决split函数的一个问题： 比如有个字符串String a =
	 * "a,b,c";那么a.split(",");返回的是一个String型的数组长度为3， 若字符串a = "a,,c"
	 * 执行a.split(",");返回的也是长度为3的字符串数组， 若a = "a,,"
	 * 执行a.split(",");此时返回的是个长度为1的字符串数组,把后面的就给去了， 如果程序中用到了后面的字符，就会引起数组越界的错误，
	 * 可以将a = "a,,"在加一个字符a = "a,,,end"，这样虽然改变了数组的长度但是不会产生数组越界的错误。
	 * */
	public static String[] strSplit(String str) {

		if (StringUtils.isBlank(str))
			return null;

		if (',' == (str.charAt(str.length() - 1))) {
			str += ",END";
		}

		return str.split(",");
	}

	public static String[] strSplit(String str, int len) {

		if (StringUtils.isBlank(str))
			return new String[len];

		return strSplit(str);
	}

	/**
	 * 将2进制字符串进行按位或操作(实质是:权限值相加)
	 * */
	public static String getBinAddStr(String one, String two) {
		
		StringBuffer buf = new StringBuffer();
		
		if (one.length() != two.length())
			return null;
		
		for (int i = 0; i < one.length(); i++) {
			int v1 = one.charAt(i) == '1' ? 1 : 0;
			int v2 = two.charAt(i) == '1' ? 1 : 0;
			if ((v1 + v2) > 0)// 说明其中一位是1
				buf.append("1");
			else
				buf.append("0");
		}
		
		return buf.toString();
	}

	/**
	 * 判断权限串中，有无给定值的权限
	 * */
	public static boolean hasRight(String rightstr, int rcode) {
		
		if (null == rightstr || rightstr.length() < rcode)
			return false;
		return rightstr.charAt(rcode - 1) == '1';
	}

	public static String getstrFromUrl(String urladdress, String sendstr) {
		StringBuffer resbu = new StringBuffer();
		try {
			URL url = new URL(urladdress);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			PrintWriter out = new PrintWriter(connection.getOutputStream());
			out.println(sendstr);
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				resbu.append(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resbu.toString();
	}

	public static String parseMenuUrl(String url) {
		String parameter = "";
		if (StringUtil.isNotBlank(url)) {
			int parameterIndex = url.indexOf("?");
			int len = url.length();
			String tem = "";
			if (parameterIndex > 0 && parameterIndex < len) {
				tem = url.substring(parameterIndex + 1, len); // 处理传递的参数
				tem = tem.replace("&", "__");
				parameter = "#token#" + "__" + tem;
				// 处理参数前的URL
				if (url.startsWith("/")) {
					tem = url.substring(1, parameterIndex);
				} else {
					tem = url.substring(0, parameterIndex);
				}
				tem = tem.replace(".", "-");
				tem = tem.replace("/", ".");
				url = "#domain#" + tem;
			} else {
				if (url.startsWith("/")) {
					url = url.substring(1);
				}
				tem = url.replace(".", "-");
				tem = tem.replace("/", ".");
				parameter = "#token#";
				url = "#domain#" + tem + "";
			}
		}
		
		return "id=\"" + url + "?token=" + parameter + "\"";
	}

	public static boolean startCheck(String reg, String string) {

		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		tem = matcher.matches();
		
		return tem;
	}

	/**
	 * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
	 * */
	public static boolean checkCellPhone(String cellPhoneNr) {
		
		String reg = "^[1][\\d]{10}";
		return startCheck(reg, cellPhoneNr);
	}

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符 地址结尾必须是2位以上，如：cn,test,com,info
	 * */
	public static boolean checkEmail(String email) {
		
		String regex = "\\w+\\@\\w+\\.\\w{2,}";
		return startCheck(regex, email);
	}
	
	/***
	 * 字符串所在位置
	 * @param substr 长字符串
	 * @param str  所查找字符串
	 * @param splitstr 分隔符 
	 * @return
	 */
	public static String getSplitIndex(String substr,String str,String splitstr){
		if(isBlank(substr)||isBlank(str))return "";
		String[] strs=substr.split(",");
		for(int i=0;i<strs.length;i++){
			if(strs[i].equals(str))
				return String.valueOf(i);
		}
		return "";
	}
	
	/**
	 * 全角转半角
	 * @param input
	 * @return
	 */
	public static String toHalfStr(String input) {     
        char c[] = input.toCharArray();   
        for (int i = 0; i < c.length; i++) {   
          if (c[i] == '\u3000') {   
             c[i] = ' ';   
           } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {   
             c[i] = (char) (c[i] - 65248);   
 
           }   
         }   
        String returnString = new String(c);   
      
        return returnString;   
	}  

	/**
	 * 半角转全角
	 * @param input
	 * @return
	 */
	public static String ToFullStr(String input) {   
        char c[] = input.toCharArray();   
        for (int i = 0; i < c.length; i++) {   
          if (c[i] == ' ') {   
             c[i] = '\u3000';                 //采用十六进制,相当于十进制的12288   
           } else if (c[i] < '\177') {    //采用八进制,相当于十进制的127   
             c[i] = (char) (c[i] + 65248);   
           }   
         }   
        return new String(c);   
	}   

	/**
	 * 对用户输入进行清理$（美元符号）
     [5] %（百分比符号）
     [6] @（at 符号）
     [7] '（单引号）
     [8] "（引号）
     [9] \'（反斜杠转义单引号）
     [10] \"（反斜杠转义引号）
     [11] <>（尖括号）
     [12] ()（括号）
     [13] +（加号）
     [14] CR（回车符，ASCII 0x0d）
     [15] LF（换行，ASCII 0x0a）
     [16] ,（逗号）
     [17] \（反斜杠）
	 * */
	public static String replaceStr(String str) {
		//str = StringEscapeUtils.escapeHtml3(str);
		str = str.replace("'", "‘");
		str = str.replace("|", "｜");
		str = str.replace("&", "＆");
		str = str.replace(";", "；");
		str = str.replace("@", "＠");
		str = str.replace("\"", "”");
		str = str.replace("\\'", "＼’");
		str = str.replace("\\\"", "＼＼‘");
		str = str.replace("<", "《");
		str = str.replace(">", "》");
		str = str.replace("(", "（");
		str = str.replace(")", "）");
		str = str.replace("\\(", "（");
		str = str.replace("\\)", "）");
		str = str.replace("+", "＋");
		str = str.replace("\\+", "＋");
		str = str.replace("\r", "");
		str = str.replace("\n", "");
		str = str.replace("script", "　s　c　r　i　p　t　");
		str = str.replace("SCRIPT", "　s　c　r　i　p　t　");
		str = str.replace("%27", "");
		str = str.replace("%22", "");
		str = str.replace("%3E", "");
		str = str.replace("%3C", "");
		str = str.replace("%3D", "");
		str = str.replace("%2F", "");
		return str;
	}

	public static String join(String separator, List<String> strings){
		if(null==strings||strings.size()==0||strings.isEmpty())return null;
		String[] stringarray=new String[strings.size()];
		for(int i=0;i<strings.size();i++){
			stringarray[i]=strings.get(i);
		}
		return join(separator,stringarray);
	}
	public static String join(String separator, String[] stringarray){
		if (stringarray == null)
			return null;
		else
			return join(separator, stringarray, 0, stringarray.length);
	}

	public static String join(String separator, String[] stringarray, int startindex, int count){
		String result = "";
		if (stringarray == null)return null;
		for (int index = startindex; index < stringarray.length && index - startindex < count; index++){
			if (separator != null && index > startindex)
				result += separator;
			if (stringarray[index] != null)
				result += stringarray[index];
		}
		return result;
	}
	
	//数据权限解析:替换掉SQL中{and :gjcdataaccess(o.orgcode,createid)} sunjl
	public static String parseDataAccessStr(String sql,String accessrange,String employeeid){
		if(null == sql || sql.indexOf("gjcdataaccess") == -1) return sql;//SQL中不存在数据权限过滤标识，直接返回
		String fparams = sql.substring(sql.indexOf("gjcdataaccess") +14);
		String[] fields = fparams.substring(0, fparams.indexOf(")}")).split(",");
		String orgcode = fields.length > 0 ? fields[0] : null;
		String createid = fields.length > 1 ? fields[1] : null;
		if(null != createid) createid = createid + "='" + employeeid + "'";
		
		StringBuffer ssql = new StringBuffer();
		if (StringUtil.isNotBlank(accessrange)) {// 过滤条件:权限范围内的数据
			ssql.append(" and (").append(accessrange.replaceAll("_orgcode_", orgcode));
			if(null != createid)//如果创建者ID不为空，还需可以看到自已创建的数据
				ssql.append(" or ").append(createid).append(")");
			else
				ssql.append(")");
		}else{ //过滤条件:当前用户只可以看到自已创建的数据
			if(null != createid) ssql.append(" and ").append(createid);
		}
		
		sql = sql.replaceFirst("\\{and :gjcdataaccess\\(([A-Za-z0-9._,]*)\\)\\}",ssql.toString());//替换掉{and :gjcdataaccess(o.orgcode,createid)}
		return sql;
	}
	
	
}
