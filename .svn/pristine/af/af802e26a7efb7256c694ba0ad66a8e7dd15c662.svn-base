package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;


/**
 * 用户投诉
 * 
 */
@Entity
@Table(name="swj_question")
@NamedQuery(name="SwjQuestion.findAll", query="SELECT s FROM SwjQuestion s")
public class SwjQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 问题编号
	 */
	private String code;

	/**
	 * 投诉时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date complainDate;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 问题内容
	 */
	private String questioncontent;

	/**
	 * 问题类型
	 */
	private String questiontype;

	/**
	 * 河段名称
	 */
	private String reachname;

	
	public static final String PENDING="1";
	public static final String TOBESOLVED="2";
	public static final String TOBEFOLLOWUP="3";
	public static final String TOBEOVER="4";
	public static final String END="5";
	public static final String placeOnFile="10";
	
	/**
	 * 状态( 1:待受理（待受理） 2:待解决（待解决）   3：待跟进（待解决）   4：待完结（待完结）  5：已完结（待评价）  )   10:已经评价过，归档
	 * 3 待跟进  职能部门+区域            4、待完结  镇街提交到区级
	 */
	private int status;
	
	/**
	 * 微信用户
	 */
	private String openid;
	
	/**
	 * 微信用户昵称
	 */
	private String nickname;
	
	/**
	 * 微信用户头像url
	 */
	private String headimgurl;
	
	/**
	 * 问题位置
	 */
	private String questionposition;
	/**
	 * 经度
	 * */
	private String x;
	/**
	 * 纬度
	 * */
	private String y;
	/**
	 * 河道编号
	 * */
	//@OneToMany
	@Column(name="river_code")
	private String riverCode;
	
	@Column(name="left_right")
	private String leftRight;
	
	@Column(name="part_name")
	private String partName;
	
	private String area;
	
	private String phone;
	@Transient
	private int cou;
	@Transient
	private String month;
	
	private String length;
	
	private String personid;
	
	private String personname;
	
	@Column(name="area_person_id")
	private String areaPersonId;
	
	@Column(name="area_person_name")
	private String areaPersonName;
	
	@Column(name="street_person_id")
	private String streetPersonId;
	
	@Column(name="street_person_name")
	private String streetPersonName;
	
	@Column(name="village_person_id")
	private String villagePersonId;
	
	@Column(name="village_person_name")
	private String villagePersonName;
	//地区截止时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="area_limit_date")
	private Date areaLimitDate;
	//街镇处理截止时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="street_limit_date")
	private Date streetLimitDate;
	//村级处理截止时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="village_limit_date")
	private Date villageLimitDate;
	/**
	 * 是否 是跨级下发的投诉 0：是
	 * */
	private String iscross;
	
	/**
	 * 是否已导出
	 * */
	private String type;
	/**
	 * 导出时间
	 * */
	@Temporal(TemporalType.TIMESTAMP)
	private Date exporttime;
	
	@Column(name="has_right")
	private int hasRight;
	
	@Column(name="attachment_weixin_server_id")
	private String attachmentWeixinServiceId;
	

		/**
	 * 催办状态
	 * */
	private String urgency;
	
	
		/**
	 * 催办日期
	 * */
	@Temporal(TemporalType.TIMESTAMP)
	private Date urgencyDate;
	
	
	/**
	 * 关注数量
	 */
	@Column(name="attention_count")
	private int attentionCount;
	
	/**
	 * 点赞数量
	 */
	@Column(name="praise_count")
	private int praiseCount ;
	
	
	/**
	 * 转发
	 */
	@Column(name="repost_count")
	private int repostCount;
	
	
	/**
	 * 三级河长备注内容
	 */
	private String remark;
	
	@Column(name="city")
	private String city;
	
	private String dealmanid;
	
	@Column(name="department_person_id")
	private String departmentPersonId;
	
	@Column(name="department_person_name")
	private String departmentPersonName;
	
	//局发文编号
	@Column(name="ciry_assignedcode")
	private String ciryAssignedcode;
	
	//治水办发文编号
	@Column(name="area_assignedcode")
	private String areaAssignedcode;
	
	private String importance;
	
	/*private String cityName ;//市处理人
	
	private String areaName ; // 区处理人
	
	private String cityContent;//市处理详情
	
	private String areaContent;//去处理详情
*/	
	
	//分派时间
	private String assignedDate;
	
	//申请办结时间
	private String applyDate;
	
	//完结时间
	private String finishDate;
	
	
//    @OneToOne(optional = true,fetch=FetchType.EAGER)
//	@JoinColumn(name = "river_code", referencedColumnName = "river_code",insertable = false, updatable = false)
//	private River river;
	
	@ManyToOne(optional = false,fetch=FetchType.EAGER)
	@JoinColumn(name="openid",referencedColumnName = "open_id",insertable = false, updatable = false)
	private SwjWeiXinUser weixinUser;
	
	
	/**
	 * cascade=CascadeType.ALL 级联 同步删除
	 * 如果投诉被删除了，那么itemS当然同步删除
	 
	@OneToMany(mappedBy="question")
	private Set<SwjQuestionItem>questionItems;*/
	
	public SwjQuestion() {
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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

	public String getRiverCode() {
		return riverCode;
	}

	public void setRiverCode(String riverCode) {
		this.riverCode = riverCode;
	}


	public String getCode() {
		return this.code;
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

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Date getComplainDate() {
		return this.complainDate;
	}

	public void setComplainDate(Date complainDate) {
		this.complainDate = complainDate;
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
	
	public Date getExporttime() {
		return exporttime;
	}
	
	public void setExporttime(Date exporttime) {
		this.exporttime = exporttime;
	}

	public SwjWeiXinUser getWeixinUser() {
		return weixinUser;
	}

	public void setWeixinUser(SwjWeiXinUser weixinUser) {
		this.weixinUser = weixinUser;
	}

/*	public Set<SwjQuestionItem> getQuestionItems() {
		return questionItems;
	}

	public void setQuestionItems(Set<SwjQuestionItem> questionItems) {
		this.questionItems = questionItems;
	}*/

	public int getHasRight() {
		return hasRight;
	}

	public void setHasRight(int hasRight) {
		this.hasRight = hasRight;
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

	public String getAttachmentWeixinServiceId() {
		return attachmentWeixinServiceId;
	}

	public void setAttachmentWeixinServiceId(String attachmentWeixinServiceId) {
		this.attachmentWeixinServiceId = attachmentWeixinServiceId;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	
	public int getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}


	public int getRepostCount() {
		return repostCount;
	}

	public void setRepostCount(int repostCount) {
		this.repostCount = repostCount;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public Date getUrgencyDate() {
		return urgencyDate;
	}

	public void setUrgencyDate(Date urgencyDate) {
		this.urgencyDate = urgencyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentPersonId() {
		return departmentPersonId;
	}

	public void setDepartmentPersonId(String departmentPersonId) {
		this.departmentPersonId = departmentPersonId;
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

	public String getDepartmentPersonName() {
		return departmentPersonName;
	}

	public void setDepartmentPersonName(String departmentPersonName) {
		this.departmentPersonName = departmentPersonName;
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

	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	/*public String getCityName() {
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

	public String getCityContent() {
		return cityContent;
	}

	public void setCityContent(String cityContent) {
		this.cityContent = cityContent;
	}

	public String getAreaContent() {
		return areaContent;
	}

	public void setAreaContent(String areaContent) {
		this.areaContent = areaContent;
	}*/

	
	
}