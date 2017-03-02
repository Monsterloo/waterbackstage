package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the rivers database table.
 * 
 */
@Entity
@Table(name="rivers")
@NamedQuery(name="River.findAll", query="SELECT r FROM River r")
public class River implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;
	
	private String area;

	private String end;

	private String grade;

	private String length;

	@Column(name="river_code")
	private String riverCode;

	@Column(name="river_name")
	private String riverName;

	private String start;
	@Column(name="cover_area")
	private String coverArea;
	
	private String remark;
	
	private String width;
	@Transient
	private int cou;
	
	private String belongPollRiver;
	
//	@OneToOne(mappedBy = "river")
//	private SwjQuestion swjQuestion;
	
	
	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

	public River() {
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	public String getCoverArea() {
		return coverArea;
	}

	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getRiverCode() {
		return this.riverCode;
	}

	public void setRiverCode(String riverCode) {
		this.riverCode = riverCode;
	}

	public String getRiverName() {
		return this.riverName;
	}

	public void setRiverName(String riverName) {
		this.riverName = riverName;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getBelongPollRiver() {
		return belongPollRiver;
	}

	public void setBelongPollRiver(String belongPollRiver) {
		this.belongPollRiver = belongPollRiver;
	}
	
}