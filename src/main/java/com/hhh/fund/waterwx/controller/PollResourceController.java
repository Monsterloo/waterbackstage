package com.hhh.fund.waterwx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.SwjPollutantSourceService;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;
import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.web.model.DataTablesResult;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/pollResource")
public class PollResourceController {

	@Autowired
	private SwjPollutantSourceService pSourceService;
	
	@Autowired
	private ResponsibilityService responseService;
	
	private String[]areas = new String[]{"天河区","番禺区","白云区","花都区","荔湾区","越秀区","黄埔区","南沙区","增城区","海珠区"};
	
	private String [] belongPollRivers = new String[]{"0","1","2","3"};
	
	private Map<String,String>dictMap = new HashMap<String,String>();
	
	
	@RequestMapping("/toPollResourceList")
	public String toPollResourceList(){
		return "waterwx/poll_resource_list";
	}
	
	@RequestMapping("/toPollResourceStatistics")
	public String toPollResourceStatistics(){
		return "waterwx/pollResourceStatistics";
	}
	
	@RequestMapping("/toAreaPollResourceStatistics")
	public String toAreaPollResourceStatistics(String area_,Model model){
		model.addAttribute("area_remark",area_);
		
		return "waterwx/areaPollResourceStatistics";
	}
	
	
	@RequestMapping("/area_statistics")
	public void area_statistics(HttpServletRequest request,HttpServletResponse response,String area_){
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer =  response.getWriter();
			
			List returnList = new ArrayList();
			
			String sql = getAreaStatisticsSql(area_,1+"");
			
			List<Object[]>objList = pSourceService.getAreaStatisticsList(sql);
			
			if(objList!=null&&objList.size()!=0){
				List<List>dataList = changeForm(objList);
				//序列
				addIndex(dataList,0);
				//小计
				addSubTotal(dataList,2,7,13);
				//污染源合计
				addPollResourceTotal(dataList,2);
					
				returnList.add(dataList);
			}else{
				returnList.add(new ArrayList<Object[]>());
			}
			
			
			sql = getAreaStatisticsSql(area_,2+"");
			
			//海珠区  35条黑臭河
			List<Object[]>objList2 = pSourceService.getAreaStatisticsList(sql);
			
			if(objList2!=null&&objList2.size()!=0){
				List<List>dataList2 = changeForm(objList2);
				//序列
				addIndex(dataList2,0);
				//小计
				addSubTotal(dataList2,2,7,13);
				//污染源合计
				addPollResourceTotal(dataList2,2);
					
				returnList.add(dataList2);
			}else{
				returnList.add(new ArrayList<Object[]>());
			}
			
			
			
			sql = getAreaStatisticsSql(area_,3+"");
			//海珠区  35条黑臭河
			List<Object[]>objList3 = pSourceService.getAreaStatisticsList(sql);
			
			if(objList3!=null&&objList3.size()!=0){
				List<List>dataList3 = changeForm(objList3);
				//序列
				addIndex(dataList3,0);
				//小计
				addSubTotal(dataList3,2,7,13);
				//污染源合计
				addPollResourceTotal(dataList3,2);
					
				returnList.add(dataList3);
			}else{
				returnList.add(new ArrayList<Object[]>());
			}
			
			writer.println(JSONArray.fromObject(returnList));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private List<List> changeForm(List<Object[]> objList) {
		List<List> returnList = new ArrayList<List>(); 
		for(Object[] objs:objList){
			returnList.add(new ArrayList(Arrays.asList(objs)));
		}
		return returnList;
	}

	/**
	 * 查询字典
	 * */
	@RequestMapping(value="/findDict")
	public @ResponseBody List<SysUcenterDict> findDict(String dict){
		return responseService.findDict(dict);
	}
	
	@RequestMapping("/statistics")
	public void statistics(HttpServletRequest request,HttpServletResponse response){
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer =  response.getWriter();
		   
		   String sql = getSql();
			
			Map<String, List>tempList = getTemplateList();
			
			List<Object[]>objList = pSourceService.getAreaStatisticsList(sql);
			
			if(objList==null||objList.size()==0){
				writer.println("false");
				writer.flush();
				return;
			}
			
			for(int i=0;i<objList.size();i++){
				Object[] objArry = (Object[])objList.get(i);
				tempList.put(objArry[0]+"_"+objArry[1],new ArrayList(Arrays.asList(objArry)));
			}
			
			List<List> dataList = getReturnList(tempList);
			
			//序列
			addIndex(dataList,0);
			//小计
			addSubTotal(dataList,4,9,15);
			
			//污染源合计
			addPollResourceTotal(dataList,4);
			
			//各个区小计行
			addAreaSubTotal(dataList);
			
			//所有的去区合并
			addAreaTotal(dataList);
			
		   writer.println(JSONArray.fromObject(dataList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addAreaSubTotal(List<List> dataList) {
		//每个第四行想加
		for(int i=0;i<dataList.size()-4;i=i+4){
			List list = dataList.get(i);//合计行
			List list1 =dataList.get(i+1);
			List list2 =dataList.get(i+2);
			List list3 =dataList.get(i+3);
			sumRows(list,list1,list2,list3);
		}
	}


	
	//downloadTemplate
	
	@RequestMapping("/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response){
		try {
		    
			ServletContext sc = request.getServletContext();
			String realPath = sc.getRealPath("/template")+File.separator+"pollutant-source-template.xlsx";
			InputStream in = new FileInputStream(realPath);
			
			response.addHeader("Content-Disposition", "attachment;filename=" + new String("pollutant-source-template.xlsx"));
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
	 * 查询所有的河道
	 * */
	@RequestMapping(value="/searchAllPollResource")
	public @ResponseBody DataTablesResult<PollutantSourceBean> searchAllResponse(@RequestParam(value = "start",defaultValue="0") int start,
			@RequestParam(value = "length",defaultValue="10") int pageSize,
			PollutantSourceBean bean){
		int page = getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		int t= pr.getPageNumber();
		SmsPage<PollutantSourceBean> records = null;
		records = pSourceService.findAll(pr,bean);
		
		List<SysUcenterDict> list= responseService.findDict("pollResType");
		List<SysUcenterDict> unitList=responseService.findDict("responsibilityUnit");
		
		changeToMap(list,dictMap);
		changeToMap(unitList,dictMap);
		
		changeDictCodeToNameToShow(records,dictMap);
		
		DataTablesResult<PollutantSourceBean> dtr = new DataTablesResult<PollutantSourceBean>();
		dtr.setData(records.getContent());
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}

	private void changeDictCodeToNameToShow(SmsPage<PollutantSourceBean> records, Map<String, String> dictMap2) {
		List<PollutantSourceBean> data = records.getContent();
		for(PollutantSourceBean bean:data){
			bean.setPollsourcetypeStr(dictMap2.get(bean.getPollsourcetype()));
			bean.setTherectificationresponsibilityunitStr(dictMap2.get(bean.getTherectificationresponsibilityunit()));
			
		}
		
	}

	/**
	 * 修改河道，返回数据
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody PollutantSourceBean findById(String id){
		PollutantSourceBean bean = pSourceService.findById(id);
		bean.setTherectificationresponsibilityunitStr(dictMap.get(bean.getTherectificationresponsibilityunit()));
		return bean;
	}	
	
	
	private int getPage(int start, int pageSize) {
		return (int)Math.floor((double)start/pageSize);
	}
	
	private void changeToMap(List<SysUcenterDict> list, Map<String, String> map) {
		for(SysUcenterDict dict:list){
			map.put(dict.getCode(), dict.getName());
		}
	}
	
	private void sumRows(List list, List list1, List list2, List list3) {
		for(int i=3;i<list.size();i++){
			BigInteger totalVal = (BigInteger) list.get(i);
			totalVal = ((BigInteger)list1.get(i)).add((BigInteger)list2.get(i)).add((BigInteger)list3.get(i));
		}
	}

	private void addPollResourceTotal(List<List> dataList,int addItemIndex) {
		for(int i=0;i<dataList.size();i++){
			List list = dataList.get(i);
			list.add(addItemIndex, ((BigInteger)list.get(addItemIndex)).add((BigInteger)list.get(addItemIndex+5)).add((BigInteger)list.get(addItemIndex+11)));
		}
	}

	private void addSubTotal(List<List> objList,int AddItemIndex1,int AddItemIndex2,int AddItemIndex3) {
		for(int i=0;i<objList.size();i++){
			List list = objList.get(i);
			list.add(AddItemIndex1, ((BigInteger)list.get(AddItemIndex1)).add((BigInteger)list.get(AddItemIndex1+1)).add((BigInteger)list.get(AddItemIndex1+2)).add((BigInteger)list.get(AddItemIndex1+3)));
			list.add(AddItemIndex2, ((BigInteger)list.get(AddItemIndex2)).add((BigInteger)list.get(AddItemIndex2+1)).add((BigInteger)list.get(AddItemIndex2+2)).add((BigInteger)list.get(AddItemIndex2+3)));
			list.add(AddItemIndex3, ((BigInteger)list.get(AddItemIndex3)).add((BigInteger)list.get(AddItemIndex3+1)).add((BigInteger)list.get(AddItemIndex3+2)));
		}
		
	}

	//添加序号
	private List<List> addIndex(List<List> objList,int AddItemIndex) {
		List<List> objs = new ArrayList<List>();
		int index = 1;
		for(int i=0;i<objList.size();i++){
			List currList = objList.get(i);
			currList.add(AddItemIndex, index+"");
			if((i+1)%belongPollRivers.length==0){
				index++;
			}
			objs.add(currList);
		}
		return objs;
	}

	private List getReturnList(Map<String, List> tempList) {
		List<List>objList = new ArrayList<List>();
		for(int i=0;i<areas.length;i++){
			for(int j=0;j<belongPollRivers.length;j++){
				objList.add(tempList.remove(areas[i]+"_"+belongPollRivers[j]));
			}
		}
		return objList;
	}

	private Map<String,List> getTemplateList() {
		Map<String,List>map = new HashMap<String,List>();
		for(int i=0;i<areas.length;i++){
			for(int j=0;j<belongPollRivers.length;j++){
				map.put(areas[i]+"_"+belongPollRivers[j], 
						new ArrayList(Arrays.asList(new Object[]{
								areas[i],
								belongPollRivers[j],
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0),
								BigInteger.valueOf(0)})));
			}
		}
		return map;
	}

	
	private String getAreaStatisticsSql(String area,String belongPollRiver) {
		StringBuilder selectSb = new StringBuilder();
		selectSb.append("select * from v_area_pollresource_statistics where area='"+area+"' and belongPollRiver='"+belongPollRiver+"' ");
		return selectSb.toString();
	}
	
	
	public String getSql() {
		StringBuilder selectSb = new StringBuilder();
		
		selectSb.append("select main.area area,main.belongPollRiver 河涌所属污染河道,main.cnt 污染河涌数量,");
		selectSb.append("CASE WHEN poll1.cnt is null then 0 ELSE poll1.cnt END 工业cnt1,");
		selectSb.append("CASE WHEN poll2.cnt is null then 0 ELSE poll2.cnt END 工业cnt2,");
		selectSb.append("CASE WHEN poll3.cnt is null then 0 ELSE poll3.cnt END 工业cnt3,");
		selectSb.append("CASE WHEN poll4.cnt is null then 0 ELSE poll4.cnt END 工业cnt4,");
		selectSb.append("CASE WHEN poll5.cnt is null then 0 ELSE poll5.cnt END 农业cnt5,");
		selectSb.append("CASE WHEN poll6.cnt is null then 0 ELSE poll6.cnt END 农业cnt6,");
		selectSb.append("CASE WHEN poll7.cnt is null then 0 ELSE poll7.cnt END 农业cnt7,");
		selectSb.append("CASE WHEN poll8.cnt is null then 0 ELSE poll8.cnt END 农业cnt8,");
		selectSb.append("CASE WHEN poll9.cnt is null then 0 ELSE poll9.cnt END 排水口cnt,");
		selectSb.append("CASE WHEN poll10.cnt is null then 0 ELSE poll10.cnt END 排水口污染cnt1,");
		selectSb.append("CASE WHEN poll11.cnt is null then 0 ELSE poll11.cnt END 排水口污染cnt2,");
		selectSb.append("CASE WHEN poll12.cnt is null then 0 ELSE poll12.cnt END 排水口污染cnt3 ");

		selectSb.append("from ( ");
		selectSb.append("	select t.`name` area,r.belongPollRiver,count(1) cnt from sys_ucenter_dict t  ");
		selectSb.append("		LEFT JOIN rivers r on t.`name` = r.area ");
		selectSb.append("	where t.parent='area' and r.belongPollRiver<>'0' ");
		selectSb.append("	group by r.area,r.belongPollRiver ");
		selectSb.append(") main ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '工业污染' AND p.theRectificationResponsibilityUnit = '1' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll1 on main.area=poll1.area and main.belongPollRiver=poll1.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '工业污染' AND p.theRectificationResponsibilityUnit = '2' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll2 on main.area=poll2.area and main.belongPollRiver=poll2.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '工业污染' AND p.theRectificationResponsibilityUnit = '3' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll3 on main.area=poll3.area and main.belongPollRiver=poll3.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '工业污染' AND p.theRectificationResponsibilityUnit = '4' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll4 on main.area=poll4.area and main.belongPollRiver=poll4.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '农业污染' AND p.theRectificationResponsibilityUnit = '1' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll5 on main.area=poll5.area and main.belongPollRiver=poll5.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '农业污染' AND p.theRectificationResponsibilityUnit = '2' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll6 on main.area=poll6.area and main.belongPollRiver=poll6.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '农业污染' AND p.theRectificationResponsibilityUnit = '3' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll7 on main.area=poll7.area and main.belongPollRiver=poll7.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from pollutant_source p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.industryoragriculture = '农业污染' AND p.theRectificationResponsibilityUnit = '4' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll8 on main.area=poll8.area and main.belongPollRiver=poll8.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from outfall_polluate_resource p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll9 on main.area=poll9.area and main.belongPollRiver=poll9.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from outfall_polluate_resource p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.theRectificationResponsibilityUnit = '5' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll10 on main.area=poll10.area and main.belongPollRiver=poll10.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from outfall_polluate_resource p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.theRectificationResponsibilityUnit = '6' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll11 on main.area=poll11.area and main.belongPollRiver=poll11.belongPollRiver ");
		selectSb.append("LEFT JOIN ( ");
		selectSb.append("	SELECT p.area,r.belongPollRiver,count(1) cnt  ");
		selectSb.append("	from outfall_polluate_resource p,rivers r  ");
		selectSb.append("	where p.riverCode=r.river_code  AND p.theRectificationResponsibilityUnit = '4' ");
		selectSb.append("	group by p.area,r.belongPollRiver) poll12 on main.area=poll12.area and main.belongPollRiver=poll12.belongPollRiver ");
		return selectSb.toString();
		
		
	}
	
	
	private void addAreaTotal(List<List> dataList) {
		Object[]objs = null;
		for(int i=0;i<belongPollRivers.length;i++){
			List list1 = dataList.get(i);
			objs = new Object[list1.size()];
			objs[0] = "0";
			objs[1] = "全市合计";
			objs[2] = i+"";
			List list2 = dataList.get(i+4);
			List list3 = dataList.get(i+8);
			List list4 = dataList.get(i+12);
			List list5 = dataList.get(i+16);
			List list6 = dataList.get(i+20);
			List list7 = dataList.get(i+24);
			List list8 = dataList.get(i+28);
			List list9 = dataList.get(i+32);
			List list10 = dataList.get(i+36);
			for(int j=3;j<objs.length;j++){
				objs[j] = ((BigInteger)list2.get(j)).
						add((BigInteger)list3.get(j)).
						add((BigInteger)list4.get(j)).
						add((BigInteger)list5.get(j)).
						add((BigInteger)list6.get(j)).
						add((BigInteger)list7.get(j)).
						add((BigInteger)list8.get(j)).
						add((BigInteger)list9.get(j)).
						add((BigInteger)list10.get(j));
			}
			dataList.add(i,new ArrayList(Arrays.asList(objs)));
		}
	}
	
}
