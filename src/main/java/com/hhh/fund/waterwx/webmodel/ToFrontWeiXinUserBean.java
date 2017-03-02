package com.hhh.fund.waterwx.webmodel;

import com.hhh.fund.waterwx.util.StringUtil;

public class ToFrontWeiXinUserBean {
	
	public static final int MAX_LEVEL=5;
	
	private String UserId;
	
	private String Name;
	
	private String PhoneNum;
	
	private String PhotoHref;
	
	private String Level;
	
	private String Sex;
	
	private String signature;
	
	private String Address;

	
	public static void main(String[] args) {
	}
	
	
	public static String changeScoreToLevel(String score){
		if(StringUtil.isBlank(score)){
			score = "0";
		}
		int level = (int)Math.sqrt(Double.parseDouble(score)+1);
		if(level>MAX_LEVEL){
			return MAX_LEVEL+"";
		}
		return level+"";
	}
	
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhotoHref() {
		return PhotoHref;
	}

	public void setPhotoHref(String photoHref) {
		PhotoHref = photoHref;
	}
	
}
