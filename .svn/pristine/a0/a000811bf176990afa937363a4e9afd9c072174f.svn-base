package com.hhh.fund.waterwx.model;

import net.sf.json.JSONObject;

public class PushTextMessage extends PushBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String content;    //推送内容

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/*
	 * 推送文本
	 * {
	 *   "touser":"OPENID",
	 *   "msgtype":"text",
	 *   "text":
	 *   {
	 *        "content":"Hello World"
	 *    }
     *   } 
	 */
	
	public static String parseJSONString(PushTextMessage pushText){
		if(pushText==null&&pushText.getTouser()!=null)return null;
	    JSONObject json = new JSONObject();
	    json.put("touser", pushText.getTouser());
	    json.put("msgtype", "text");
	    JSONObject content = new JSONObject();
	    content.put("content", pushText.getContent());
	    json.put("text", content);
	    return json.toString();
	}
}
