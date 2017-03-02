package com.hhh.fund.waterwx.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
/**
 * 气泡
 * @author 3hzxp
 *
 */
public class SendMessageUtil {

	public static void sendMessage(String toOpenId,HttpServletRequest request) {
			ServletContext sc = request.getServletContext();
			Map<String,Map<String,Object>>map = (Map<String, Map<String, Object>>) sc.getAttribute("recordUserMessageMap");
			if(!map.containsKey(toOpenId)){
				Map<String,Object>userMap = new HashMap<String,Object>();
				userMap.put("messNum", "1");
				map.put(toOpenId, userMap);
			}else{
				Map<String,Object> userMap = map.get(toOpenId);
				userMap.put("messNum", "1");
			}
	}
	
}
