package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the swj_wxsignin database table.
 * 
 */
public class SwjWxsigninBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String area;

	private String grade;

	private String userid;

	private String signinposition;

	private String reachname;

	private String signinDate;

	private String signinname;

	private String x;

	private String y;

	private String[] attachIds;
	
	public SwjWxsigninBean() {
	}

	public String[] getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String[] attachIds) {
		this.attachIds = attachIds;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSigninposition() {
		return signinposition;
	}

	public void setSigninposition(String signinposition) {
		this.signinposition = signinposition;
	}

	public String getReachname() {
		return reachname;
	}

	public void setReachname(String reachname) {
		this.reachname = reachname;
	}

	public String getSigninDate() {
		return signinDate;
	}

	public void setSigninDate(Date signinDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(signinDate!=null){
			this.signinDate = sdf.format(signinDate);
		}else{
			this.signinDate = null;
		}
	}

	public String getSigninname() {
		return signinname;
	}

	public void setSigninname(String signinname) {
		this.signinname = signinname;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	

}