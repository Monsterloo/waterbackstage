package com.hhh.fund.waterwx.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hhh.fund.waterwx.entity.SysUcenterDict;

public class CacheDataUtils {

	public static boolean isTestAccount(HttpServletRequest request){
		List<SysUcenterDict> dicts = (List<SysUcenterDict>) request.getServletContext().getAttribute(ComplainConstants.TESTACCOUNT);
		String openId = (String) request.getSession().getAttribute(Constants.SESSEION_WECHAT_OPEN_ID);
		boolean isTestAccount = false;
		for(SysUcenterDict dict:dicts){
			if(dict.getCode().equals(openId)){
				isTestAccount = true;
			}
		}
		return isTestAccount;
	}
	
}
