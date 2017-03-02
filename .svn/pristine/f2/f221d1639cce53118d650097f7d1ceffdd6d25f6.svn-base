package com.hhh.fund.waterwx.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjWxquestionBean;
public interface SwjWxQuestionService  {
	/**
	 * 保存问题类型
	 * */
	public void save(SwjWxquestionBean bean);
	/**
	 * 删除问题类型
	 * */
	public void delete(String id);
	/**
	 * 查找所有问题
	 * */
	public SmsPage<SwjWxquestionBean> findTypeAll(Pageable page);
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 查询河道
	 * @param id 河道id
	 * */
	public SwjWxquestionBean findById(String id);
	/**
	 * 查询所有无效投诉
	 * 
	 * */
	public List<SwjWxquestionBean> getAllWxByWeixin();
}
