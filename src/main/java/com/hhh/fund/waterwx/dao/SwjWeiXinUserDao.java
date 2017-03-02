package com.hhh.fund.waterwx.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;

public interface SwjWeiXinUserDao  extends SpecificationsRepository<SwjWeiXinUser, String> {
	
	SwjWeiXinUser findByOpenId(String openId);
	
	@Modifying
	@Query("update SwjWeiXinUser set isNotRemind=-1 where openId=:openId")
	void updateIsNotRemindByUserId(@Param("openId") String openId);
	
	@Modifying
	@Query("update SwjWeiXinUser set phoneNum=:phoneNum where openId=:openId")
	void updatePhoneNumByOpenId(@Param("phoneNum") String phoneNum, @Param("openId") String openId);
	
	SwjWeiXinUser findByPhoneNum(String phoneNum);
	
	@Modifying
	@Query("update SwjWeiXinUser set isJoinActivity=:isJoinActivity where openId=:openId")
	void updateIsJoinActByOpenId(@Param("isJoinActivity") String isJoinActivity, @Param("openId") String openId);
	
	@Modifying
	@Query("update SwjWeiXinUser set score=score+1 where openId=:openId")
	void addScore(@Param("openId")String openId);
	
}
