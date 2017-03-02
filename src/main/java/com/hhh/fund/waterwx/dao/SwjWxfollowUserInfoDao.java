package com.hhh.fund.waterwx.dao;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.entity.SwjWxfollowuserinfo;
import com.hhh.fund.waterwx.entity.SwjWxquestion;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;



@Repository
public interface SwjWxfollowUserInfoDao extends SpecificationsRepository<SwjWxfollowuserinfo, String> {
	/**
	 * 查询与投诉表相同的用户
	 * */
	@Query("SELECT f FROM SwjWxfollowuserinfo f , SwjQuestion q where q.type IS NULL and q.status in ('0','2','5') and q.nickname=f.nickname order by q.id")
	public List<SwjWxfollowuserinfo> findGiftingMoneyUser();
	
	@Query("SELECT q FROM SwjWxfollowuserinfo f , SwjQuestion q where q.type IS NULL and q.status in ('0','2','5') and q.nickname=f.nickname order by q.id")
	public List<SwjQuestion> findGiftingMoneyUserQuestionId();
	
}
