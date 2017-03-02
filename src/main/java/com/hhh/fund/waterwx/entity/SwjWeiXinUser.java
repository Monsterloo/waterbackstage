package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 微信用户表
 * @author 3hzxp
 *
 */
@Entity
@Table(name="swj_weixin_user")
@NamedQuery(name="SwjWeiXinUser.findAll", query="SELECT s FROM SwjWeiXinUser s")
public class SwjWeiXinUser implements Serializable {
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=36)
	private String id;
	
	@Column(name="open_id")
	private String openId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_num")
	private String phoneNum;
	
	@Column(name="photo_href")
	private String photoHref;
	
	@Column(name="sex")
	private int sex;
	
	@Column(name="area")
	private String area;
	
	@Column(name="signature")
	private String signature;
	
	@Column(name="score")
	private String score;
	
	@Column(name="is_not_remind")
	private String isNotRemind;
	
	@Column(name="is_repost")
	private String isRepost;
	
	@Column(name="is_join_activity")
	private String isJoinActivity;
	
	@OneToMany(mappedBy="weixinUser")
	private Set<SwjQuestion> swjQuestions;
	
    @OneToMany(mappedBy="weixinUser")
    private Set<SwjQuestionItem> swjQuestionItem;  
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getIsNotRemind() {
		return isNotRemind;
	}

	public void setIsNotRemind(String isNotRemind) {
		this.isNotRemind = isNotRemind;
	}

	public String getIsRepost() {
		return isRepost;
	}

	public void setIsRepost(String isRepost) {
		this.isRepost = isRepost;
	}

	public Set<SwjQuestion> getSwjQuestions() {
		return swjQuestions;
	}

	public void setSwjQuestions(Set<SwjQuestion> swjQuestions) {
		this.swjQuestions = swjQuestions;
	}

	public String getPhotoHref() {
		return photoHref;
	}

	public void setPhotoHref(String photoHref) {
		this.photoHref = photoHref;
	}

	public String getIsJoinActivity() {
		return isJoinActivity;
	}

	public void setIsJoinActivity(String isJoinActivity) {
		this.isJoinActivity = isJoinActivity;
	}

	public Set<SwjQuestionItem> getSwjQuestionItem() {
		return swjQuestionItem;
	}

	public void setSwjQuestionItem(Set<SwjQuestionItem> swjQuestionItem) {
		this.swjQuestionItem = swjQuestionItem;
	}
	
}
