package com.hhh.fund.waterwx.webmodel;

import java.util.HashSet;
import java.util.Set;

import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.entity.InsideNotice;
import com.hhh.fund.waterwx.entity.NoticeConsume;

public class InsideNoticeBean {

	private String id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 发送通知的日期
	 */
	private String ctime;

	/**
	 * 署名
	 */
	private String author;
	
	/**
	 * 状态  默认为：1，未发送，2：发送
	 */
	private String status;
	
	/**
	 * 通知对象
	 */
	private Set<NoticeConsumeBean> sonsumes;
	
	public void toBean(InsideNotice notice){
		this.setId(notice.getId());
		this.setAuthor(notice.getAuthor());
		this.setContent(notice.getContent());
		this.setCtime(StringUtil.dateFormat(notice.getCtime()));
		this.setStatus(notice.getStatus());
		this.setTitle(notice.getTitle());
		Set<NoticeConsumeBean> consumes = new HashSet<>();
		for(NoticeConsume bean : notice.getSonsumes()){
			NoticeConsumeBean n = new NoticeConsumeBean();
			n.setId(bean.getId());
			n.setCtype(bean.getCtype());
			n.setCid(bean.getCid());
			n.setName(bean.getName());
			consumes.add(n);
		}
		this.setSonsumes(consumes);
	}
	
	public InsideNotice toEntity(){
		InsideNotice notice = new InsideNotice();
		notice.setId(this.getId());
		notice.setAuthor(this.getAuthor());
		notice.setContent(this.getContent());
		notice.setCtime(StringUtil.parstDate(this.getCtime()));
		notice.setStatus(this.getStatus());
		notice.setTitle(this.getTitle());
		Set<NoticeConsume> consumes = new HashSet<>();
		for(NoticeConsumeBean bean : this.getSonsumes()){
			NoticeConsume n = new NoticeConsume();
			n.setId(bean.getId());
			n.setCtype(bean.getCtype());
			n.setCid(bean.getCid());
			n.setName(bean.getName());
			n.setNotice(notice);
			consumes.add(n);
		}
		notice.setSonsumes(consumes);
		return notice;
	}

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

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
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

	public Set<NoticeConsumeBean> getSonsumes() {
		return sonsumes;
	}

	public void setSonsumes(Set<NoticeConsumeBean> sonsumes) {
		this.sonsumes = sonsumes;
	}
}
