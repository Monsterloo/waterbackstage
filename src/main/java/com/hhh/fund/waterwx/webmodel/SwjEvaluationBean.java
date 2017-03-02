package com.hhh.fund.waterwx.webmodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


public class SwjEvaluationBean {


	private String id;
	
	private String questionId;
	
	private String attritudeStart;
	
	private String efficiencyStart;
	
	private String qualityStar;
	
	private String remark;
	
	private Date createTime;
	
	private int status;
	
	private String applicationPersonId;
	
	private PhotoListBean PhotoList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAttritudeStart() {
		return attritudeStart;
	}
	public void setAttritudeStart(String attritudeStart) {
		this.attritudeStart = attritudeStart;
	}
	public String getEfficiencyStart() {
		return efficiencyStart;
	}
	public void setEfficiencyStart(String efficiencyStart) {
		this.efficiencyStart = efficiencyStart;
	}
	public String getQualityStar() {
		return qualityStar;
	}
	public void setQualityStar(String qualityStar) {
		this.qualityStar = qualityStar;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getApplicationPersonId() {
		return applicationPersonId;
	}
	public void setApplicationPersonId(String applicationPersonId) {
		this.applicationPersonId = applicationPersonId;
	}
	public PhotoListBean getPhotoList() {
		return PhotoList;
	}
	public void setPhotoList(PhotoListBean photoList) {
		PhotoList = photoList;
	}
	
}
