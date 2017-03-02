package com.hhh.fund.waterwx.webmodel;


public class SwjQuestionItemBean {
	
	public static final String REPOST="repost";
	public static final String PRAISE="praise";
	public static final String REPLY="reply";
	public static final String ATTENTION = "attention";
	
	
	private String id;
	
	private String dataId;
	
	private String type;
	
	private String openId;
	
	private String toOpenId;
	
	private String createTime;
	
	private String content;
	
	private int status;
	
	private String weixinUserName;
	
	private String openIdPhotoHref;
	
	private String lastRecordTime;
	
	private String title;
	
	private String personId;
	
	private String villPersonId;
	
	private String areaPersonId;
	
	private String streetPersonId;
	
	private String departmentPersonId;
	
	private String area;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getWeixinUserName() {
		return weixinUserName;
	}

	public void setWeixinUserName(String weixinUserName) {
		this.weixinUserName = weixinUserName;
	}

	public String getOpenIdPhotoHref() {
		return openIdPhotoHref;
	}

	public void setOpenIdPhotoHref(String openIdPhotoHref) {
		this.openIdPhotoHref = openIdPhotoHref;
	}

	public String getToOpenId() {
		return toOpenId;
	}

	public void setToOpenId(String toOpenId) {
		this.toOpenId = toOpenId;
	}

	public String getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(String lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getVillPersonId() {
		return villPersonId;
	}

	public void setVillPersonId(String villPersonId) {
		this.villPersonId = villPersonId;
	}

	public String getAreaPersonId() {
		return areaPersonId;
	}

	public void setAreaPersonId(String areaPersonId) {
		this.areaPersonId = areaPersonId;
	}

	public String getStreetPersonId() {
		return streetPersonId;
	}

	public void setStreetPersonId(String streetPersonId) {
		this.streetPersonId = streetPersonId;
	}

	public String getDepartmentPersonId() {
		return departmentPersonId;
	}

	public void setDepartmentPersonId(String departmentPersonId) {
		this.departmentPersonId = departmentPersonId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
}
