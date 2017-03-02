package com.hhh.fund.waterwx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="swj_evaluation")
@NamedQuery(name="SwjEvaluation.findAll", query="SELECT s FROM SwjEvaluation s")
public class SwjEvaluation {

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;
	
	@Column(name="question_id")
	private String questionId;
	
	@Column(name="attitude_star")
	private String attritudeStart;
	
	@Column(name="efficienty_star")
	private String efficiencyStart;
	
	@Column(name="quality_star")
	private String qualityStar;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="status")
	private int status;
	
	@Column(name="application_person_id")
	private String applicationPersonId;
	
	
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
	
	
}
