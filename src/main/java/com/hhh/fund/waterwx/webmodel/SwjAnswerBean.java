package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the swj_answer database table.
 * 
 */
public class SwjAnswerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String answercontent;

	private String answerDate;
	private String id;

	private String questionId;
	private String username;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public SwjAnswerBean() {
	}

	public String getAnswercontent() {
		return this.answercontent;
	}

	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}

	public String getAnswerDate() {
		return this.answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		if(answerDate!=null){
			this.answerDate = sdf.format(answerDate);
		}else{
			this.answerDate = null;
		}
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}