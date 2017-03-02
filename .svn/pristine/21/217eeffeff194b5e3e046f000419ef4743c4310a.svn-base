package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the swj_user database table.
 * 
 */
@Entity
@Table(name = "swj_user")
@NamedQuery(name = "SwjUser.findAll", query = "SELECT s FROM SwjUser s")
public class SwjUser implements Serializable {
	private static final long serialVersionUID = 1L;

	

	
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	private String area;
	/**
	 * 投诉时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	private String email;
	
	private String job;

	private String loginname;

	private String name;

	private String password;

	private String phone;
	private String userid;
	
	
	public SwjUser() {
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}