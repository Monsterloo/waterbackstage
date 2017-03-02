package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the swj_attachment database table.
 * 
 */
@Entity
@Table(name="swj_attachment")
@NamedQuery(name="SwjAttachment.findAll", query="SELECT s FROM SwjAttachment s")
public class SwjAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String DiskPath = "/img/pics";

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] file;

	@Column(name="question_id")
	private String questionId;
	
	@Column(name="answer_id")
	private String answerId;
	
	@Column(name="application_id")
	private String applicationId;
	
	public SwjAttachment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	

}