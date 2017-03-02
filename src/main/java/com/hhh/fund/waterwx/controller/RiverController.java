package com.hhh.fund.waterwx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.web.model.DataTablesResult;

@Controller
@RequestMapping("/river")
public class RiverController {
	private final static String PAGE_SIZE="7";
	@Autowired
	private RiverService riverService;
	
	@Autowired
	private ResponsibilityService responseService;
	
	@RequestMapping(value="/main")
	public String type(){
		return "waterwx/riverList";
	}
	
	/**
	 * 查询字典
	 * */
	@RequestMapping(value="/findDict")
	public @ResponseBody List<SysUcenterDict> findDict(String dict){
		return responseService.findDict(dict);
	}
	
	
	
	/**
	 * 查询所有的河道
	 * */
	@RequestMapping(value="/searchAllRiver")
	public @ResponseBody DataTablesResult<RiverBean> searchAlltype(String river,String area,String pollrivers,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize){
		int page = riverService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<RiverBean> records = riverService.findRiverAll(pr,river,area,pollrivers);
		
		DataTablesResult<RiverBean> dtr = new DataTablesResult<RiverBean>();
		dtr.setData(records.getContent());
//		dtr.setDraw(draw);
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}
	/**
	 * 保存河道
	 * */
	@RequestMapping(value="/saveRiver")
	public @ResponseBody String saveRiver(RiverBean bean){
		riverService.save(bean);
		return "succ";
	}
	/**
	 * 删除河道
	 * */
	@RequestMapping(value="/deleteRiver")
	public @ResponseBody String deleteRiver(String id){
		riverService.delete(id);
		return "succ";
	}
	/**
	 * 修改河道，返回数据
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody RiverBean findById(String id){
		RiverBean bean = riverService.findById(id);
		List<SysUcenterDict> pollriversList = this.findDict("pollrivers");
		for(SysUcenterDict dict:pollriversList){
			if(dict.getCode().equals(bean.getBelongPollRiver())){
				bean.setBelongPollRiverStr(dict.getName());
			}
		}
		return bean;
	}
}
