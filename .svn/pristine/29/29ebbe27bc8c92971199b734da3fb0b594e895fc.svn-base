package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.PublicSignsBoardInfo;
@Repository
public interface PublicSignsBoardInfoDao  extends SpecificationsRepository<PublicSignsBoardInfo, String>  {
	
	public List<PublicSignsBoardInfo> findByAreaName(String areaName);
	
	@Query("SELECT r FROM PublicSignsBoardInfo r ")
	public List<PublicSignsBoardInfo> findAll();
}
