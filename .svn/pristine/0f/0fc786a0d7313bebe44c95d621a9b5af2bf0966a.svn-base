package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the swj_feedback database table.
 * 
 */
@Entity
@Table(name="swj_feedback")
@NamedQuery(name="SwjFeedback.findAll", query="SELECT s FROM SwjFeedback s")
public class SwjFeedback implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date feedbackdate;
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;

	private String username;

	private String userrole;
	
	@Column(name="question_id")
	private String questionId;
	
	public SwjFeedback() {
	}
	
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getFeedbackdate() {
		return this.feedbackdate;
	}

	public void setFeedbackdate(Date feedbackdate) {
		this.feedbackdate = feedbackdate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserrole() {
		return this.userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}