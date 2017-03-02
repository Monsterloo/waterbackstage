package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the swj_wxquestion database table.
 * 
 */
@Entity
@Table(name="swj_wxquestion")
@NamedQuery(name="SwjWxquestion.findAll", query="SELECT s FROM SwjWxquestion s")
public class SwjWxquestion implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	private String title;

	public SwjWxquestion() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}