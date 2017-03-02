package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 内部通知
 *
 */
@Entity
@Table(name="swj_inside_notice")
public class InsideNotice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 标题
	 */
	@Column
	private String title;
	
	/**
	 * 内容
	 */
	@Column(length=1000)
	private String content;
	
	/**
	 * 发送通知的日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date ctime;

	/**
	 * 署名
	 */
	private String author;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 通知对象
	 */
	@OneToMany(mappedBy="notice", cascade=CascadeType.ALL)
	private Set<NoticeConsume> sonsumes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<NoticeConsume> getSonsumes() {
		return sonsumes;
	}

	public void setSonsumes(Set<NoticeConsume> sonsumes) {
		this.sonsumes = sonsumes;
	}
}
