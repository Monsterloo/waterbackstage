package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the swj_wxsignin database table.
 * 
 */
@Entity
@Table(name="swj_wxsignin")
@NamedQuery(name="SwjWxsignin.findAll", query="SELECT s FROM SwjWxsignin s")
public class SwjWxsignin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	private String area;

	private String grade;

	private String userid;

	private String signinposition;

	private String reachname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date signinDate;

	private String signinname;

	private String x;

	private String y;

	public SwjWxsignin() {
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

	public Date getSigninDate() {
		return signinDate;
	}

	public void setSigninDate(Date signinDate) {
		this.signinDate = signinDate;
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