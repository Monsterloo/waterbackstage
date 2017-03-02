package com.hhh.fund.waterwx.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hhh.fund.waterwx.entity.PublicSignsBoardInfo;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardStatisticsBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

public interface PublicSignsBoardInfoService {

	public void save(PublicSignsBoardInfoBean bean);
	
	public List<PublicSignsBoardInfoBean> findByAreaName(String areaName);

	public int getPage(int start, int pageSize);

//	public SmsPage<PublicSignsBoardInfoBean> findByRiverNames(PageRequest pr);
	
	public PublicSignsBoardStatisticsBean findStatisticsByArea(String areaName);
}
