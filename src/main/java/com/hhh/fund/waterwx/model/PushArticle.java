package com.hhh.fund.waterwx.model;

import net.sf.json.JSONObject;

public class PushArticle extends PushBaseMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;                 //标题
	
	private String description;           //描述
	
	private String url;                   //点击后跳转的链接 
	
	private String picurl;                //图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80 

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public static JSONObject parseJSONObject(PushArticle pushArticle){
		if(pushArticle==null)return null;
	    JSONObject json = new JSONObject();
	    json.put("title", ""+pushArticle.getTitle());
	    json.put("description", ""+pushArticle.getDescription());
	    json.put("url", ""+pushArticle.getUrl());
	    json.put("picurl", ""+pushArticle.getPicurl());
	    return json;
		
	}
}
