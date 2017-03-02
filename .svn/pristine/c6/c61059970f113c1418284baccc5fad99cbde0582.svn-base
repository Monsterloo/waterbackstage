package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the swj_wxexport_user database table.
 * 
 */
@Entity
@Table(name="swj_wxexport_user")
@NamedQuery(name="SwjWxexportUser.findAll", query="SELECT s FROM SwjWxexportUser s")
public class SwjWxexportUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String headimgurl;

	private String nickname;
	
	private String openid;
	
	@Id
	private String questionid;
	
	private int id;

	public SwjWxexportUser() {
	}

	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getQuestionid() {
		return this.questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}