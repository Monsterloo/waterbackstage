package com.hhh.fund.waterwx.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjWeiXinUserDao;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.service.SwjWeixinUserService;
import com.hhh.fund.waterwx.util.StringUtil;
import com.hhh.fund.waterwx.webmodel.SwjWeiXinUserBean;

@Component
@Transactional
public class SwjWeixinUserServiceImpl implements SwjWeixinUserService{

	@Autowired
	private SwjWeiXinUserDao swjWeiXinUserDao;
	/**
	 * 保存方法
	 * */
	@Override
	public void save(SwjWeiXinUserBean bean) {
		SwjWeiXinUser user=new SwjWeiXinUser();
		BeanUtils.copyProperties(bean, user);
		swjWeiXinUserDao.save(user);
	}
	
	/**
	 * 保存方法
	 * */
	@Override
	public void saveBean(SwjWeiXinUser bean) {
		swjWeiXinUserDao.save(bean);
	}
	
	
	@Override
	public SwjWeiXinUser getUserByOpenId(String openId) {
		
		SwjWeiXinUser user  = null;
		
		if(!StringUtil.isBlank(openId)){
			user = swjWeiXinUserDao.findByOpenId(openId);
		}
		
		return user;
	}

	@Override
	public SwjWeiXinUser getUserById(String userId) {
		return swjWeiXinUserDao.findOne(userId);
	}

	@Override
	public void updateIsNotRemindByOpenId(String userId) {
		swjWeiXinUserDao.updateIsNotRemindByUserId(userId);
	}

	@Override
	public void updatePhoneNumByOpenId(String phoneNum, String openId) {
		swjWeiXinUserDao.updatePhoneNumByOpenId(phoneNum,openId);
	}

	@Override
	public SwjWeiXinUser findByPhoneNum(String phoneNum) {
		return swjWeiXinUserDao.findByPhoneNum(phoneNum);
	}

	@Override
	public void updateIsJoinActByOpenId(String isJoinActivity, String openId) {
		swjWeiXinUserDao.updateIsJoinActByOpenId(isJoinActivity,openId);
	}

	@Override
	public SwjWeiXinUser findByOpenId(String openId) {
		return swjWeiXinUserDao.findByOpenId(openId);
	}

	@Override
	public void saveAll(List<SwjWeiXinUserBean> users) {
		for(SwjWeiXinUserBean bean:users){
			save(bean);
		}
	}

	@Override
	public void addScore(String openId) {
		swjWeiXinUserDao.addScore(openId);
	}

}
