package com.hhh.fund.waterwx.model;

import java.util.HashMap;
import java.util.Map;

public class MsgTemplate {

	private String touser;
	private String template_id;
	private String url;
	private Map<String,Object> data = new HashMap<String, Object>();
	
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public static void main(String args[]){
		 MsgTemplate msg = new MsgTemplate();
		 MsgTemplateInfo info = new MsgTemplateInfo();
		 info.setValue("fsfsdfsad");
		 info.setColor("red");
		 
		 MsgTemplateInfo info2 = new MsgTemplateInfo();
		 info2.setValue("fsfsdfsad22222");
		 info2.setColor("red222");
		 
		 Map<String,Object> data = new HashMap<String, Object>();
		 data.put("key", info);
		 data.put("key2", info2);
		 msg.setTouser("ad5saf32sa4df3");
		 msg.setTemplate_id("sdsadsad96451456316");
		 msg.setUrl("http://");
		 msg.setData(data);
//		 String d=JSONObject.fromObject(msg).toString();
//		 System.err.println("d--"+d);
		 
	}
	
}
