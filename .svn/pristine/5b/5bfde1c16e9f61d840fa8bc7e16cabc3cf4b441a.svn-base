package com.hhh.fund.waterwx.service;

import java.util.List;
import java.util.Map;

import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.entity.SwjWxfollowuserinfo;
import com.hhh.fund.waterwx.entity.SwjWxquestion;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxfollowuserinfoBean;





public interface SwjWxfollowUserInfoService{

	/**
	 * 保存方法
	 * */
	public void save(SwjWxfollowuserinfoBean bean);
	
	/**
	 * 查找之前清空表中数据
	 * */
	public void deleteFollowUserTable();
	
	/**
	 * 对比投诉表找出相同用户
	 * */
	public List<SwjWxfollowuserinfo> findGiftingMoneyUser();
	
	public List<SwjQuestion> findGiftingMoneyUserQuestionId();
	
	/**
	 * 保存导出表的用户信息
	 * */
	public void saveExportUser(SwjWxexportUserBean exportUser);
	
	
	
	
}
