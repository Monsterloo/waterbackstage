package com.hhh.fund.waterwx.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hhh.fund.waterwx.dao.SwjAnswerDao;
import com.hhh.fund.waterwx.dao.SwjQuestionDao;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.service.SwjAnswerService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
@Component
@Transactional
public class SwjAnswerServiceImpl implements SwjAnswerService {
		@Autowired
		private SwjAnswerDao answerDao;
		
		public SmsPage<SwjAnswerBean> findAnswerByQid(String qId,Pageable page) {
			Page<SwjAnswer> alist = null;
			alist = answerDao.findByQuestionId(qId, page);
			List<SwjAnswerBean> accounts = new ArrayList();
			if(null != alist.getContent() && !alist.getContent().isEmpty()){
				for(SwjAnswer a : alist.getContent()){
					SwjAnswerBean bean = new SwjAnswerBean();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return null;
			}
			return new SmsPage<SwjAnswerBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}
		
		/**
		 * 获取页码,从0页开始
		 * @return
		 */
		public int getPage(int start,int pageSize){
			return (int)Math.floor((double)start/pageSize);
		}
		/**
		 * 删除回复
		 * */
		public void deleteAnswer(String id){
			answerDao.delete(id);
		}
		/**
		 * 保存回复
		 * */
		public String saveAnswer(SwjAnswerBean bean){
			SwjAnswer answer = new SwjAnswer();
			String date = bean.getAnswerDate();
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date answerDate = null;
			try {
				answerDate = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answer.setAnswerDate(answerDate);
			String username = bean.getUsername();
			System.out.println(username+"4444");
			BeanUtils.copyProperties(bean, answer);
			SwjAnswer ans = answerDao.save(answer);
			return ans.getId();
		}
		/**
		 * 根据问题查找回复
		 * @param questionId 问题id
		 * */
		public List<SwjAnswer> findLastAnswerByQuestionId(String questionId) {
			List<SwjAnswer> page=answerDao.findLastAnswerByQuestionId(questionId);
			return page;
		}
		/**
		 * 根据回复id查回复
		 * @param id 回复id
		 * */
		public SwjAnswerBean findById(String id) {
			SwjAnswer answer = answerDao.findOne(id);
			SwjAnswerBean bean = new SwjAnswerBean();
			BeanUtils.copyProperties(answer, bean);
			return bean;
		}
		/**
		 * 根据投诉id查回复
		 * @param qId 问题id
		 * */
		public List<SwjAnswerBean> findAllAnswer(String qId) {
			List<SwjAnswer> list = answerDao.findByQuestionIdOrderByAnswerDate(qId);
			List<SwjAnswerBean> beanList = new ArrayList<SwjAnswerBean>();
			if(list.size()<1){
				return beanList;
			}
			for(SwjAnswer sa: list){
				SwjAnswerBean bean = new SwjAnswerBean();
				BeanUtils.copyProperties(sa, bean);
				beanList.add(bean);
			}
			return beanList;
		}
}
