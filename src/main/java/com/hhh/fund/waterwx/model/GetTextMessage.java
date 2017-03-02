package com.hhh.fund.waterwx.model;

/*
 * 文本消息 
 */
public class GetTextMessage extends GetBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    } 
	
}
