package com.hhh.fund.waterwx.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityStatisticsBean;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
public interface ResponsibilityService {
	
	public void save(ResponsibilityBean bean);
	
	/**
	 * 保存问题类型
	 * */
	public void save(ResponsibilityBean bean,RiverBean riverBean);
	/**
	 * 删除问题类型
	 * */
	public void delete(String id);
	/**
	 * 查找所有问题
	 * */
	public SmsPage<ResponsibilityBean> findTypeAll(Pageable page);
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 查询河道
	 * @param id 河道id
	 * */
	public ResponsibilityBean findById(String id);
	/**
	 * 查找字典
	 * */
	public List<SysUcenterDict> findDict(String dict);
	
	/**
	 * 按地区查找相关信息
	 * 
	 * */
	public List<Responsibility> getReDataByArea(String area);
	
	/**
	 * 按地区查找相关信息
	 * @param area 
	 * 
	 * */
	public List<Responsibility> getReByRiverName(String riverName, String area);
	/**
	 * 根据河道名称 查询河长制
	 * @param rivers 河道名
	 * */
	public SmsPage<ResponsibilityBean> findByRiverNames(String rivers,Pageable page);
	
	/**
	 * 根据河涌名河道名称左右岸 查询河长
	 * @param area 
	 * 
	 * */
	public String findByParameter(String riverCode, String reachName,String riverBankName);
	
	public List<Responsibility> findByRiverCode(String riverCode);
	
	public List<Responsibility> findRiverByRiverCode(String riverCode);
	
	public List<Responsibility> findByRiverId(String riverId);
	
	/**
	 * 查询行政区域对应的电话
	 * */
	public String findPhoneNumByArea(String parent,String name);
	
	public boolean saveList(List beanList);
	
	public ResponsibilityStatisticsBean findStatisticsByArea(String areaName);
}
