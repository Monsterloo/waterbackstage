package com.hhh.fund.waterwx.dao;



import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjUser;
import com.hhh.fund.waterwx.entity.SwjWxsignin;
@Repository
public interface SwjSignInDao extends SpecificationsRepository<SwjWxsignin, String> {
	
	/**
	 * 查询用户信息
	 * */
	@Query("SELECT u FROM  SwjUser u where userid=:userid")
	public List<SwjUser> getUserInfo(@Param("userid") String userid);
	

}
