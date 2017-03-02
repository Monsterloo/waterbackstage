package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjFeedbackDao;
import com.hhh.fund.waterwx.entity.SwjFeedback;
import com.hhh.fund.waterwx.service.SwjFeedbackService;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjFeedbackBean;
@Component
@Transactional
public class SwjFeedbackServiceImpl implements SwjFeedbackService {
	@Autowired
	private SwjFeedbackDao fbDao;
	/**
	 * 保存反馈信息
	 * */
	public void save(SwjFeedbackBean bean){
			SwjFeedback type = new SwjFeedback();
			BeanUtils.copyProperties(bean, type);
			fbDao.save(type);
	}
	/**
	 * 删除本人反馈信息
	 * */
	public void delete(String id){
		fbDao.delete(id);
	}
	/**
	 * 查找所有反馈信息
	 * */
	public SmsPage<SwjFeedbackBean> findListFeedback(String questionId,Pageable page) {
		Page<SwjFeedback> alist = null;
		alist = fbDao.findByQuestionIdOrderByFeedbackdateDesc(questionId,page);
		List<SwjFeedbackBean> accounts = new ArrayList<SwjFeedbackBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(SwjFeedback a : alist.getContent()){
				SwjFeedbackBean bean = new SwjFeedbackBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return null;
		}
		return new SmsPage<SwjFeedbackBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize){
		return (int)Math.floor((double)start/pageSize);
	}
	/**
	 * 根据id查找唯一反馈信息
	 * @param id 反馈编号
	 * */
	public SwjFeedbackBean findById(String id){
		SwjFeedback feed = fbDao.findOne(id);
		SwjFeedbackBean bean = new SwjFeedbackBean();
		BeanUtils.copyProperties(feed, bean);
		return bean;
	}
	/**
	 * 查找所有反馈信息
	 * */
	public List<SwjFeedbackBean> findAllFeedbackByWeixin(String questionId) {
		List<SwjFeedback> alist = null;
		alist = fbDao.findByQuestionIdOrderByFeedbackdateDesc(questionId);
		List<SwjFeedbackBean> accounts = new ArrayList<SwjFeedbackBean>();
		
		if(alist.size()>0){
			for(SwjFeedback a : alist){
				SwjFeedbackBean bean = new SwjFeedbackBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}
		return accounts;
	}
}
