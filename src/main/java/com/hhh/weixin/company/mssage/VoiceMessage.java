package com.hhh.weixin.company.mssage;

import com.hhh.weixin.util.MessageType;

/**
 * voice消息
 * @author 3hhjj
 *
 */
public class VoiceMessage extends Message {

	public VoiceMessage(){
		this.setMsgtype(MessageType.voice);
	}
	
	private MediaBody voice;

	public MediaBody getVoice() {
		return voice;
	}

	public void setVoice(MediaBody voice) {
		this.voice = voice;
	}
}
