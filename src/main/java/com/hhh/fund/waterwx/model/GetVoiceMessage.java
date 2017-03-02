package com.hhh.fund.waterwx.model;

/*
 * 音频消息 
 */
public class GetVoiceMessage extends GetBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    // 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    } 
}
