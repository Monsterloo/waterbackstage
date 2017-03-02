package com.hhh.fund.waterwx.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SwjUser;
import com.hhh.fund.waterwx.entity.SysUcenterRole;
import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SwjUserDao extends SpecificationsRepository<SwjUser, String> {
	public SwjUser findByUserid(String userid);
	public SwjUser findByName(String username);
	public SwjUser findById(String id);
	
	//public Account findByUsernameAndPhoneAndLoginnameAndArea(String username,String phone,String loginname,String area);
	
	@Query("SELECT count(u.id) as cou from SwjUser u,SysUcenterAccount s where u.name=s.name and u.area=:area")
	public List<Long> getUserCountByArea(@Param("area")String area);

	@Query("select u from Account u where u.id = :id")
	public Account findAccountById(@Param("id")String id);
	
	@Query("select u from Account u where   u.phone=:phone")
	public Account findAccountName(@Param("phone")String phone);
}
