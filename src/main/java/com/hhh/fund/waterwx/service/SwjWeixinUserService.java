package com.hhh.fund.waterwx.service;

import java.util.List;

import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.webmodel.SwjWeiXinUserBean;

public interface SwjWeixinUserService {

	SwjWeiXinUser getUserByOpenId(String openId);

	SwjWeiXinUser getUserById(String userId);

	void updateIsNotRemindByOpenId(String userId);

	void updatePhoneNumByOpenId(String phoneNum, String openId);

	SwjWeiXinUser findByPhoneNum(String phoneNum);

	void updateIsJoinActByOpenId(String isJoinActivity, String openId);
	
	/**
	 * 保存方法
	 * */
	public void save(SwjWeiXinUserBean weixinUser);
	
	/**
	 * 保存方法
	 * */
	public void saveBean(SwjWeiXinUser bean);
	/**
	 * 根据开始结束时间获取河道投诉列表
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 */
//	public ComplainListJsonBean findQuestionByTime(String userId, String startDateTime, String endDateTime,
//			String districtId, String type, String status);

	SwjWeiXinUser findByOpenId(String openId);

	void saveAll(List<SwjWeiXinUserBean> users);

	void addScore(String openId);
}
