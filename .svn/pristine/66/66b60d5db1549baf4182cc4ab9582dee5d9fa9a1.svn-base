package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SysUcenterDictDao extends SpecificationsRepository<SysUcenterDict, String> {
	/**
	 * 查询行政区域
	 * */
	public List<SysUcenterDict> findByParent(String parent);

	public SysUcenterDict findByName(String name);

	@Query("SELECT s FROM SysUcenterDict s where parent=:parent and name=:name")
	public List<SysUcenterDict> findPhoneNumByArea(@Param("parent") String parent,@Param("name") String name);
}
