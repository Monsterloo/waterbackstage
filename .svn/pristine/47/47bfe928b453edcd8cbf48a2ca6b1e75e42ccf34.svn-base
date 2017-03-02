package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface ResponsibilityDao extends SpecificationsRepository<Responsibility, String> {
	public List<Responsibility> findByRiverCode(String rivercode);
	public List<Responsibility> findByRiverName(String rivername);
	public List<Responsibility> findByRiverNameAndLeftRight(String rivername,String lr);
	public List<Responsibility> findByRiverCodeAndPartName(String riverCode,String partName);
	@Query("select re from Responsibility re,River r where r.riverName = re.riverName and r.area=:area and re.riverName like %:rivername%")
	public List<Responsibility> getByRivernameAndArea(@Param("area") String area,@Param("rivername") String rivername);
	@Query("select re from Responsibility re,River r where r.riverName = re.riverName and r.area=:area and re.riverName=:rivername and re.leftRight = :leftRight")
	public List<Responsibility> getByRivernameAndAreaAndLf(@Param("area") String area,@Param("rivername") String rivername,@Param("leftRight") String leftRight);
	@Query("select re from Responsibility re,River r where r.riverName = re.riverName and r.area=:area and re.riverName=:rivername and re.leftRight = :leftRight and re.partName=:reach")
	public List<Responsibility> getByRivernameAndAreaAndLfAndReach(@Param("area") String area,@Param("rivername") String rivername,@Param("leftRight") String leftRight,@Param("reach") String reach);
	
	/**
	 * 根据地区 选择区长
	 * */
	@Query("select distinct re.distMgrName from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area")
	public List<Responsibility> getAreaNameByArea(@Param("area") String area);
	
	/**
	 * 根据地区 选择相关信息
	 * */
	@Query("select re from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area order by re.riverName")
	public List<Responsibility> getReDataByArea(@Param("area") String area);
	
	/**
	 * 根据地区和河道,左右岸 选择街长
	 * */
	@Query("select distinct re.streetMgrName from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area and re.riverName=:rivername ")
	public List<Responsibility> getStreetNameByAreaAndRiverName(@Param("area") String area,@Param("rivername") String rivername);
	/**
	 * 根据地区和河道,左右岸 选择街长
	 * */
	@Query("select distinct re.villageMgrName from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area and re.riverName=:rivername and re.leftRight=:lr")
	public List<Responsibility> getVillageName(@Param("area") String area,@Param("rivername") String rivername,@Param("lr") String lr);
	/**
	 * 根据地区和河道,河段 选择街长
	 * */
	@Query("select distinct re.villageMgrName from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area and re.riverName=:rivername and re.partName=:reach")
	public List<Responsibility> getVillageNameByReach(@Param("area") String area,@Param("rivername") String rivername,@Param("reach") String reach);
	
	/**
	 * 根据地区和河道,河段 选择街长
	 * */
	@Query("select distinct re.villageMgrName from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area and re.riverName=:rivername")
	public List<Responsibility> getVillageNameByAreaAndRiver(@Param("area") String area,@Param("rivername") String rivername);
	/**
	 * 根据和河段名和地区查询相关信息
	 * @param area 
	 * */
	@Query("select re from Responsibility re,River ri where re.riverCode = ri.riverCode and ri.area=:area and re.riverName like %:rivername% order by re.riverName")
	public List<Responsibility> getReByRiverName(@Param("rivername") String rivername,@Param("area") String area);
	
	/**
	 * 根据地区和河道,河段 左右岸 选择街长
	 * */
	@Query("select distinct re.villageMgrName from Responsibility re,River ri where re.riverCode = ri.riverCode and re.riverCode=:riverCode and re.partName=:partName and re.leftRight=:leftRight")
	public String findByParameter(@Param("riverCode") String riverCode,@Param("partName") String partName,@Param("leftRight") String leftRight);
	
	public List<Responsibility> findByDistMgrName(String areaName);
	
	public List<Responsibility> findByStreetMgrName(String streetName);
	
	public List<Responsibility> findByVillageMgrName(String villageName);
	
	@Query("select distinct re.riverCode,re.leftRight from Responsibility re where re.partName=:partName")
	public List<Responsibility> findByRiverBank(@Param("partName")String partName);
	
	@Query("select distinct re.riverName,re.partName from Responsibility re,River ri where re.riverCode = ri.riverCode and re.riverCode=:riverCode")
	public List<Responsibility> findRiverByRiverCode(@Param("riverCode")String riverCode);
	
}
