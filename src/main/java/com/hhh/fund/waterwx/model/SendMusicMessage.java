package com.hhh.fund.waterwx.model;

/*
 * 响应消息之音乐消息
 */
public class SendMusicMessage extends SendBaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    // 音乐  
    private SendMusic SendMusic;  
  
    public SendMusic getMusic() {  
        return SendMusic;  
    }  
  
    public void setMusic(SendMusic sendMusic) {  
        SendMusic = sendMusic;  
    } 
}
