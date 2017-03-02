

package com.hhh.fund.waterwx.webmodel;



import java.io.Serializable;
import javax.persistence.*;



public class SwjWeiXinUserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String area;

	@Column(name="is_not_remind")
	private int isNotRemind;

	@Column(name="is_repost")
	private int isRepost;

	private String name;

	@Column(name="open_id")
	private String openId;

	@Column(name="phone_num")
	private String phoneNum;

	@Column(name="photo_href")
	private String photoHref;

	private int score;

	private int sex;

	private String signature;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getIsNotRemind() {
		return this.isNotRemind;
	}

	public void setIsNotRemind(int isNotRemind) {
		this.isNotRemind = isNotRemind;
	}

	public int getIsRepost() {
		return this.isRepost;
	}

	public void setIsRepost(int isRepost) {
		this.isRepost = isRepost;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhotoHref() {
		return this.photoHref;
	}

	public void setPhotoHref(String photoHref) {
		this.photoHref = photoHref;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}