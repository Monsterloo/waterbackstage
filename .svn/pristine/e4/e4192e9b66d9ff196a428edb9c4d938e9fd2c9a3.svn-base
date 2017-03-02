package com.hhh.fund.waterwx.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;

public interface SwjQuestiontypeService {
	/**
	 * 保存问题类型
	 * */
	public void save(SwjQuestiontypeBean bean);
	/**
	 * 删除问题类型
	 * */
	public void delete(String id);
	/**
	 * 查找所有问题
	 * */
	public SmsPage<SwjQuestiontypeBean> findTypeAll(Pageable page);
	/**
	 * 查找所有问题类型，不分页
	 * @return
	 */
	public List<SwjQuestiontype> findTypeAll();
}
