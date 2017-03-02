package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the swj_questiontype database table.
 * 
 */
public class SwjQuestiontypeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String detail;
	private String id;
	private int ordernumber;
	
	public SwjQuestiontypeBean() {
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
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