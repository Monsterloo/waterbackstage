package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the swj_wxfollowuserinfo database table.
 * 
 */
@Entity
@Table(name="swj_wxfollowuserinfo")
@NamedQuery(name="SwjWxfollowuserinfo.findAll", query="SELECT s FROM SwjWxfollowuserinfo s")
public class SwjWxfollowuserinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nickname;
	
	@Id
	private String openid;
	
	private String headimgurl;

	public SwjWxfollowuserinfo() {
	}

	public String getNickname() {
		return nickname;
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

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

}