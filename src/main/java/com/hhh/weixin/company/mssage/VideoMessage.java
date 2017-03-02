package com.hhh.weixin.company.mssage;

import com.hhh.weixin.util.MessageType;

/**
 * video消息
 * @author 3hhjj
 *
 */
public class VideoMessage extends Message {

	public VideoMessage(){
		this.setMsgtype(MessageType.video);
	}
	
	private VideoBody video;

	public VideoBody getVideo() {
		return video;
	}

	public void setVideo(VideoBody video) {
		this.video = video;
	} 
}
