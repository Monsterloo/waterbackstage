package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the swj_question database table.
 * 
 */

public class SwjQuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int pageNo = 1;
	
	private int size = 10;
	
	private long lastRecordTime;
	
	private String code;

	private String complainDate;
	
	private String id;

	private String questioncontent;

	private String questiontype;

	private String reachname;

	private int status;
	private String statusStr;//也是投诉状态，只不过是从页面接收数据，格式 为  1,2,3,4
	
	private String openid;
	
	private String nickname;
	
	private String headimgurl;
	
	private String questionposition;

	private String x;
	private String y;
	
	private String leftRight;
	private String partName;
	private String riverCode;
	private String area;
	
	private String phone;
	
	private int cou;
	
	private String length;
	
	private String personid;
	
	private String personname;
	
	private String areaPersonId;
	
	private String areaPersonName;
	
	private String streetPersonId;
	
	private String streetPersonName;
	
	private String villagePersonId;
	
	private String villagePersonName;
	
	private String departmentPersonId;
	
	private String departmentPersonName;
	
	private Date areaLimitDate;
	
	private Date streetLimitDate;
	
	private Date villageLimitDate;
	
	private String iscross;
	
	private String type;
	
	private String exporttime;
	
	private String cityName ;//市处理人
	
	private String areaName ; // 区处理人
	
	
	/**
	 * 市分派情况
	 **/
	
	private String citycontent;
	
	/**
	 * 区级河长反馈内容
	 * */
	private String areacontent;
	/**
	 * 级街镇河长反馈内容
	 * */
	private String streetcontent;
	/**
	 * 村级河长反馈内容
	 * */
	private String villagecontent;
	
	/**
	 * 查询条件startTime;
	 */
	private String startTime;
	/**
	 * 查询条件endTime;
	 */
	private String endTime;
	
	/**
	 * 催办状态
	 * */
	private String urgency;
	
	private String urgencyDate;
	
	private String remark;
	
	private String isMyOwn;//是圈子还是自己个人空间
	
	private String rolename;
	
	private String userid;
	
	private String city;
	
	private String dealmanid;
	
	//我关注的questionid
	private String questionIdsIattention;
	
	private String ciryAssignedcode;

	private String areaAssignedcode;
	
	private String importance;
	
	//分派时间
	private String assignedDate;
	
	//申请办结时间
	private String applyDate;
	
	//完结时间
	private String finishDate;
	
	
	public String getAreacontent() {
		return areacontent;
	}

	public void setAreacontent(String areacontent) {
		this.areacontent = areacontent;
	}

	public String getStreetcontent() {
		return streetcontent;
	}

	public void setStreetcontent(String streetcontent) {
		this.streetcontent = streetcontent;
	}

	public String getVillagecontent() {
		return villagecontent;
	}

	public void setVillagecontent(String villagecontent) {
		this.villagecontent = villagecontent;
	}
	public String getIscross() {
		return iscross;
	}

	public void setIscross(String iscross) {
		this.iscross = iscross;
	}
	
	public Date getAreaLimitDate() {
		return areaLimitDate;
	}
	public void setAreaLimitDate(Date areaLimitDate) {
		this.areaLimitDate = areaLimitDate;
	}
	public Date getStreetLimitDate() {
		return streetLimitDate;
	}
	public void setStreetLimitDate(Date streetLimitDate) {
		this.streetLimitDate = streetLimitDate;
	}
	public Date getVillageLimitDate() {
		return villageLimitDate;
	}
	public void setVillageLimitDate(Date villageLimitDate) {
		this.villageLimitDate = villageLimitDate;
	}
	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}
	
	public String getAreaPersonName() {
		return areaPersonName;
	}
	public void setAreaPersonName(String areaPersonName) {
		this.areaPersonName = areaPersonName;
	}
	public String getStreetPersonName() {
		return streetPersonName;
	}
	public void setStreetPersonName(String streetPersonName) {
		this.streetPersonName = streetPersonName;
	}
	public String getVillagePersonName() {
		return villagePersonName;
	}
	public void setVillagePersonName(String villagePersonName) {
		this.villagePersonName = villagePersonName;
	}
	
	public String getRiverCode() {
		return riverCode;
	}

	public void setRiverCode(String riverCode) {
		this.riverCode = riverCode;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String[] attachIds) {
		this.attachIds = attachIds;
	}

	private String[] attachIds;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public SwjQuestionBean() {
	}
	
	public String getLeftRight() {
		return leftRight;
	}

	public void setLeftRight(String leftRight) {
		this.leftRight = leftRight;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getQuestionposition() {
		return questionposition;
	}

	public void setQuestionposition(String questionposition) {
		this.questionposition = questionposition;
	}
	
	public String getComplainDate() {
		return this.complainDate;
	}

	public void setComplainDate(Date complainDate) {
		if(complainDate!=null){
			this.complainDate = sdf.format(complainDate);
		}else{
			this.complainDate = null;
		}
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestioncontent() {
		return this.questioncontent;
	}

	public void setQuestioncontent(String questioncontent) {
		this.questioncontent = questioncontent;
	}

	public String getQuestiontype() {
		return this.questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}

	public String getReachname() {
		return this.reachname;
	}

	public void setReachname(String reachname) {
		this.reachname = reachname;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExporttime() {
		return exporttime;
	}
	
	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
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

	public String getVillagePersonId() {
		return villagePersonId;
	}

	public void setVillagePersonId(String villagePersonId) {
		this.villagePersonId = villagePersonId;
	}

	public void setExporttime(String exporttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(exporttime!=null){
			this.exporttime = sdf.format(exporttime);
		}else{
			this.exporttime = null;
		}
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/*public void setComplainDate(String complainDate) {
		this.complainDate = complainDate;
	}*/
	
	public String getDepartmentPersonId() {
		return departmentPersonId;
	}

	public void setDepartmentPersonId(String departmentPersonId) {
		this.departmentPersonId = departmentPersonId;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getUrgencyDate() {
		return urgencyDate;
	}

	public void setUrgencyDate(String urgencyDate) {
		this.urgencyDate = urgencyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		System.out.println("this.rolename>>>>>>>>>>>>");
		this.rolename = rolename;
	}

	public String getIsMyOwn() {
		return isMyOwn;
	}

	public void setIsMyOwn(String isMyOwn) {
		this.isMyOwn = isMyOwn;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDealmanid() {
		return dealmanid;
	}

	public void setDealmanid(String dealmanid) {
		this.dealmanid = dealmanid;
	}

	public long getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(long lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

	public String getDepartmentPersonName() {
		return departmentPersonName;
	}

	public void setDepartmentPersonName(String departmentPersonName) {
		this.departmentPersonName = departmentPersonName;
	}

	public String getQuestionIdsIattention() {
		return questionIdsIattention;
	}

	public void setQuestionIdsIattention(String questionIdsIattention) {
		this.questionIdsIattention = questionIdsIattention;
	}

	public String getCiryAssignedcode() {
		return ciryAssignedcode;
	}

	public void setCiryAssignedcode(String ciryAssignedcode) {
		this.ciryAssignedcode = ciryAssignedcode;
	}

	public String getAreaAssignedcode() {
		return areaAssignedcode;
	}

	public void setAreaAssignedcode(String areaAssignedcode) {
		this.areaAssignedcode = areaAssignedcode;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getCitycontent() {
		return citycontent;
	}

	public void setCitycontent(String citycontent) {
		this.citycontent = citycontent;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public void setComplainDate(String complainDate) {
		this.complainDate = complainDate;
	}

}