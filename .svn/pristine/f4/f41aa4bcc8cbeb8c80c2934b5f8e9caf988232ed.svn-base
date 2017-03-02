package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 接收通知的人
 * @author 3hhjj
 *
 */
@Entity
@Table(name="swj_notice_consume")
public class NoticeConsume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5122253856022557197L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 用户ID或部门ID
	 */
	private String cid;
	
	/**
	 * 用户或部门名称
	 */
	private String name;
	
	/**
	 * 类型：1为用户， 2部门
	 */
	private short ctype;
	
	@ManyToOne(optional = false,cascade=CascadeType.ALL)
    @JoinColumn(name="notice_id")
	private InsideNotice notice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getCtype() {
		return ctype;
	}

	public void setCtype(short ctype) {
		this.ctype = ctype;
	}

	public InsideNotice getNotice() {
		return notice;
	}

	public void setNotice(InsideNotice notice) {
		this.notice = notice;
	}

}
