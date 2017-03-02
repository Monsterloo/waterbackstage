package com.hhh.fund.waterwx.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.service.PublicSignsBoardInfoService;
import com.hhh.fund.waterwx.service.SwjPublicSignBoardInfoService;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardStatisticsBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityStatisticsBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.web.model.DataTablesResult;


@Controller
@RequestMapping("/publicSignBoardInfo")
public class SwjPublicSignBoardInfoListController {
	
	@Autowired
	private SwjPublicSignBoardInfoService boardInfoService;
	
	@Autowired
	private PublicSignsBoardInfoService publicSignsBoardInfoService;
	
	/**
	 * 查询所有的河道
	 * */
	@RequestMapping(value="/searchAll")
	public @ResponseBody DataTablesResult<PublicSignsBoardInfoBean> searchAllResponse(@RequestParam(value = "start",defaultValue="0") int start,
			@RequestParam(value = "length",defaultValue="10") int pageSize,
			PublicSignsBoardInfoBean bean){
		int page = getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		
		SmsPage<PublicSignsBoardInfoBean> records = boardInfoService.findTypeAll(pr,bean);
		DataTablesResult<PublicSignsBoardInfoBean> dtr = new DataTablesResult<PublicSignsBoardInfoBean>();
		dtr.setData(records.getContent());
//		dtr.setDraw(draw);
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}
	
	
	/**
	 * 修改河道，返回数据
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody PublicSignsBoardInfoBean findById(String id){
		PublicSignsBoardInfoBean bean = boardInfoService.findById(id);
		return bean;
	}
	
	
	@RequestMapping(value="/getStatData")
	public @ResponseBody Map<String,List<String>> getStatData(HttpServletRequest request,String area){
		PublicSignsBoardStatisticsBean bean = publicSignsBoardInfoService.findStatisticsByArea(area);
		Map<String, List<String>> map = bean.getStatMap();
		for(String str : map.keySet()){
			List<String> list = map.get(str);
			System.out.print(str+" .....");
			for(String item : list){
				System.out.print(item+"  ");
			}
			System.out.println();
		} 
		return map;
	}

	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	private int getPage(int start, int pageSize) {
		return (int)Math.floor((double)start/pageSize);
	}
	
}
