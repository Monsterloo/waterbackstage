package com.hhh.fund.waterwx.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
public interface RiverService {
	/**
	 * 保存河道
	 * */
	public void save(RiverBean bean);
	/**
	 * 删除河道
	 * */
	public void delete(String id);
	/**
	 * 查找所有河道
	 * */
	public SmsPage<RiverBean> findRiverAll(Pageable page,String river,String area,String pollrivers);
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 查询河道
	 * @param id 河道id
	 * */
	public RiverBean findById(String id);
	/**
	 * 按地区统计河道数量
	 * 
	 * */
	public List<River> getRiverGroupByArea();
	/**
	 * 按河道名称查找河道等级
	 * 
	 * */
	public List<River> getGradeByRiverName(String reachname);
	/**
	 * 根据河道名 查河道
	 * @param rivername
	 * */
	public List<River> findByRiverName(String rivername);
	/**
	 * 根据查询条件查询河道
	 * @param area 行政区域  grade 河道等级  river 河道名称
	 * 
	 * */
	public List<RiverBean> getRiverBySearch(final String area,final String grade,final String river);
	/**
	 * 根据查询条件查询河道
	 * @param rivercode 河道编号
	 * 
	 * */
	public River findByRiverCode(final String riverCode);
	/**
	 * 获取所有的字符，不分页
	 * @return
	 */
	public List<River> findAllRiver();
	
	/**
	 * 根据地区名称查询河涌
	 * @return
	 */
	public List<River> getRiverListByAreaName(String area);
	
	/**
	 * 根据查询条件查询河道
	 * @param rivercode 河道编号
	 * 
	 * */
	public List<River> findByListRiverCode(String riverCode);
	
	/**
	 * 根据河涌名模糊查询
	 * @param riverName
	 * 
	 * */
	public List<River> getRiverByAreaAndRiver(String areaName,String riverName);
	
}
