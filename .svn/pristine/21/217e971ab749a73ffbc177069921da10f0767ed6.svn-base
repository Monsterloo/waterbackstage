package com.hhh.fund.waterwx.entity;


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

@Entity
@Table(name="swj_question_item")
@NamedQuery(name="SwjQuestionItems.findAll", query="SELECT s FROM SwjQuestionItems s")
public class SwjQuestionItems {
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	@Column(name="data_id")
	private String dataId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="open_id")
	private String openId;

	
	@Column(name="to_open_id")
	private String toOpenId;
	
	@Column(name="create_time")
	private String createTime;
	
	@Column(name="content")
	private String content;
	
	@Column(name="status")
	private int status;
	
	@Column(name="city_area_type")
	private String cityAreaType;
	
	
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
	public String toString(){
		return "id="+this.id+",dataId="+dataId+",type"+type;
	}
	public String getToOpenId() {
		return toOpenId;
	}
	public void setToOpenId(String toOpenId) {
		this.toOpenId = toOpenId;
	}
	public String getCityAreaType() {
		return cityAreaType;
	}
	public void setCityAreaType(String cityAreaType) {
		this.cityAreaType = cityAreaType;
	}
	
	
}
