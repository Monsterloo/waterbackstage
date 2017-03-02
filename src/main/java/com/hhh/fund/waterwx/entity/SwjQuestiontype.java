package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the swj_questiontype database table.
 * 
 */
@Entity
@Table(name="swj_questiontype")
@NamedQuery(name="SwjQuestiontype.findAll", query="SELECT s FROM SwjQuestiontype s")
public class SwjQuestiontype implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String detail;
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	private int ordernumber;
	
	public SwjQuestiontype() {
	}
	
	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}