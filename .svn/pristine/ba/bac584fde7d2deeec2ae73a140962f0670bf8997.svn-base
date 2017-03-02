package com.hhh.fund.waterwx.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.entity.SwjWxfollowuserinfo;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;



@Repository
public interface SwjWxexportUserDao extends SpecificationsRepository<SwjWxexportUser, String> {

	
	/**
	 * 导出数据查询
	 * */
	@Query("SELECT s FROM SwjWxexportUser s")
	public List<SwjWxexportUser> getAllToExport();
	
	
}
