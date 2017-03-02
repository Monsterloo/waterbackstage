package com.hhh.fund.waterwx.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjAnswerBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;



public interface SwjAnswerService {
	public SmsPage<SwjAnswerBean> findAnswerByQid(String qId,Pageable page);
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 删除回复
	 * */
	public void deleteAnswer(String id);
	/**
	 * 保存回复
	 * */
	public String saveAnswer(SwjAnswerBean bean);
	
	
	public List<SwjAnswer> findLastAnswerByQuestionId(String questionId);
	
	/**
	 * 根据回复id查回复
	 * @param id 回复id
	 * */
	public SwjAnswerBean findById(String id);
	/**
	 * 根据投诉id查回复
	 * @param qId 问题id
	 * */
	public List<SwjAnswerBean> findAllAnswer(String qId);
}
