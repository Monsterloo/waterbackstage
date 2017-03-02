package com.hhh.fund.waterwx.webmodel;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


public class SwjQuestionItemsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	
	private String dataId;
	
	private String type;
	
	private String openId;
	
	private String toOpenId;
	
	private String createTime;
	
	private String content;
	
	private int status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getToOpenId() {
		return toOpenId;
	}
	public void setToOpenId(String toOpenId) {
		this.toOpenId = toOpenId;
	}
	public String toString(){
		return "id="+this.id+",dataId="+dataId+",type"+type;
	}
	
}
