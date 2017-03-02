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
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SwjUcenterRoleDao extends SpecificationsRepository<SysUcenterRole, String> {
	
	@Query("SELECT c from SysUcenterRole c where c.id=(SELECT a.roleid FROM SysUcenterUserrole a where a.accountid=:userid)")
	public SysUcenterRole findRoleByUserId(@Param("userid") String userId);
	
	public SysUcenterRole findByName(String roleName);
}
