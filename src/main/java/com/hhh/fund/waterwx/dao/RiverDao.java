package com.hhh.fund.waterwx.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface RiverDao extends SpecificationsRepository<River, String> {
	
	@Query(" select t from River t where t.riverCode=:riverCode")
	public River findByRiverCode(@Param("riverCode")String riverCode);
	
	public List<River> findByRiverName(String riverName);
	//@Query("SELECT t.area, count(s.id) as cou FROM  River t , SwjQuestion s where s.riverCode = t.riverCode and s.complainDate between :startdate and :enddate and s.status not in('-1','1') and s.area<>'' and status<>'' GROUP BY t.area")
	@Query("SELECT s.area, count(s.id) as cou FROM SwjQuestion s where s.complainDate between :startdate and :enddate and s.status not in('-1','1') and s.area<>'' and s.status<>'' and s.city='广州市' GROUP BY s.area")
	public List<River> getTj(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	
	/**
	 * 查询所有地区
	 * */
	@Query(" select distinct substring(area,1,3) as area from River where area != '' ")
	public List<River> getAllArea();
	/**
	 * 根据地区查询所有河段
	 * */
	public List<River> findByArea(String area);
	/**
	 * 根据地区查询所有河段
	 * */
	@Query("select r from River r where area=:area and riverName like %:rivername% ")
	public List<River> getRiverByAreaAndRiver(@Param("area") String area,@Param("rivername") String rivername);
	
	/**
	 * 根据地区查询河涌列表
	 * */
	@Query("select r.riverCode,r.riverName from River r where r.area=:area")
	public List<River> getRiverListByAreaName(@Param("area") String area);
	
	@Query("select r from River r where riverName like %:rivername% ")
	public List<River> getRiverByRiver(@Param("rivername") String rivername);
	/**
	 * 根据地区统计所有河道
	 * */
	@Query("select area,count(id) as cou from River where area !='' group by area")
	public List<River> getRiverGroupByArea();

	/**
	 * 根据河道名称查询河道
	 * */
	@Query("select r from River r where riverName=:rivername")
	public List<River> findByReachname(@Param("rivername") String rivername);

	@Query("select r from River r")
	public List<River> findAllRivers();

	@Query("select riverCode,riverName,area,start from River ")
	public List<Object[]> findAllRiverParams();
	
	@Query("select r from River r where r.riverCode=:riverCode")
	public List<River> findByListRiverCode(@Param("riverCode")String riverCode);
	
	//public Page<River> findDistinctRiverCodeByArea(String area,Pageable page);

	public List<River> findIdByRiverName(String riverName);
}
