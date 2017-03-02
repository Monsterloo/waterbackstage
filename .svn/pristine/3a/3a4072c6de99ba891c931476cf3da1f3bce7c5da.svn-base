package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the swj_answer database table.
 * 
 */
@Entity
@Table(name="swj_answer")
@NamedQuery(name="SwjAnswer.findAll", query="SELECT s FROM SwjAnswer s")
public class SwjAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	private String answercontent;

	@Temporal(TemporalType.TIMESTAMP)
	private Date answerDate;
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	@Column(name="question_id")
	private String questionId;

	private String username;
	
	public SwjAnswer() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getAnswercontent() {
		return this.answercontent;
	}

	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}

	public Date getAnswerDate() {
		return this.answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

}