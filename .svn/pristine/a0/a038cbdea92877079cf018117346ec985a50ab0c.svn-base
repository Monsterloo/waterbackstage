package com.hhh.fund.waterwx.service;



import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjFeedbackBean;
public interface SwjFeedbackService {
	/**
	 * 保存反馈信息
	 * */
	public void save(SwjFeedbackBean bean);
	/**
	 * 删除本人反馈信息
	 * */
	public void delete(String id);
	/**
	 * 查找所有反馈信息
	 * */
	public SmsPage<SwjFeedbackBean> findListFeedback(String questionId,Pageable page);
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 根据id查找唯一反馈信息
	 * @param id 反馈编号
	 * */
	public SwjFeedbackBean findById(String id);
	/**
	 * 查找所有反馈信息
	 * */
	public List<SwjFeedbackBean> findAllFeedbackByWeixin(String questionId);
}
