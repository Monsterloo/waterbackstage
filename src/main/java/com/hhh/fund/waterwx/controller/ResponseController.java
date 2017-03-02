package com.hhh.fund.waterwx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityStatisticsBean;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.web.model.DataTablesResult;

@Controller
@RequestMapping("/response")
public class ResponseController {
	private final static String PAGE_SIZE="7";
	@Autowired
	private ResponsibilityService responseService;
	@Autowired
	private RiverService riverService;
	
	@RequestMapping(value="/main")
	public String type(){
		return "waterwx/responseList";
	}
	
	@RequestMapping("/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response){
		try {
		    
			ServletContext sc = request.getServletContext();
			String realPath = sc.getRealPath("/template")+File.separator+"reposibility_new.xls";
			System.out.println(realPath);
			InputStream in = new FileInputStream(realPath);
			
			response.addHeader("Content-Disposition", "attachment;filename=" + new String("reposibility_new.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Length", "" + in.available());
			OutputStream os = response.getOutputStream();
			byte[]buff = new byte[1024];
			int i = 0;
			while((i=in.read(buff))!=-1){
				os.write(buff);// 输出文件
				os.flush();
			}
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新版河长制统计
	 * @return
	 */
	@RequestMapping(value="/toStatistics")
	public String toStatistics(HttpServletRequest request){
		return "waterwx/responseList_1Statistics";
	}
	
	@RequestMapping(value="/getStatData")
	public @ResponseBody Map<String,List<String>> getStatData(HttpServletRequest request,String area){
		ResponsibilityStatisticsBean bean = responseService.findStatisticsByArea(area);
		Map<String, List<String>> map = bean.getTotalMap();
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
	 * 查询所有的河道
	 * */	
	@RequestMapping(value="/searchAllResponse")
	public @ResponseBody DataTablesResult<ResponsibilityBean> searchAllResponse(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize,String area,String grade,String river){
		int page = responseService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		int t= pr.getPageNumber();
		System.out.println(t);
		SmsPage<ResponsibilityBean> records = null;
		
		if(area == null || area.equals("")){
			records = responseService.findTypeAll(pr);
		}else{
			List<RiverBean> riverBeans = riverService.getRiverBySearch(area,grade,river);
			String rivers = "";
			if(riverBeans != null){
				for(RiverBean r:riverBeans){
					String rivername = r.getRiverName();
					rivers +=rivername+",";
				}
			}
			System.out.println(rivers);
			records = responseService.findByRiverNames(rivers,pr);
		}
		Integer count = null;
		DataTablesResult<ResponsibilityBean> dtr = new DataTablesResult<ResponsibilityBean>();
		dtr.setData(records.getContent());
		dtr.setDraw(draw);
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}
	/**
	 * 保存河道
	 * */
	@RequestMapping(value="/saveResponse")
	public @ResponseBody String saveResponse(ResponsibilityBean bean,RiverBean riverBean,String respid,String riverid){
		System.out.println(bean.getRiverCode()+"response");
		bean.setId(respid);
		riverBean.setId(riverid);
		System.out.println(riverBean.getRiverCode()+"river");
		responseService.save(bean,riverBean);
		//riverService.save(riverBean);
		return "succ";
	}
	/**
	 * 删除河道
	 * */
	@RequestMapping(value="/deleteResponse")
	public @ResponseBody String deleteRiver(String id){
		responseService.delete(id);
		return "succ";
	}
	/**
	 * 修改河道，返回数据
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody Map findById(String id){
		ResponsibilityBean bean = responseService.findById(id);
		String rivername = bean.getRiverName();
		List<River> list = riverService.findByRiverName(rivername);
		Map map = new HashMap();
		River river = new River();
		if(list != null){
			river=list.get(0);
		}
		map.put("river", river);
		map.put("resp", bean);
		return map;
	}
	/**
	 * 查询字典
	 * */
	@RequestMapping(value="/findDict")
	public @ResponseBody List<SysUcenterDict> findDict(String dict){
		System.out.println(dict+"controller123");
		return responseService.findDict(dict);
	}
}
