package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;



public class SwjFinishapplyBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String content;

	private String createTime;

	private String dataId;

	private String openId;
	
	private String title;
	
	private int status;

	private PhotoListBean PhotoList;
	
	public SwjFinishapplyBean() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDataId() {
		return this.dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PhotoListBean getPhotoList() {
		return PhotoList;
	}

	public void setPhotoList(PhotoListBean photoList) {
		PhotoList = photoList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}