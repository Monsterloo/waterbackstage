package com.hhh.fund.waterwx.webmodel;

import java.util.List;

public class SwjComplainBean {
	
	private String openId;
	
	private String sex;
	
	private String Name;
	
	private String Photo;
	
	private String Level;
	
//	private String ComplainId;
	private String questionId;
	
//	private String ComplainNo;
	private String questionCode;
	
//	private String ComplainType;
	private String questiontype;
	
	private String questionposition;
	
//	private String Title;
	private String questioncontent;
	
	private String Status;
	
//	private String ComplainTime;
	private String complainDate;
	
	private String TimeDesc;
	
//	private String DistrictId;
	private String rivercode;
	
//	private String district;
	private String riverName;
	
	private String Coordinate;
	
	private String Address;
	
	private PhotoListBean PhotoList;
	
	private String RepostCount="0";
	
	private String myRepost;
	
	private String ReplayCount="0";
	
	private String myReplay;
	
	private String PraiseCount="0";
	
	private String myPraise;
	
	private String attentionCount="0";
	
	private String myAttention;
	
	private String area;
	
	private String phoneNum;
	
	private int flowcount; 
	
	private List<SwjQuestionItemBean>flowlist;
	
	private String signature;

	private String code;

	private String x;

	private String y;
	
	private String serverWeixinPicId;
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}


	public String getTimeDesc() {
		return TimeDesc;
	}

	public void setTimeDesc(String timeDesc) {
		TimeDesc = timeDesc;
	}

	public String getCoordinate() {
		return Coordinate;
	}

	public void setCoordinate(String coordinate) {
		Coordinate = coordinate;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public PhotoListBean getPhotoList() {
		return PhotoList;
	}

	public void setPhotoList(PhotoListBean photoList) {
		PhotoList = photoList;
	}

	public String getRepostCount() {
		return RepostCount;
	}

	public void setRepostCount(String repostCount) {
		RepostCount = repostCount;
	}

	public String getReplayCount() {
		return ReplayCount;
	}

	public void setReplayCount(String replayCount) {
		ReplayCount = replayCount;
	}

	public String getPraiseCount() {
		return PraiseCount;
	}

	public void setPraiseCount(String praiseCount) {
		PraiseCount = praiseCount;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}

	public String getQuestioncontent() {
		return questioncontent;
	}

	public void setQuestioncontent(String questioncontent) {
		this.questioncontent = questioncontent;
	}

	public String getComplainDate() {
		return complainDate;
	}

	public void setComplainDate(String complainDate) {
		this.complainDate = complainDate;
	}

	public String getRivercode() {
		return rivercode;
	}

	public void setRivercode(String rivercode) {
		this.rivercode = rivercode;
	}

	public String getRiverName() {
		return riverName;
	}

	public void setRiverName(String riverName) {
		this.riverName = riverName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMyRepost() {
		return myRepost;
	}

	public void setMyRepost(String myRepost) {
		this.myRepost = myRepost;
	}

	public String getMyReplay() {
		return myReplay;
	}

	public void setMyReplay(String myReplay) {
		this.myReplay = myReplay;
	}

	public String getMyPraise() {
		return myPraise;
	}

	public void setMyPraise(String myPraise) {
		this.myPraise = myPraise;
	}

	public String getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(String attentionCount) {
		this.attentionCount = attentionCount;
	}

	public String getMyAttention() {
		return myAttention;
	}

	public void setMyAttention(String myAttention) {
		this.myAttention = myAttention;
	}

	public int getFlowcount() {
		return flowcount;
	}

	public void setFlowcount(int flowcount) {
		this.flowcount = flowcount;
	}

	public List<SwjQuestionItemBean> getFlowlist() {
		return flowlist;
	}

	public void setFlowlist(List<SwjQuestionItemBean> flowlist) {
		this.flowlist = flowlist;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestionposition() {
		return questionposition;
	}

	public void setQuestionposition(String questionposition) {
		this.questionposition = questionposition;
	}

	public void setX(String x) {
		this.x = x;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getX() {
		return x;
	}

	public String getY() {
		return y;
	}

	public String getServerWeixinPicId() {
		return serverWeixinPicId;
	}

	public void setServerWeixinPicId(String serverWeixinPicId) {
		this.serverWeixinPicId = serverWeixinPicId;
	}
}
