package com.hhh.fund.waterwx.model;


/** 
 * 普通按钮（子按钮） 
 *  
 * @author fws 
 * @date 2014-1-23 
 * click：
 *  用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event	
 *  的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
 * view：
 *  用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的url值
 * （即网页链接），达到打开网页的目的，建议与网页授权获取用户基本信息接口结合，获得用户的登入个人信息。
 * 
 * 
 */
public class CommonButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String type;      //按钮的类型
    
    private String key;       //KEY 值 (click必有)
    
    private String url;       //路径 (view必有)
  
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getKey() {  
        return key;  
    }  
  
    public void setKey(String key) {  
        this.key = key;  
    }
}
