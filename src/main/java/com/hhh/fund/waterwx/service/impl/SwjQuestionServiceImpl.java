package com.hhh.fund.waterwx.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import jxl.write.DateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.alibaba.druid.support.logging.LogFactory;
import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.dao.ResponsibilityDao;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SwjAnswerDao;
import com.hhh.fund.waterwx.dao.SwjAttachmentDao;
import com.hhh.fund.waterwx.dao.SwjQuestionDao;
import com.hhh.fund.waterwx.dao.SwjQuestionItemDao;
import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.dao.SwjUserDao;
import com.hhh.fund.waterwx.dao.SwjWeiXinUserDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjUser;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.entity.SysUcenterDepartment;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.webmodel.ComplainListJsonBean;
import com.hhh.fund.waterwx.webmodel.PhotoListBean;
import com.hhh.fund.waterwx.webmodel.PictureUrlBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjComplainBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionItemBean;
import com.hhh.fund.waterwx.webmodel.ToFrontWeiXinUserBean;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.util.CacheDataUtils;
import com.hhh.fund.waterwx.util.ComplainConstants;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.util.StringUtil;
@Component
@Transactional
public class SwjQuestionServiceImpl implements SwjQuestionService {
		@Autowired
		private SwjQuestionDao questionDao;
		@Autowired
		private SwjQuestiontypeDao typeDao;
		@Autowired
		private ResponsibilityDao responseDao;
		@Autowired
		private SwjAttachmentDao attDao;
		@Autowired
		private RiverDao riverDao;
		@Autowired
		private SwjAnswerDao answerDao;
		@Autowired
		private SwjQuestionItemDao swjQuestionItemDao;
		@Autowired
		private SwjAttachmentDao swjAttachmentDao;
		
		@PersistenceContext
		private EntityManager entityManager;

		@Autowired
		private SwjUserDao swjUserDao;
		/**
		 * 查找所有问题
		 * */
		public List<SwjQuestion> findAll(){
			List<SwjQuestion> list = (List<SwjQuestion>)questionDao.findAll();
			return list;
		}
		
		public SmsPage<SwjQuestionBean> findQuestionAll(Pageable page) {
			Page<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec, page);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			
			if(null != alist.getContent() && !alist.getContent().isEmpty()){
				for(SwjQuestion a : alist.getContent()){
					SwjQuestionBean bean = new SwjQuestionBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date startTime = null;
					startTime = a.getComplainDate();
					String d = sdf.format(startTime);
					System.out.println(d+"ddddd");
					//bean.setComplainDate(d);
					//a.getComplainDate();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return null;
			}
			return new SmsPage<SwjQuestionBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}
		
		/**
		 * 获取页码,从0页开始
		 * @return
		 */
		public int getPage(int start,int pageSize){
			return (int)Math.floor((double)start/pageSize);
		}
		/**
		 * 微信（我的投诉） 
		 * @param openId 微信编号 page 分页所需参数
		 * @return map
		 * */
		public Map<String,Object> findByOpenid(String openId,Pageable page){
			Page<SwjQuestion> list= questionDao.findByOpenidOrderByComplainDateDesc(openId,page);
			List<SwjQuestion> quesList = list.getContent();
			long count = list.getTotalElements();
			
			int cou = (int)count;
			Map<String,Object> map = new HashMap<String,Object>();
			//分页后的list
			map.put("list", quesList);
			//我的投诉的总量
			map.put("total", cou);
			return map;
		}
		/**
		 * 拿到问题类型
		 * */
		public List<SwjQuestiontype> getAllQuestionType(){
			List<SwjQuestiontype> list  = typeDao.getAllOrderby();
			return list;
		}
		/**
		 * 拿到河段
		 * */
		public List<Responsibility> getAllRiver(){
			List<Responsibility> list = new ArrayList<Responsibility>();
			Iterator<Responsibility> it = responseDao.findAll().iterator();
			while(it.hasNext()){
				list.add(it.next());
			}
			List<Responsibility> list1 = new ArrayList<Responsibility>();
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String rivername = list.get(i).getRiverName();
					String rivername1 = list.get(j).getRiverName();
	                    if (rivername.equals(rivername1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 拿到左右岸
		 * @param 河流名称
		 * */
		public List<Responsibility> getCoast(String rivername){
			List<Responsibility> list = responseDao.findByRiverName(rivername);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String leftright = list.get(i).getLeftRight();
					String leftright1 = list.get(j).getLeftRight();
	                    if (leftright.equals(leftright1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 拿到河段
		 * @param 河流名称 左右岸
		 * */
		public List<Responsibility> getReach(String rivername,String lr){
			System.out.println("service");
			System.out.println(rivername+","+lr);
			List<Responsibility> list = responseDao.findByRiverNameAndLeftRight(rivername, lr);
			System.out.println(list.size()+"reachservice");
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getPartName();
					String partname1 = list.get(j).getPartName();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 保存方法
		 * */
		public String save(SwjQuestionBean bean){
			SwjQuestion question = new SwjQuestion();
			BeanUtils.copyProperties(bean, question);
			question.setComplainDate(new Date());
			SwjQuestion swj = questionDao.save(question);
			String id = swj.getId();
			return id;
		}
		/**
		 * 保存方法
		 * */
		public String saveQuestion(SwjQuestionBean bean){
			SwjQuestion question = new SwjQuestion();
			BeanUtils.copyProperties(bean, question);
			question.setComplainDate(new Date());
			SwjQuestion swj = questionDao.save(question);
			String id = swj.getId();
			return id;
		}
		/**
		 * 修改状态
		 * */
		public void updateStatus(String id,int status){
			SwjQuestion question = questionDao.findOne(id);
			question.setStatus(status);
			questionDao.save(question);
		}
		/**
		 * 投诉退回修改状态
		 * */
		public void updateBackStatus(String id,int status){
			SwjQuestion question = questionDao.findOne(id);
			if(status==1){
				question.setArea("");
				question.setAreaPersonId("");
			}else if(status==2){
				question.setStreetPersonId("");
			}else{
				question.setVillagePersonId("");
			}	
			questionDao.save(question);
		}
		
		public SwjQuestion  findByOpenIdAndId(String openId,String id){
			
			return questionDao.findByOpenidAndId(openId,id);
		}
		/**
		 * 保存附件
		 * */
		public void saveAttachment(SwjAttachment att){
			attDao.save(att);
		}
		public SwjQuestionBean findById(String id){
			SwjQuestion question = questionDao.findOne(id);
			SwjQuestionBean bean = new SwjQuestionBean();
			Date date =question.getComplainDate();
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d = sdf.format(date);
			BeanUtils.copyProperties(question, bean);
			bean.setComplainDate(date);
			return bean;
		}
		
		public SwjQuestion findQuestionById(String id){
			if(StringUtils.isBlank(id)){
				return null;
			}
			SwjQuestion question = questionDao.findById(id);
			return question;
		}
		
		/**
		 * pc端 保存投诉信息
		 * */
		public void saveQuestionByWeb(SwjQuestionBean bean){
			SwjQuestion question = new SwjQuestion();
			SwjQuestion qu = questionDao.findOne(bean.getId());
			Date  date = qu.getComplainDate();
			String personname = bean.getReachname();
			List<Responsibility> reList = responseDao.findByRiverName(personname);
			String name = "";
			if(reList.size()>0){
				Responsibility rb = reList.get(0);
				name = rb.getDistMgrName();
			}
			String riverCode = bean.getRiverCode();
			String area = bean.getArea();
			String resultID = generageCode(area, date);
			qu.setPersonname(name);
			qu.setLength(bean.getLength()+"km");
			qu.setCode(resultID);
			qu.setPhone(bean.getPhone());
			qu.setArea(bean.getArea());
			qu.setRiverCode(bean.getRiverCode());
			qu.setLeftRight(bean.getLeftRight());
			qu.setPartName(bean.getPartName());
			qu.setQuestioncontent(bean.getQuestioncontent());
			qu.setQuestiontype(bean.getQuestiontype());
			qu.setIscross(bean.getIscross());//设置 是否跨级下发
			String reachname = bean.getReachname();
			if(reachname.equals("请选择")){
				reachname = "";
			}
			qu.setReachname(reachname);
			qu.setStatus(bean.getStatus());
			qu.setAreaPersonName(bean.getAreaPersonName());
			qu.setStreetPersonName(bean.getStreetPersonName());
			qu.setVillagePersonName(bean.getVillagePersonName());
			qu.setAreaLimitDate(bean.getAreaLimitDate());
			qu.setStreetLimitDate(bean.getStreetLimitDate());
			qu.setVillageLimitDate(bean.getVillageLimitDate());
			SwjQuestion swj = questionDao.save(qu);
		}

		private String generageCode(String area, Date date) {
			DateFormat format = new SimpleDateFormat("yyyy");
			String time = format.format(date);
			String year = time.substring(2);
			System.out.println("year"+year);
			
			String areaCode = "";
			if(area.equals("天河区")){
				areaCode = "TH";
			}else if(area.equals("花都区")){
				areaCode = "HD";
			}else if(area.equals("白云区")){
				areaCode = "BY";
			}else if(area.equals("越秀区")){
				areaCode = "YX";
			}else if(area.equals("海珠区")){
				areaCode = "HZ";
			}else if(area.equals("荔湾区")){
				areaCode = "LW";
			}else if(area.equals("黄埔区")){
				areaCode = "HP";
			}else if(area.equals("番禺区")){
				areaCode = "PY";
			}else{
				areaCode = "ZC";
			}
			 String resultID="TS-"+areaCode+"-"+year;
			 String maxSQM=null;
			 List<SwjQuestion> list = (List<SwjQuestion>)questionDao.findAll();
			 String question1 = questionDao.getMaxCode();
			 System.out.println(question1+"12345");
			 maxSQM = question1;
			 System.out.println(maxSQM+"sqm");
			 if(maxSQM==null){
					resultID+="00001";
			 }else{
						Long count=Long.parseLong(maxSQM);
						count++;
						String countStr=count.toString();
						while(countStr.length()<maxSQM.length())
							countStr="0"+countStr;
						resultID+=countStr;
			 }
			return resultID;
		}
		public List<SwjAttachment> getAttchId(String qId){
			return attDao.findByQuestionId(qId);
		}
		
		public SwjAttachment getAttachment(String id){
			return attDao.findOne(id);
		}
		/**
		 * 根据河道编号和河段名称查询责任人
		 * */
		public List<Responsibility> findByRiverCodeAndPartName(String riverCode,String partName){
			List<Responsibility> list = responseDao.findByRiverCodeAndPartName(riverCode, partName);
			return list;
		}
		/**
		 * 统计地区问题数量 2图
		 * 
		 * */
		public List<River> getTj(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<River> list = riverDao.getTj(startdate,enddate);
			return list;
		}
		
		/**
		 * 统计地区问题数量
		 * 
		 * */
		public List<SwjQuestion> getAreaQuestionTj(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getAreaQuestionTj(startdate,enddate);
			return list;
		}
		
		public List<SwjQuestion> getAreaQuestion(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					cal.add(Calendar.MONTH, -1);
					
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getQuestionType(startdate,enddate);
			return list;
		}
		/**
		 * 查询所有地区 统计
		 * */
		public List<River> getAllArea(){
			List<River> list = riverDao.getAllArea();
			return list;
		}
		/**
		 * 查询所有地区 统计
		 * */
		public List<SwjQuestion> findQuestionAllArea(){
			List<SwjQuestion> list = questionDao.findQuestionAllArea();
			return list;
		}
		/**
		 * 查询问题类型 统计
		 * */
		public List<SwjQuestiontype> getQuestionTypeByTj(){
			List<SwjQuestiontype> list = typeDao.getAllType();
			return list;
		}
		/**
		 * 查询已处理的投诉
		 * */
		public List<SwjQuestion> getYcl(){
			List<SwjQuestion> list = questionDao.getYcl();
			return list;
		}
		/**
		 * 查询未处理的投诉
		 * */
		public List<SwjQuestion> getWcl(){
			List<SwjQuestion> list = questionDao.getWcl();
			return list;
		}
		/**
		 * 根据河段名称查找河段编号
		 * */
		public River getRiverByRiverName(String riverName){
			System.out.println(riverName+"riverService");
			List<River> river = riverDao.findByRiverName(riverName);
			River r = null;
			if(river.size()>0){
				r = river.get(0);
			}
			return r;
		}
		/**
		 * 根据地区查河段
		 * @param area 地区
		 * */
		public List<River> getRiversByArea(String area){
			List<River> rivers = riverDao.findByArea(area);
			return rivers;
		}
		/**
		 * 根据地区查问题 并统计
		 * @param area 地区
		 * */
		public List<SwjQuestion> getRiversByAreaToTj(String area,String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					cal.add(Calendar.MONTH, -1);
					
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getTypeByArea(area,startdate,enddate);
			return list;
		}
		/**
		 * 根据河段查问题 并统计
		 * @param rivername 河段名称
		 * */
		public List<SwjQuestion> getRiversByRiverToTj(String rivername){
			List<SwjQuestion> list = questionDao.getTypeByRiverName(rivername);
			return list;
		}
		/**
		 * 无效投诉保存状态
		 * */
		public void saveWxQuestion(String id,String content){
			SwjQuestion q = questionDao.findOne(id);
			Date date = q.getComplainDate();
			String resultID = generageCode("151", date);
			String code = "";
			if(resultID != null){
				code  = resultID.substring(2);
			}
			String wxstr = "WX"+code;
			SwjAnswer answer = new SwjAnswer();
			answer.setAnswercontent(content);
			answer.setAnswerDate(new Date());
			answer.setQuestionId(id);
			q.setStatus(3);
			q.setCode(wxstr);
			questionDao.save(q);
			answerDao.save(answer);
		}
		
		/**
		 * 获取已处理的问题 地区分类统计
		 * */
		public List<SwjQuestion> getCountByAreaYcl(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					cal.add(Calendar.MONTH, -1);
					
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getCountByAreaYcl(startdate,enddate);
			return list;
		}
		/**
		 * 获取已处理的问题 地区分类统计2
		 * */
		public List<SwjQuestion> getQuestionCountByAreaYcl(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getQuestionCountByAreaYcl(startdate,enddate);
			return list;
		}
		/**
		 * 获取所有的问题 地区分类统计(不关联河流)
		 * */
		public List<SwjQuestion> getQuestionCountByAreaAll(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getQuestionCountByAreaAll(startdate,enddate);
			return list;
		}
		
		/**
		 * 获取所有的问题 地区分类统计
		 * */
		public List<SwjQuestion> getCountByAreaAll(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					cal.add(Calendar.MONTH, -1);
					
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getCountByAreaAll(startdate,enddate);
			return list;
		}
		
		/**
		 * 获取正在处理的问题 地区分类统计
		 * */
		public List<SwjQuestion> getCountAreaZzcl(String area){
			List<SwjQuestion> list = questionDao.getCountAreaZzcl(area);
			return list;
		}
		/**
		 * 获取已处理的问题 地区分类统计
		 * */
		public List<SwjQuestion> getCountAreaYcl(String area){
			List<SwjQuestion> list = questionDao.getCountAreaYcl(area);
			return list;
		}
		/**
		 * 根据地区和河段获取所有问题
		 * */
		public List<SwjQuestion> getTypeByRiverNameAndArea(String riverName,String area,String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					cal.add(Calendar.MONTH, -1);
					
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getTypeByRiverNameAndArea(riverName,area,startdate,enddate);
			return list;
		}
		/**
		 * 根据地区和河段和问题类型获取问题
		 * */
		public List<SwjQuestion> getQuestionByRiverNameAndArea(String rivername,String area,String questiontype){
			List<SwjQuestion> list = new ArrayList();
			if(rivername == null || rivername.equals("")|| rivername.equals("null")){
				if(area == null || area.equals("")|| area.equals("null")){
					list = questionDao.findByQuestiontype(questiontype);
				}else{
					list = questionDao.getQuestionByQuestiontypeAndArea(area, questiontype);
				}
			}else if( area == null || area.equals("")|| area.equals("null")){
				list = questionDao.getQuestionByRiverNameAndQuestiontype(rivername, questiontype);
			}else{
				list = questionDao.getQuestionByRiverNameAndArea(rivername,area,questiontype);
			}
			System.out.println(list.size()+"sb");
			return list;
		}
		/**
		 * 微信端 统计问题类型数量
		 * */
		/*public List<SwjQuestion> getQuestionTypeWx(){
			List<SwjQuestiontype> typeList = typeDao.getAllType();
			List<SwjQuestion> list = questionDao.getQuestionTypeWx();
			Map map = new HashMap(); 
			for(int i=0;i<typeList.size();i++){
				SwjQuestiontype q = typeList.get(i);
				String cont= q.getDetail();
				map.put(cont, 0);
			}
			for(int j=0;j<list.size();j++){
				String type = list.get(j).getQuestiontype();
				list.get(j).get
				map.remove(type);
				map.put(type, value)
				if()
			}
			return list;
		}*/
		/**
		 * 获得河道信息
		 * */
		public List<Responsibility> getAllResponsibility(String area,String rivername,String leftRight){
			List<Responsibility> list = responseDao.getByRivernameAndAreaAndLf(area, rivername, leftRight);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getPartName();
					String partname1 = list.get(j).getPartName();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 获得河道信息
		 * */
		public List<Responsibility> getAllResponseByAreaAndRiverName(String area,String rivername){
			List<Responsibility> list = responseDao.getByRivernameAndArea(area, rivername);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getLeftRight();
					String partname1 = list.get(j).getLeftRight();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 获得河道信息
		 * */
		public List<Responsibility> getAllResponseByFour(String area,String rivername,String lr,String reach){
			List<Responsibility> list = responseDao.getByRivernameAndAreaAndLfAndReach(area, rivername, lr, reach);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getLeftRight();
					String partname1 = list.get(j).getLeftRight();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 河道地区查询 河道 模糊查询
		 * 
		 * */
		public List<River> getRiverToWx(String rivername,String area){
			List<River> list = new ArrayList();
			if(rivername == null || rivername.equals("")){
				list = riverDao.findByArea(area);
			}else if( area == null || area.equals("")){
				list = riverDao.getRiverByRiver(rivername);
			}else{
				list = riverDao.getRiverByAreaAndRiver(area, rivername);
			}
			return list;
		}
		/**
		 * 导出报表统计数据
		 * */
		public List<SwjQuestionBean> getAllToExport(final String code,final String type,final int status,final String river,final String coast,final String reach,final String startTime,final String endTime,final String area){
			List<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != startTime && !startTime.isEmpty()){
						SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						System.out.println(startTime+"<<<<<"+endTime);
						Date startDate = null;
						Date endDate = null;
						if(null != endTime && !endTime.isEmpty()){
							try {
								startDate = sdf.parse(startTime);
								endDate = sdf.parse(endTime);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							try {
								startDate = sdf.parse(startTime);
								endDate = new Date();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						predicates.add(cb.between(root.get("complainDate").as(Date.class),startDate, endDate));
					}
					if(predicates.isEmpty()){
						return null;
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec);
			System.out.println(alist.size()+"size111");
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
				for(SwjQuestion a : alist){
					SwjQuestionBean bean = new SwjQuestionBean();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
				return accounts;
		}
		/**
		 * 导出list
		 * */
		public List<Map<Integer, Object>> getAllToExcel(String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			List<SwjQuestion> alist = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					//startdate = cal.getTime();
					endTime=format.format(Calendar.getInstance().getTime());
					startTime=format.format(cal.getTime());
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
			}
			String endtime=DateUtils.StringToDateTime_yMdhms(0)+" 17:00:00";
			String beginDate=DateUtils.StringToDateTime_yMdhms(7)+" 17:00:00";
			System.out.println("开始时间"+beginDate);
			System.out.println("结束时间"+endtime);
			String sql="select bb.cou2 AS cou,aa.cou AS cou1,scou.cou1 AS cou2 from ((((select s.area AS area,count(1) AS cou from waterweixin.swj_question s where ((s.status not in ('-1','1')) and (s.area <> '') and (s.status <> '') and (s.city = '广州市')) group by s.area)) aa left join (select s1.area AS area,ifnull(b.count,0) AS cou1 from (waterweixin.swj_area_select s1 left join (select sq.area AS area,count(1) AS count from waterweixin.swj_question sq where ((sq.status in ('5','10')) and (sq.area <> '') and (sq.city = '广州市')) group by sq.area) b on((s1.area = b.area)))) scou on((scou.area = aa.area))) left join (select s1.area AS area,ifnull(b.count,0) AS cou2 from (waterweixin.swj_area_select s1 left join (select sq.area AS area,count(1) AS count from waterweixin.swj_question sq where ((sq.status not in ('-1','1')) and (sq.area <> '') and (sq.complainDate between '"+beginDate+"' and '"+endtime+"') and (sq.city = '广州市')) group by sq.area order by sq.area) b on((s1.area = b.area)))) bb on((scou.area = bb.area))) order by aa.area";
			System.out.println(sql);
			Query query = entityManager.createNativeQuery(sql);
			alist = query.getResultList();
			System.out.println(alist.listIterator());
			//List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			 List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer,Object>>();
			for (int i = 0; i < alist.size(); i++) {
				Map<Integer, Object> data = new HashMap<Integer,Object>();
				Object  obj=alist.get(i);
				Object [] str=(Object [])obj;
				data.put(1, Integer.valueOf(str[0].toString()));
				data.put(2, Integer.valueOf(str[1].toString()));
				data.put(3, Integer.valueOf(str[2].toString()));
				datalist.add(data);
			}
			return datalist;
		}
		/**
		 * 总个数
		 */
		public Map<String, Object> getTotalToExcel(String startTime,String endTime){
			List<SwjQuestion> alist = null;
			endTime=DateUtils.StringToDateTime_yMdhms(0)+" 17:00:00";
			startTime=DateUtils.StringToDateTime_yMdhms(7)+" 17:00:00";
			String 	sql="select sum(bb.cou2) AS cou,sum(aa.cou) AS cou1,sum(scou.cou1) AS cou2 from ((((select s.area AS area,count(1) AS cou from waterweixin.swj_question s where ((s.status not in ('-1','1')) and (s.area <> '') and (s.status <> '') and (s.city = '广州市')) group by s.area)) aa left join (select s1.area AS area,ifnull(b.count,0) AS cou1 from (waterweixin.swj_area_select s1 left join (select sq.area AS area,count(1) AS count from waterweixin.swj_question sq where ((sq.status in ('5','10')) and (sq.area <> '') and (sq.city = '广州市')) group by sq.area) b on((s1.area = b.area)))) scou on((scou.area = aa.area))) left join (select s1.area AS area,ifnull(b.count,0) AS cou2 from (waterweixin.swj_area_select s1 left join (select sq.area AS area,count(1) AS count from waterweixin.swj_question sq where ((sq.status not in ('-1','1')) and (sq.area <> '') and (sq.complainDate between '"+startTime+"' and '"+endTime+"') and (sq.city = '广州市')) group by sq.area order by sq.area) b on((s1.area = b.area)))) bb on((scou.area = bb.area))) order by aa.area";
			
			System.out.println(sql);
			Query query = entityManager.createNativeQuery(sql);
			alist = query.getResultList();
			System.out.println(alist.listIterator());
			//List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			Map<String, Object> data = new HashMap<String,Object>();
			for (int i = 0; i < alist.size(); i++) {
				Object  obj=alist.get(i);
				Object [] str=(Object [])obj;
				data.put("C5", Integer.valueOf(str[0].toString()));
				data.put("D5", Integer.valueOf(str[1].toString()));
				data.put("E5", Integer.valueOf(str[2].toString()));
			}
			return data;
		}
		/**
		 * 导出为csv格式的文件
		 * @param list 导出的数据
		 * @param map  
		 * */
		public boolean exportCsv(File file, List<SwjQuestionBean> dataList){
	        boolean isSucess=false;
	        
	        FileOutputStream out=null;
	        OutputStreamWriter osw=null;
	        BufferedWriter bw=null;
	        try {
	            out = new FileOutputStream(file);
	            osw = new OutputStreamWriter(out);
	            bw =new BufferedWriter(osw);
	            if(dataList!=null && !dataList.isEmpty()){
	            	bw.append("受理编号,行政区域,投诉河道,问题类型,问题位置,投诉人,投诉人电话,投诉时间,投诉内容,投诉位置,照片,区级河长,镇街河长,村级河长,回复时间\r");
	                for(SwjQuestionBean bean : dataList){
	                	if(bean.getCode()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getCode()).append(",");
	                	}
	                	if(bean.getArea()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getArea()).append(",");
	                	}
	                	if(bean.getRiverCode()==null){
	                		bw.append("").append(",");
	                	}else{
	                		River river = riverDao.findByRiverCode(bean.getRiverCode());
	                		String rivername = river.getRiverName();
	                		bw.append(rivername).append(",");
	                	}
	                	if(bean.getQuestiontype()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestiontype()).append(",");
	                	}
	                	if(bean.getQuestionposition()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestionposition()).append(",");
	                	}
	                	//投诉人
	                	bw.append("").append(",");
	                	if(bean.getPhone()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getPhone()).append(",");
	                	}
	                	if(bean.getComplainDate()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getComplainDate()).append(",");
	                	}
	                	if(bean.getQuestioncontent()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestioncontent()).append(",");
	                	}
	                	if(bean.getQuestionposition()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestionposition()).append(",");
	                	}
	                	//照片
	                	bw.append("").append(",");
	                	//责任河长
	                	if(bean.getRiverCode() != null){
	                		String rivercode = bean.getRiverCode();
	                		System.out.println(bean.getRiverCode()+"rivercode");
	                			System.out.println("11111111");
	                			List<Responsibility> reList = responseDao.findByRiverCode(rivercode);
	                			System.out.println(reList.size()+"22222222");
	                			if(reList.size()>0){
	                				String mgrName = "";
	                				String streetName = "";
	                				String villageName = "";
	    	                		for(Responsibility re : reList){
	    	                			mgrName = re.getDistMgrName();
	    	                			streetName = re.getStreetMgrName();
	    	                			villageName = re.getVillageMgrName();
	    	                		}
	    	                		bw.append(mgrName).append(",");
	    	                		bw.append(streetName).append(",");
	    	                		bw.append(villageName).append(",");
	                			}else{
	                				bw.append("").append(",");
	                				bw.append("").append(",");
	                				bw.append("").append(",");
	                			}
	                	}else{
	                		bw.append("").append(",");
            				bw.append("").append(",");
            				bw.append("").append(",");
	                	}
	                	//回复内容
	                	String qid = bean.getId();
	                	List<SwjAnswer> answerList = answerDao.findLastAnswerByQuestionId(qid);
	                	if(answerList.size()>0){
	                		String content = answerList.get(0).getAnswercontent();
	                		/*if(content == null){
	                			bw.append("").append(",");
	                		}else{
	                			bw.append(content).append(",");
	                		}*/
	                		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                		Date date = answerList.get(0).getAnswerDate();
            				bw.append(format.format(date)).append("\r");
	                	}else{
	                		//bw.append("").append(",");
            				bw.append("").append("\r");
	                	}
	                	//bw.append(clientInfo.getDeviceInfo()).append(",");
	                    //bw.append(data).append(",");
	                }
	            }
	            isSucess=true;
	        } catch (Exception e) {
	            isSucess=false;
	        }finally{
	            if(bw!=null){
	                try {
	                    bw.close();
	                    bw=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(osw!=null){
	                try {
	                    osw.close();
	                    osw=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(out!=null){
	                try {
	                    out.close();
	                    out=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	        }
	        
	        return isSucess;
	    }
		/**
		 * 导出为csv格式的文件
		 * @param list 导出的数据
		 * @param map  
		 * */
		public boolean exportCsv1(File file, List<SwjQuestionBean> dataList,String content){
	        boolean isSucess=false;
	        
	        FileOutputStream out=null;
	        OutputStreamWriter osw=null;
	        BufferedWriter bw=null;
	        try {
	            out = new FileOutputStream(file);
	            osw = new OutputStreamWriter(out);
	            bw =new BufferedWriter(osw);
	            if(dataList!=null && !dataList.isEmpty()){
	            	bw.append(content+"\r");
	            	String[] contents = content.split(",");
	                for(SwjQuestionBean bean : dataList){
	                	for(int i=0;i<contents.length;i++){
	                		
	                	if(contents[i].equals("受理编号")){
	                	if(bean.getCode()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getCode()).append(",");
	                	}
	                	}
	                	
	                	if(contents[i].equals("行政区域")){
	                	if(bean.getArea()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getArea()).append(",");
	                	}
	                	}
	                	if(contents[i].equals("投诉河道")){
	                	if(bean.getRiverCode()==null){
	                		bw.append("").append(",");
	                	}else{
	                		River river = riverDao.findByRiverCode(bean.getRiverCode());
	                		String rivername = river.getRiverName();
	                		bw.append(rivername).append(",");
	                	}
	                	}
	                	
	                	if(contents[i].equals("问题类型")){
	                	if(bean.getQuestiontype()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestiontype()).append(",");
	                	}
	                	}
	                	if(contents[i].equals("问题位置")){
	                	if(bean.getQuestionposition()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestionposition()).append(",");
	                	}
	                	}
	                	//投诉人
	                	if(contents[i].equals("投诉人")){
	                	bw.append("").append(",");
	                	}
	                	if(contents[i].equals("投诉人电话")){
	                	if(bean.getPhone()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getPhone()).append(",");
	                	}
	                	}
	                	if(contents[i].equals("投诉时间")){
	                	if(bean.getComplainDate()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getComplainDate()).append(",");
	                	}
	                	}
	                	if(contents[i].equals("投诉内容")){
	                	if(bean.getQuestioncontent()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestioncontent()).append(",");
	                	}
	                	}
	                	if(contents[i].equals("投诉位置")){
	                	if(bean.getQuestionposition()==null){
	                		bw.append("").append(",");
	                	}else{
	                		bw.append(bean.getQuestionposition()).append(",");
	                	}
	                	}
	                	//照片
	                	if(contents[i].equals("照片")){
	                	bw.append("").append(",");
	                	}
	                	//责任河长
	                	if(bean.getRiverCode() != null){
	                		String rivercode = bean.getRiverCode();
	                		System.out.println(bean.getRiverCode()+"rivercode");
	                			System.out.println("11111111");
	                			List<Responsibility> reList = responseDao.findByRiverCode(rivercode);
	                			System.out.println(reList.size()+"22222222");
	                			if(reList.size()>0){
	                				String mgrName = "";
	                				String streetName = "";
	                				String villageName = "";
	    	                		for(Responsibility re : reList){
	    	                			mgrName = re.getDistMgrName();
	    	                			streetName = re.getStreetMgrName();
	    	                			villageName = re.getVillageMgrName();
	    	                		}
	    	                	if(contents[i].equals("区级河长")){
	    	                		bw.append(mgrName).append(",");
	    	                	}
	    	                		if(contents[i].equals("镇街河长")){
	    	                		bw.append(streetName).append(",");
	    	                		}
	    	                		if(contents[i].equals("村级河长")){
	    	                		bw.append(villageName).append(",");
	    	                		}
	                			}else{
	                				if(contents[i].equals("区级河长")){
	                				bw.append("").append(",");
	                				}
	                				if(contents[i].equals("镇街河长")){
	                				bw.append("").append(",");
	                				}
	                				if(contents[i].equals("村级河长")){
	                				bw.append("").append(",");
	                				}
	                			}
	                	}else{
	                		if(contents[i].equals("区级河长")){
	                		bw.append("").append(",");
	                		}
	                		if(contents[i].equals("镇街河长")){
            				bw.append("").append(",");
	                		}
	                		if(contents[i].equals("村级河长")){
            				bw.append("").append(",");
	                		}
	                	}
	                	//回复内容
	                	String qid = bean.getId();
	                	List<SwjAnswer> answerList = answerDao.findLastAnswerByQuestionId(qid);
	                	if(answerList.size()>0){
	                		//String content = answerList.get(0).getAnswercontent();
	                		/*if(content == null){
	                			bw.append("").append(",");
	                		}else{
	                			bw.append(content).append(",");
	                		}*/
	                		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                		Date date = answerList.get(0).getAnswerDate();
	                		if(contents[i].equals("回复时间")){
            				bw.append(format.format(date));
	                		}
	                	}else{
	                		//bw.append("").append(",");
	                		if(contents[i].equals("回复时间")){
            				bw.append("");
	                		}
	                	}
	                	//bw.append(clientInfo.getDeviceInfo()).append(",");
	                    //bw.append(data).append(",");
	                }
	                	bw.append("\r");
	                }
	            }
	            isSucess=true;
	        } catch (Exception e) {
	            isSucess=false;
	        }finally{
	            if(bw!=null){
	                try {
	                    bw.close();
	                    bw=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(osw!=null){
	                try {
	                    osw.close();
	                    osw=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(out!=null){
	                try {
	                    out.close();
	                    out=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	        }
	        
	        return isSucess;
	    }
		/**
		 * 查询条件查询投诉
		 * */
		public SmsPage<SwjQuestionBean> findBySearch(final String code,final String type,final int status,
				final String river,final String coast,final String reach,final String startTime,
				final String endTime,final String area,final String areaname,final String streetname,final String villagename,final String importance,final String iscross,Pageable page){
			Page<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != code && !code.isEmpty()){
						predicates.add(cb.like(root.get("code").as(String.class), "%"+code+"%"));
					}
					if(null != type && !type.isEmpty() && !type.equals("请选择")){
						predicates.add(cb.equal(root.get("questiontype").as(String.class), type));
					}
					if(null != importance && !importance.isEmpty() && !importance.equals("请选择")){
						predicates.add(cb.equal(root.get("importance").as(String.class), importance));
					}
					//System.out.println(status);
					if(status==0){
						predicates.add(cb.notEqual(root.get("status").as(Integer.class), -1));
						//predicates.add(cb.equal(root.get("status").as(Integer.class), status));
					}
					/*if(status != 4){
						if(status == 35){
							Predicate p = cb.equal(root.get("status").as(Integer.class), 3);
							Predicate p1 = cb.equal(root.get("status").as(Integer.class), 5);
							predicates.add(cb.or(p,p1));
						}else{
							predicates.add(cb.equal(root.get("status").as(Integer.class), status));
						}
					}*/
					if(null != river && !river.isEmpty() && !river.equals("请选择")){
						predicates.add(cb.equal(root.get("reachname").as(String.class), river));
					}
					if(null != coast && !coast.isEmpty() && !coast.equals("请选择")){
						predicates.add(cb.equal(root.get("leftRight").as(String.class), coast));
					}
					if(null != reach && !reach.isEmpty() && !reach.equals("请选择")){
						predicates.add(cb.equal(root.get("partName").as(String.class), reach));
					}
					if(null != area && !area.isEmpty() && !area.equals("请选择")){
						predicates.add(cb.equal(root.get("area").as(String.class), area));
					}
					if(null != areaname && !areaname.isEmpty()){
						predicates.add(cb.equal(root.get("areaPersonName").as(String.class), areaname));
					}
					if(null != streetname && !streetname.isEmpty()){
						predicates.add(cb.equal(root.get("streetPersonName").as(String.class), streetname));
					}
					if(null != villagename && !villagename.isEmpty() && !villagename.equals("请选择")){
						predicates.add(cb.equal(root.get("villagePersonName").as(String.class), villagename));
					}
					if(null != iscross && !iscross.isEmpty() && !iscross.equals("请选择")){
						String isCross = "";
						if(iscross.equals("0")){
							isCross = "0";
							predicates.add(cb.equal(root.get("iscross").as(String.class), isCross));
						}else{
							isCross = "0";
							predicates.add(cb.isNull(root.get("iscross").as(String.class)));
						}
					}
					if(null != startTime && !startTime.isEmpty()){
						SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date startDate = null;
						Date endDate = null;
						if(null != endTime && !endTime.isEmpty()){
							try {
								startDate = sdf.parse(startTime);
								endDate = sdf.parse(endTime);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							try {
								startDate = sdf.parse(startTime);
								endDate = new Date();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						predicates.add(cb.between(root.get("complainDate").as(Date.class),startDate, endDate));
					}
					if(predicates.isEmpty()){
						query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
						return query.getRestriction();
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec, page);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			if(null != alist.getContent() && !alist.getContent().isEmpty()){
				for(SwjQuestion a : alist.getContent()){
					SwjQuestionBean bean = new SwjQuestionBean();
					bean=getUser(bean,a.getId(),"1");
					bean=getUser(bean,a.getId(),"2");
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date time = null;
					time = a.getComplainDate();
					String d = sdf.format(time);
					a.setNickname(a.getWeixinUser()==null?"":a.getWeixinUser().getName());
					BeanUtils.copyProperties(a, bean);
					bean.setComplainDate(a.getComplainDate());
					accounts.add(bean);
				}
			}else{
				return  new SmsPage<SwjQuestionBean>(0, 0, accounts);
			}
			return new SmsPage<SwjQuestionBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}
		public SwjQuestionBean getUser(SwjQuestionBean a,String id,String type){
			//查询item表
			List<SwjQuestionItems> itemlist=swjQuestionItemDao.findByDataId(id,type);
			String name="";
			String content="";
			if(itemlist.size()!=0){
				SwjQuestionItems item=itemlist.get(0);
				//System.out.println(item.getOpenId()+"id");
				String userid="";
				if(item.getOpenId()!=null && !"".equals(item.getOpenId())){
					userid=item.getOpenId().substring(7, item.getOpenId().length());
					//System.err.println(userid+"userid");
					SwjUser acc=swjUserDao.findByUserid(userid);
					if(acc!=null){
						name =acc.getName();
					}
					
				}
				content=item.getContent();
			}
			if("1".equals(type)){//市 
				a.setCityName(name);
				a.setCitycontent(content);
			}else{//区
				a.setAreaName(name);
				a.setAreacontent(content);
			}
			
			return a;
		}
		/*//获取投诉人微信名称
		public List<SwjQuestionBean> getSwjUser(Page<SwjQuestion> alist,List<SwjQuestionBean> accounts){
			if(null != alist.getContent() && !alist.getContent().isEmpty()){
				for(SwjQuestion a : alist.getContent()){
					SwjQuestionBean bean = new SwjQuestionBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String name = null;
					name = a.getWeixinUser().getName();
					a.setNickname(name);
					System.out.println(name+"-----name");
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return null;
			}
			return accounts;
			
		}*/
		/**
		 * 获取区级，街道，村级河长，姓名及联系电话
		 * */
		public List<Responsibility> getPersonName(String area,String rivername,String lr,String reach){
			List<Responsibility> list = null;
			if(rivername == null || rivername.equals("") || rivername.equals("请选择")){
				list = responseDao.getAreaNameByArea(area);
			}else{//河道不为空
				if(lr == null || lr.equals("") || lr.equals("请选择")){
					if(reach == null || reach.equals("") || reach.equals("请选择")){
						list = responseDao.getStreetNameByAreaAndRiverName(area, rivername);
					}else{
						list = responseDao.getVillageNameByReach(area, rivername, reach);
					}
				}else{
					list = responseDao.getVillageName(area, rivername, lr);
				}
				
			}
			return list;
		}
		/**
		 * 获取村级河长
		 * */
		public List<Responsibility> getVillagePersonName(String area,String rivername){
			List<Responsibility> list = responseDao.getVillageNameByAreaAndRiver(area,rivername);
			return list;
		}
		/**
		 * 获取街镇河长电话
		 * */
		public List<Responsibility> getTelByStreetName(String streetName){
			List<Responsibility> list = responseDao.findByStreetMgrName(streetName);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getStreetMgrTel();
					String partname1 = list.get(j).getStreetMgrTel();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 获取村级河长电话
		 * */
		public List<Responsibility> getTelByVillageName(String villageName){
			List<Responsibility> list = responseDao.findByVillageMgrName(villageName);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getVillageMgrTel();
					String partname1 = list.get(j).getVillageMgrTel();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 获取区级河长电话
		 * */
		public List<Responsibility> getTelByAreaName(String areaName){
			List<Responsibility> list = responseDao.findByDistMgrName(areaName);
			for(int i=0;i<list.size();i++){
				 for (int j = list.size() - 1 ; j > i; j--){
					 String partname = list.get(i).getDistMgrTel();
					String partname1 = list.get(j).getDistMgrTel();
	                    if (partname.equals(partname1)){
	                        list.remove(list.get(j));
	                    }
	                }
			}
			return list;
		}
		/**
		 * 查询所有的问题，地图显示
		 * @param area 行政区域
		 * @param sTime 开始时间
		 * @param eTime 结束时间
		 * */
		public List<SwjQuestionBean> getAllQuestion(final String area,final Date sdate,final Date edate){
			List<SwjQuestion> alist = null;
			System.out.println("6666"+sdate+","+edate);
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != area && !area.isEmpty() && !area.equals("请选择")){
						predicates.add(cb.equal(root.get("area").as(String.class), area));
					}
					if(null != edate){
						predicates.add(cb.between(root.get("complainDate").as(Date.class),sdate, edate));
					}
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			if(alist != null && alist.size()>0){
				for(SwjQuestion a : alist){
					SwjQuestionBean bean = new SwjQuestionBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date time = null;
					time = a.getComplainDate();
					String d = sdf.format(time);
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return accounts;
			}
			return accounts;
		}
		/**
		 * 根据菜单过滤投诉数据
		 * @param menu 菜单
		 * */
		public SmsPage<SwjQuestionBean> findQuestionByMenu(String menu,Pageable page) {
			Page<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec, page);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			
			if(null != alist.getContent() && !alist.getContent().isEmpty()){
				for(SwjQuestion a : alist.getContent()){
					SwjQuestionBean bean = new SwjQuestionBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date startTime = null;
					startTime = a.getComplainDate();
					String d = sdf.format(startTime);
					System.out.println(d+"ddddd");
					//bean.setComplainDate(d);
					//a.getComplainDate();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return null;
			}
			return new SmsPage<SwjQuestionBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}
		/**
		 * 完结操作
		 * @Param id  问题id
		 * */
		public void endQuestion(String id){
			SwjQuestion q = questionDao.findOne(id);
			q.setStatus(5);
			questionDao.save(q);
		}
		/**
		 *是否为越级下发的投诉
		 *@param qId  问题id
		 * */
		public String getIscross(String qId){
			SwjQuestion q = questionDao.findOne(qId);
			String is = q.getIscross();
			if(is != null){
				if(is.equals("0")){
					return "succ";
				}
			}
			return "fail";
		}
		/**
		 * 微信处理投诉过滤
		 * @param status 
		 * */
		public List<SwjQuestionBean> getComplainList(final int status,final String areaname,final String streetname,final String villagename){
			List<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != areaname && !areaname.isEmpty()){
						predicates.add(cb.equal(root.get("areaPersonName").as(String.class), areaname));
					}
					if(null != streetname && !streetname.isEmpty()){
						predicates.add(cb.equal(root.get("streetPersonName").as(String.class), streetname));
					}
					if(null != villagename && !villagename.isEmpty() && !villagename.equals("请选择")){
						predicates.add(cb.equal(root.get("villagePersonName").as(String.class), villagename));
					}
						if(status == 35){
							Predicate p = cb.equal(root.get("status").as(Integer.class), 3);
							Predicate p1 = cb.equal(root.get("status").as(Integer.class), 5);
							predicates.add(cb.or(p,p1));
						}else{
							predicates.add(cb.equal(root.get("status").as(Integer.class), status));
						}
						query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					if(predicates.isEmpty()){
						return null;
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec);
			System.out.println(alist.size()+"size111");
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
				for(SwjQuestion a : alist){
					SwjQuestionBean bean = new SwjQuestionBean();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
				return accounts;
		}

		public List<SwjQuestionBean> findQuestion(final String code,
				final String reachname,
				final String qtype,
				final String startTime,
				final String endTime,
				final String person) {
			List<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != code && !code.isEmpty() && !code.equals("请选择")){
						predicates.add(cb.like(root.get("code").as(String.class), "%"+code+"%"));
					}
					if(null != reachname && !reachname.isEmpty() && !reachname.equals("请选择")){
						predicates.add(cb.like(root.get("reachname").as(String.class), "%"+reachname+"%"));
					}
					if(null != qtype && !qtype.isEmpty() && !qtype.equals("请选择")){
						predicates.add(cb.equal(root.get("questiontype").as(String.class), qtype));
					}
					if(null != person && !person.isEmpty()){
						predicates.add(cb.like(root.get("personname").as(String.class), "%"+person+"%"));
					}
					if(null != startTime && !startTime.isEmpty()){
						SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
						System.out.println(startTime+"<<<<<"+endTime);
						Date startDate = null;
						Date endDate = null;
						if(null != endTime && !endTime.isEmpty()){
							try {
								startDate = sdf.parse(startTime);
								endDate = sdf.parse(endTime);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							try {
								startDate = sdf.parse(startTime);
								endDate = new Date();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						predicates.add(cb.between(root.get("complainDate").as(Date.class),startDate, endDate));
					}
					if(predicates.isEmpty()){
						query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
						return query.getRestriction();
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			
			alist = questionDao.findAll(spec);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			if(alist != null && alist.size()>0){
				for(SwjQuestion a : alist){
					SwjQuestionBean bean = new SwjQuestionBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
					Date time = null;
					time = a.getComplainDate();
					String d = sdf.format(time);
					System.out.println(d+"ddddd");
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return accounts;
			}
			return accounts;
		}
		/**
		 * 到期提醒
		 * */
		public List<SwjQuestionBean> remind(){
			List<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
						predicates.add(cb.equal(root.get("status").as(Integer.class), 2));
					if(predicates.isEmpty()){
						query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
						return query.getRestriction();
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			if(alist != null && alist.size()>0){
				for(SwjQuestion a : alist){
					SwjQuestionBean bean = new SwjQuestionBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
					Date time = null;
					time = a.getComplainDate();
					String d = sdf.format(time);
					System.out.println(d+"ddddd");
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return accounts;
			}
			return accounts;
		}
		/**
		 * 统计河道问题数量
		 * @param area 行政区域
		 * */
		public List<SwjQuestion> getCountByAreaTj(String area,String startTime,String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					cal.add(Calendar.MONTH, -1);
					startdate = cal.getTime();
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				try {
					enddate = format.parse(endTime);
					startdate = format.parse(startTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<SwjQuestion> list = questionDao.getCountByAreaToRiverTj(area, startdate, enddate);
			return list;
		}
		

		private SwjComplainBean changeSwjQuestionToSwjComplainBean_NEW(SwjQuestion question) {
			
			SwjComplainBean bean = new SwjComplainBean();
			
			String type="";
			if(!StringUtils.isBlank(question.getQuestiontype())){
				type="【"+question.getQuestiontype()+"】";
			}
			
			//设置用户数据
//			bean.setLevel(ToFrontWeiXinUserBean.changeScoreToLevel(question.getWeixinUser()==null?"0":question.getWeixinUser().getScore()));
			bean.setName(question.getWeixinUser()==null?"":question.getWeixinUser().getName());
			bean.setPhoto(question.getWeixinUser()==null?"":question.getWeixinUser().getPhotoHref());
			bean.setPhoneNum(question.getWeixinUser()==null?"":question.getWeixinUser().getPhoneNum());
			bean.setSignature(question.getWeixinUser()==null?"":question.getWeixinUser().getSignature());
			bean.setOpenId(question.getWeixinUser()==null?"":question.getWeixinUser().getOpenId());
			bean.setLevel(question.getWeixinUser()==null?"1":ToFrontWeiXinUserBean.changeScoreToLevel(question.getWeixinUser().getScore()));
			
			//设置question表数据
			bean.setComplainDate(DateUtils.changeToTimeDesc_CN(question.getComplainDate()));
			bean.setCoordinate(question.getX()+"|"+question.getY());
			bean.setArea(question.getArea());
			bean.setX(question.getX());
			bean.setY(question.getY());
			bean.setQuestionposition(question.getQuestionposition());
			bean.setQuestionCode(question.getCode());
			bean.setQuestioncontent(type+question.getQuestioncontent());
			bean.setQuestionId(question.getId());
			bean.setQuestiontype(question.getQuestiontype());
			bean.setStatus(question.getStatus()+"");
			bean.setTimeDesc(DateUtils.changeToTimeDesc(question.getComplainDate()));
			bean.setCode(question.getCode());
			bean.setServerWeixinPicId(question.getAttachmentWeixinServiceId());
			
			//设置点赞，转发，关注
			bean.setAttentionCount(question.getAttentionCount()+"");
			bean.setRepostCount(question.getRepostCount()+"");
			bean.setPraiseCount(question.getPraiseCount()+"");
			//TODO
//			bean.setMyAttention(StringUtil.isBlank()?"FALSE":question.getMyAttention());
//			bean.setMyPraise(StringUtil.isBlank(question.getMyPraise())?"FALSE":question.getMyPraise());
//			bean.setMyRepost(StringUtil.isBlank(question.getMyRepost())?"FALSE":question.getMyRepost());
			
			
			//添加投诉的图片
			PhotoListBean photoListBean = new PhotoListBean();
			List<PictureUrlBean>urlBeanList = new ArrayList<>();
			List<String>attach = swjAttachmentDao.findAttachIdByQuestionId(question.getId());
			for(String str:attach){
				PictureUrlBean pictureUrlBean = new PictureUrlBean();
				pictureUrlBean.setLocationId(str);
				urlBeanList.add(pictureUrlBean);
			}
			photoListBean.setList(urlBeanList);
			photoListBean.setListCount(attach==null?0:attach.size());
			bean.setPhotoList(photoListBean);
			
			return bean;
		}
		
		/**
		 * 获取投诉详情专用
		 */
		@Override
		public SwjComplainBean findByQuestionId(String complainId,HttpServletRequest request) {
			SwjQuestion question = questionDao.findById(complainId);
			if(question!=null){
//				List<River>rivers = riverDao.findAllRivers();
//				Map<String,River>riversMap = changeListToMap(rivers);
				SwjComplainBean bean = changeSwjQuestionToSwjComplainBean_NEW(question);
				ServletContext sc = request.getServletContext();
				
				if(StringUtil.isBlank(question.getRiverCode())){
					bean.setArea("");
					bean.setAddress("");
					bean.setRiverName("");
				}else{
					Map<String,Object>map = (Map<String, Object>) sc.getAttribute(River.class.getName());
					River river = (River)map.get(question.getRiverCode());
					bean.setArea(river.getArea());
					bean.setAddress(river.getStart());
					bean.setRiverName(river.getRiverName());
				}
				
				List<SwjQuestionItem>list = swjQuestionItemDao.findByDataIdAndType(complainId, "log");
				List<SwjQuestionItemBean> beanList = new ArrayList<SwjQuestionItemBean>();
				SwjQuestionItemBean quesbean = null; 
				for(SwjQuestionItem item:list){
					quesbean = new SwjQuestionItemBean();
					BeanUtils.copyProperties(item, quesbean);
					beanList.add(quesbean);
				}
				
				bean.setFlowcount(list.size());
				bean.setFlowlist(beanList);
				return bean;
			}else{
				return null;
			}
		}
		
		@Override
		public String save(SwjQuestion question) {
			
			SwjQuestion i= questionDao.save(question);
			i.setCode(generageCode(question.getArea(),new Date()));
			return i.getId();
		}
		
		
		
		/**
		 * 
		 * @param question
		 * @return
		 */
		private SwjComplainBean changeSwjQuestionToSwjComplainBean(SwjQuestion question,Map<String,River>rivers) {
			River question_river = null;
			
			if(!StringUtil.isBlank(question.getRiverCode())){
				question_river = rivers.get(question.getRiverCode());
			}
			
			//TODO
			SwjComplainBean bean = new SwjComplainBean();
			bean.setRivercode(question.getRiverCode());
			bean.setAddress(question_river==null?"":question_river.getStart());
			bean.setRiverName(question_river==null?"":question_river.getRiverName());
			bean.setComplainDate(DateUtils.DateToString_14a(question.getComplainDate()));
			bean.setCoordinate(question.getX()+"|"+question.getY());
			bean.setLevel(ToFrontWeiXinUserBean.changeScoreToLevel(question.getWeixinUser()==null?"0":question.getWeixinUser().getScore()));
			bean.setName(question.getWeixinUser()==null?"":question.getWeixinUser().getName());
			bean.setPhoto(question.getWeixinUser()==null?"":question.getWeixinUser().getPhotoHref());
			bean.setPhoneNum(question.getWeixinUser()==null?"":question.getWeixinUser().getPhoneNum());
			bean.setArea(question.getWeixinUser()==null?"":question.getWeixinUser().getArea());
			bean.setSignature(question.getWeixinUser()==null?"":question.getWeixinUser().getSignature());
			
			bean.setQuestionposition(question.getQuestionposition());
			
			bean.setQuestionCode(question.getCode());
			bean.setQuestioncontent(question.getQuestioncontent());
			bean.setQuestionId(question.getId());
			bean.setQuestiontype(question.getQuestiontype());
//			bean.setRiverCode(question.getRiver().getRiverCode());
			bean.setStatus(question.getStatus()+"");
			bean.setOpenId(question.getWeixinUser()==null?"":question.getWeixinUser().getOpenId());
			bean.setTimeDesc(DateUtils.changeToTimeDesc(question.getComplainDate()));
			bean.setArea(question.getArea());
			bean.setCode(question.getCode());
			PhotoListBean photoListBean = new PhotoListBean();
			
			List<PictureUrlBean>urlBeanList = new ArrayList<>();
			
			List<String>attach = swjAttachmentDao.findAttachIdByQuestionId(question.getId());
			for(String str:attach){
				PictureUrlBean pictureUrlBean = new PictureUrlBean();
				pictureUrlBean.setLocationId(str);
				urlBeanList.add(pictureUrlBean);
			}
			
			photoListBean.setList(urlBeanList);
			photoListBean.setListCount(attach==null?0:attach.size());
			bean.setPhotoList(photoListBean);
			return bean;
		}

		/**
		 * 根据用户ID查询对应等级
		 * @param question
		 * @return
		 */
		@Override
		public List<SysUcenterDepartment> findQuestionByUserId(String userId) {
			if(!StringUtil.isBlank(userId)){
				List<SysUcenterDepartment> list = questionDao.findQuestionByUserId(userId);
				return list;
			}
			return null;
		}
		
		/**
		 * 根据条件查询查问题
		 * @param area 地区
		 * @param type 投诉状态
		 * @param userId 用户ID
		 * */
		public List<SwjQuestionBean> getQuestionByParameter(final String area,final int status,final String userId){
			List<SwjQuestion> alist = null;
			System.out.println(area+","+status+","+userId);
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != area && !area.isEmpty()){
						predicates.add(cb.equal(root.get("area").as(String.class), area));
					}
					if(null != userId && !userId.isEmpty()){
						predicates.add(cb.like(root.get("userId").as(String.class), "%"+userId+"%"));
					}
					if(predicates.isEmpty()){
						return null;
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					return query.getRestriction();
				}
			};
			alist = questionDao.findAll(spec);
			System.out.println(alist.size());
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			if(null != alist && !alist.isEmpty()){
				for(SwjQuestion a : alist){
					SwjQuestionBean bean = new SwjQuestionBean();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}
			return accounts;
		}
		
		/**
		 * 市根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getSCountByParameter(int status,String city) {
			int cou=0;
			if(!StringUtil.isBlank(city)){
				List<Long> list1 = questionDao.getSCountByStatusAndCity(status,city);
					cou = list1.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}	
		
		/**
		 * 区根据条件查询查数量
		 * @param area 地区
		 * @param status 投诉状态
		 * @param roleId 区角色ID
		 * @return 
		 * */
		@Override
		public int getQCountByParameter(int status,String area,String roleId,String city) {
			int cou=0;
			if(!StringUtil.isBlank(area)&&!StringUtil.isBlank(roleId)){
				List<Long> list1 = questionDao.getQCountByAreaAndRoleId(status,area,roleId,city);
					cou = list1.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
		}
		
		/**
		 * 区属职能部门根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getDCountByParameter(int status,String area,String dealmanid,String city) {
			int cou=0;
			if(!StringUtil.isBlank(area)||!StringUtil.isBlank(dealmanid)){
				List<Long> list = questionDao.getDCountByStatusAndCity(status,area,dealmanid,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		/**
		 * 市属职能部门根据条件查询完结数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getCDCountByParameter(int status,String dealmanid,String city) {
			int cou=0;
			if(!StringUtil.isBlank(dealmanid)){
				List<Long> list = questionDao.getCDCountByParameter(status,dealmanid,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		/**
		 * 市属职能部门根据条件查询数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getCDCountByDealmanid(int status,String roleId,String city) {
			int cou=0;
			if(!StringUtil.isBlank(roleId)){
				List<Long> list = questionDao.getCDCountByDealmanid(status,roleId,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		/**
		 * 区属职能部门根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getADCountByParameter(int status,String area,String roleId,String city) {
			int cou=0;
			if(!StringUtil.isBlank(area)||!StringUtil.isBlank(roleId)){
				List<Long> list = questionDao.getADCountByStatusAndCity(status,area,roleId,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		/**
		 * 市、区根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getCountByParameter(int status,String area,String dealmanid,String city) {
			int cou=0;
			if(StringUtil.isBlank(area)){
				List<Long> list1 = questionDao.getCountByStatusAndCity(status,dealmanid,city);
					cou = list1.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				List<Long> list1 = questionDao.getQQListByStatusAndRoleId(status,area,dealmanid,city);
				cou = list1.get(0).intValue();
				System.out.println(cou);
				return cou;
			}
			
		}
		
		
		/**
		 * 镇街根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public int getStreetCountByParameter(int status,String dealmanid) {
			int cou=0;
			if(StringUtils.isBlank(dealmanid)){
				return cou;
			}
			List<Long> list = questionDao.getStreetCountByParameter(status,dealmanid);
				cou = list.get(0).intValue();
				System.out.println(cou);
			return cou;
		}
		
		/**
		 * 镇街根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public int getSStreetCountByParameter(int status,String roleId) {
			int cou=0;
			if(StringUtils.isBlank(roleId)){
				return cou;
			}
			List<Long> list = questionDao.getSStreetCountByParameter(status,roleId);
				cou = list.get(0).intValue();
				System.out.println(cou);
			return cou;
		}
		
		/**
		 * 村居根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public int getVillageCountByParameter(int status,String dealmanid) {
			int cou=0;
			if(StringUtils.isBlank(dealmanid)){
				return cou;
			}
			List<Long> list = questionDao.getVillageCountByParameter(status,dealmanid);
			cou = list.get(0).intValue();
			System.out.println(cou);
			return cou;
		}
		
		/**
		 * 村居根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public int getVVillageCountByParameter(int status,String roleId) {
			int cou=0;
			if(StringUtils.isBlank(roleId)){
				return cou;
			}
			List<Long> list = questionDao.getVVillageCountByParameter(status,roleId);
			cou = list.get(0).intValue();
			System.out.println(cou);
			return cou;
		}
		
		
		/**
		 * 根据question单表所有的条件来查询  分页
		 * @return
		 */
		public SmsPage<SwjQuestionBean> findQuestionByAllCondition(final SwjQuestionBean questionBean,Pageable page) {
			Page<SwjQuestion> alist = null;
			Specification<SwjQuestion> spec = new Specification<SwjQuestion>() {
				//TODO 根据自己需要的条件添加
				public Predicate toPredicate(Root<SwjQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					
					if(StringUtil.isNotBlank(questionBean.getCode()) && !questionBean.getCode().equals("请选择")){
						predicates.add(cb.like(root.get("code").as(String.class), "%"+questionBean.getCode()+"%"));
					}
					if(StringUtil.isNotBlank(questionBean.getReachname()) && !questionBean.getReachname().equals("请选择")){
						predicates.add(cb.like(root.get("reachname").as(String.class), "%"+questionBean.getReachname()+"%"));
					}
					if(StringUtil.isNotBlank(questionBean.getType()) && !questionBean.getType().equals("请选择")){
						predicates.add(cb.equal(root.get("questiontype").as(String.class), questionBean.getType()));
					}
					if(StringUtil.isNotBlank(questionBean.getPersonname())){
						predicates.add(cb.like(root.get("personname").as(String.class), "%"+questionBean.getPersonname()+"%"));
					}
					if(StringUtil.isNotBlank(questionBean.getStreetPersonId())){
						
					}
					
					if(StringUtil.isNotBlank(questionBean.getVillagePersonId())){
						
					}
					if(StringUtil.isNotBlank(questionBean.getArea())){
						
					}
					if(StringUtil.isNotBlank(questionBean.getAreaPersonId())){
						
					}
					
					
					if(StringUtil.isNotBlank(questionBean.getStartTime())){
						System.out.println(questionBean.getStartTime()+"<<<<<"+questionBean.getEndTime());
						Date startDate = null;
						Date endDate = null;
						if(!StringUtil.isBlank(questionBean.getEndTime())){
								startDate = DateUtils.StringToDate_8a(questionBean.getStartTime());
								endDate = DateUtils.StringToDate_8a(questionBean.getEndTime());
						}else{
								startDate = DateUtils.StringToDate_8a(questionBean.getStartTime());
								endDate = new Date();
						}
						predicates.add(cb.between(root.get("complainDate").as(Date.class),startDate, endDate));
					}
					if(predicates.isEmpty()){
						query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
						return query.getRestriction();
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("complainDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			
			alist = questionDao.findAll(spec,page);
			List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			if(null != alist.getContent() && !alist.getContent().isEmpty()){
				for(SwjQuestion a : alist.getContent()){
					SwjQuestionBean bean = new SwjQuestionBean();
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return new SmsPage<SwjQuestionBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
			}
			return new SmsPage<SwjQuestionBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}

		@Override
		public void updatePositionById(String questonposition, String id) {
			questionDao.updatePositionById(questonposition,id);
		}

		@Override
		public void updateUrgency(String complainId, int i) {
				SwjQuestion question = questionDao.findOne(complainId);
				question.setStatus(i);
				question.setUrgencyDate(new Date());
				questionDao.save(question);
		}

		
		public ComplainListJsonBean findQuestionByAllCondition(SwjQuestionBean questionBean,
                Pageable page,
                HttpServletRequest request){
			StringBuffer sql = new StringBuffer();
			sql.append(" select u.`name`,u.photo_href,u.signature,u.open_id,u.score,u.phone_num, ");
			sql.append(" q.complainDate,q.x,q.y,q.area,q.questionposition,q.`code`,q.questioncontent, ");
			sql.append(" q.id,q.questiontype,q.`status`,q.attention_count,q.repost_count,q.praise_count,q.river_code ");
			sql.append(" from swj_question q,swj_weixin_user u WHERE q.openid = u.open_id ");
			sql.append(" and u.`name` is not null and u.photo_href is not null ");
            //area  过滤区  area='天河区' or area="荔湾区"

            if(StringUtil.isNotBlank(questionBean.getArea())){
                String[]areas = questionBean.getArea().split(",");
                sql.append(" and ( ");
                for(String area:areas){
                	area = area.replace("'", "");
                   sql.append(" q.area='"+area+"' or ");
                }
                sql.setLength(sql.length()-4);
                sql.append(")");
            }
            if(StringUtil.isNotBlank(questionBean.getStatusStr())){
                String[]status = questionBean.getStatusStr().split(",");
                sql.append(" and ( ");
                for(String stat:status){
                	stat = stat.replace("'", "");
                	sql.append(" q.`status`='"+stat+"' or ");
                }
                sql.setLength(sql.length()-4);
                sql.append(")");
            }
            
            //投诉问题类型   or  
            if(StringUtil.isNotBlank(questionBean.getQuestiontype())){
                String[]questionTypes = questionBean.getQuestiontype().split(",");
                sql.append(" and ( ");
                for(String type:questionTypes){
                	type = type.replace("'", "");
                	sql.append(" q.questiontype='"+type+"' or ");
                }
                sql.setLength(sql.length()-4);
                sql.append(") ");
            }
            //河涌名字
            if(StringUtil.isNotBlank(questionBean.getReachname())){
            	sql.append(" and q.reachname like '%"+questionBean.getReachname().replace("'", "")+"%' ");
            }
            
            // 是圈子还是自己个人的
            if(StringUtil.isNotBlank(questionBean.getIsMyOwn())){
                if(questionBean.getIsMyOwn().equals("TRUE")){
                	sql.append(" and u.open_id='"+questionBean.getOpenid().replace("'", "")+"' ");
                }else{
                	sql.append(" and q.has_right=0 and q.x is not null and q.y is not null ");
                }
            }
            
            
            if(StringUtils.isNotBlank(questionBean.getStreetPersonId())){
            	sql.append(" and q.street_person_id='"+questionBean.getStreetPersonId().replace("'", "")+"' ");
            }
            if(StringUtils.isNotBlank(questionBean.getVillagePersonId())){
            	sql.append(" and q.village_person_id='"+questionBean.getVillagePersonId().replace("'", "")+"' ");
            }
            if(StringUtils.isNotBlank(questionBean.getAreaPersonId())){
            	sql.append(" and q.area_person_id='"+questionBean.getAreaPersonId().replace("'", "")+"' ");
            }
            if(questionBean.getLastRecordTime()!=0){
            	sql.append(" and q.complainDate<'"+DateUtils.DateToString_14a(new Date(questionBean.getLastRecordTime()))+"' ");
            }
			
            //是否测试数据
            if(CacheDataUtils.isTestAccount(request)){
                //predicates.add(cb.equal(root.get("city").as(String.class), ComplainConstants.TESTDATA));
            }else{
            	sql.append(" and q.city='广州市' ");
            }
            
            //筛选投诉时间
            String start_time = null;
            String end_time = null;
            
            if(StringUtil.isNotBlank(questionBean.getComplainDate())){
                int time_int = Integer.parseInt(questionBean.getComplainDate());
                start_time = DateUtils.DateToString_14a(DateUtils.getDaysAgo_Date(time_int));
                end_time = DateUtils.DateToString_14a(new Date());
            }
            
            if(StringUtils.isNotBlank(start_time)){
            	sql.append(" and q.complainDate>'"+start_time+"' and q.complainDate<'"+end_time+"' ");
            }
            
            sql.append(" order by q.complainDate desc ");
            
            
            System.out.println(sql.toString());
            
			Query query = entityManager.createNativeQuery(sql.toString());
			
			query.setFirstResult((page.getPageNumber()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());
			
			List<Object[]>objs = query.getResultList();
			
            //设置返回页面的数据
            List<SwjComplainBean>complainList = new ArrayList<>();
            //缓存读取river
            Map<String,Object> rivers = (Map<String,Object>)request.getServletContext().getAttribute(River.class.getName());
			
		 	for(Object[] q:objs){
                
                SwjComplainBean bean = changeObjToSwjComplainBean(q);
                
                //设置河道信息
                if(StringUtil.isBlank((String)q[19])){
                    bean.setRivercode("");
                    bean.setAddress("");
                    bean.setRiverName("");
                }else{
                    River river = (River)rivers.get((String)q[19]);
                    bean.setAddress(river.getStart());
                    bean.setRiverName(river.getRiverName());
                    bean.setRivercode(river.getRiverCode());
                }
                if(StringUtils.isBlank(bean.getName())){
                	bean.setName("匿名举报");
                }
                
                complainList.add(bean);
            }
	            
            long lastRecordTime = 0;
            if(objs.size()!=0){
                lastRecordTime = ((Date)objs.get(objs.size()-1)[6]).getTime();
            }
            ComplainListJsonBean jsonBean = new ComplainListJsonBean();
            jsonBean.setLastRecordTime(lastRecordTime);
            jsonBean.setCount(complainList.size());
            jsonBean.setComplainList(complainList);
            return jsonBean;
		}
		

		private SwjComplainBean changeObjToSwjComplainBean(Object[] q) {
			
			SwjComplainBean bean = new SwjComplainBean();
			
			//设置用户数据
			bean.setName((String)q[0]);
			bean.setPhoto((String)q[1]);
			bean.setSignature((String)q[2]);
			bean.setOpenId((String)q[3]);
			bean.setLevel((Integer)q[4]==null?"0":ToFrontWeiXinUserBean.changeScoreToLevel(((Integer)q[4])+""));
			bean.setPhoneNum((String)q[5]);
//			
//			//设置question表数据
			bean.setComplainDate(DateUtils.DateToString_14a((Date)q[6]));
			bean.setCoordinate((String)q[7]+"|"+(String)q[8]);
			bean.setArea((String)q[9]);
			bean.setX((String)q[7]);
			bean.setY((String)q[8]);
			bean.setQuestionposition((String)q[10]);
			bean.setQuestionCode((String)q[11]);
			bean.setQuestioncontent((String)q[12]);
			bean.setQuestionId((String)q[13]);
			bean.setQuestiontype((String)q[14]);
			bean.setStatus(((Integer)q[15]).intValue()+"");
			bean.setTimeDesc(DateUtils.changeToTimeDesc((Date)q[6]));
			bean.setCode((String)q[11]);
//			bean.setServerWeixinPicId(question.getAttachmentWeixinServiceId());
//			
//			//设置点赞，转发，关注
			bean.setAttentionCount(((Integer)q[16]).intValue()+"");
			bean.setRepostCount(((Integer)q[17]).intValue()+"");
			bean.setPraiseCount(((Integer)q[18]).intValue()+"");
			
			//添加投诉的图片
			PhotoListBean photoListBean = new PhotoListBean();
			List<PictureUrlBean>urlBeanList = new ArrayList<>();
			List<String>attach = swjAttachmentDao.findAttachIdByQuestionId((String)q[13]);
			for(String str:attach){
				PictureUrlBean pictureUrlBean = new PictureUrlBean();
				pictureUrlBean.setLocationId(str);
				urlBeanList.add(pictureUrlBean);
			}
			photoListBean.setList(urlBeanList);
			photoListBean.setListCount(attach==null?0:attach.size());
			bean.setPhotoList(photoListBean);
			
			return bean;
		}

		@Override
		public void updateAttentionCnt(String complainId, int addNum) {
			questionDao.updateAttentionCnt(complainId,addNum);
			
		}

		@Override
		public void updatePraiseCnt(String complainId, int addNum) {
			questionDao.updatePraiseCnt(complainId,addNum);
			
		}

		@Override
		public void updateRepostCnt(String complainId, int addNum) {
			questionDao.updateRepostCnt(complainId,addNum);
			
		}

		@Override
		public SwjQuestion findById_(String id) {
			return questionDao.findById(id);
		}
		
		@Override
		public void saveQuestion(SwjQuestion bean){
			questionDao.save(bean);
		}

		
		/**
		 * 市根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getQuesListByParameter(int status,String area,String dealmanid,String city) {
			if(StringUtil.isBlank(area)){
				return questionDao.getQuestionByStatusAndCity(status,dealmanid,city);
			}else{
				return questionDao.getQQListByAreaAndRoleId(status,area,dealmanid,city);
			}
			
		}
		
		/**
		 * 市根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getDQuesListByParameter(int status,String area,String dealmanid,String city) {
			if(!StringUtil.isBlank(area)){
				return questionDao.getDQuestionByStatusAndCity(status,area,dealmanid,city);
			}else{
				return new ArrayList<SwjQuestion>();
			}
			
		}	
		
		/**
		 * 市根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getADQuesListByParameter(int status,String area,String roleId,String city) {
			if(!StringUtil.isBlank(area)){
				return questionDao.getADQuestionByStatusAndCity(status,area,roleId,city);
			}else{
				return new ArrayList<SwjQuestion>();
			}
			
		}
		
		/**
		 * 市根据条件查询查数量
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public List<SwjQuestion>getQuesListByParameter(int status,String city) {
			if(!StringUtil.isBlank(city)){
				return questionDao.getQuesListByStatusAndCity(status,city);
			}else{
				return new ArrayList<SwjQuestion>();
			}
			
		}	
		
		/**
		 * 区根据条件查询查数量
		 * @param area 地区
		 * @param status 投诉状态
		 * @param roleId 区角色ID
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getListByParameter(int status,String area,String roleId,String city) {
			if(!StringUtil.isBlank(area)&&!StringUtil.isBlank(roleId)){
				return questionDao.getQListByAreaAndRoleId(status,area,roleId,city);
			}else{
				return new ArrayList<SwjQuestion>();
			}
		}
		
		/**
		 * 镇街根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getStreetListByParameter(int status,String dealmanid) {
			if(StringUtils.isBlank(dealmanid)){
				return new ArrayList<SwjQuestion>();
			}
			return questionDao.getStreetListByParameter(status,dealmanid);
		}
		
		/**
		 * 镇街根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getSStreetListByParameter(int status,String roleId) {
			if(StringUtils.isBlank(roleId)){
				return new ArrayList<SwjQuestion>();
			}
			return questionDao.getSStreetListByParameter(status,roleId);
		}
		
		/**
		 * 村居根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getVillageListByParameter(int status,String dealmanid) {
			if(StringUtils.isBlank(dealmanid)){
				return new ArrayList<SwjQuestion>();
			}
			return questionDao.getVillageListByParameter(status,dealmanid);
		}
		
		/**
		 * 村居根据条件查询查数量
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> getVVillageListByParameter(int status,String roleId) {
			if(StringUtils.isBlank(roleId)){
				return new ArrayList<SwjQuestion>();
			}
			return questionDao.getVVillageListByParameter(status,roleId);
		}
		
		/**
		 * 根据投诉ID查询备注
		 * @param status 投诉状态
		 * @param streetPersonId 用户ID
		 * @return 
		 * */
		@Override
		public List<SwjQuestion> findRemarkById(String complainId) {
			if(StringUtils.isBlank(complainId)){
				return new ArrayList<SwjQuestion>();
			}
			return questionDao.findRemarkById(complainId);
		}
		
		/**
		 * 查询完结投诉
		 * @param 
		 * @param status 投诉状态
		 * @param city 城市名称
		 * @return 
		 * */
		@Override
		public int getCityEvaluatedCount(String city) {
			int cou=0;
			if(!StringUtil.isBlank(city)){
				List<Long> list = questionDao.getCityEvaluatedCount(city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		@Override
		public int getAreaEvaluatedCount(String area,String roleId,String city){
			int cou=0;
			if(!StringUtil.isBlank(area)&&!StringUtil.isBlank(roleId)){
				List<Long> list = questionDao.getAreaEvaluatedCount(area,roleId,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		@Override
		public List<SwjQuestion> getArea(String startTime, String endTime){
			Date startdate = null;
			Date enddate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					//startdate = cal.getTime();
					endTime=format.format(Calendar.getInstance().getTime());
					startTime=format.format(cal.getTime());
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				/*try {
					//enddate = format.parse(endTime);
					//startdate = format.parse(startTime);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
			}
			
			String sql="select s.area ,ifnull(b.count,0) as cou from swj_area_select as s left join(select area,count(1) as count FROM swj_question sq  where sq.status in ('5','10') and sq.area<>'' and sq.finishDate between '"+startTime+"' and '"+endTime+"' and city='广州市'  GROUP BY sq.area) as b on s.area=b.area";
			Query query = entityManager.createNativeQuery(sql);
			List<SwjQuestion> list = query.getResultList();
			//List<SwjQuestion> list = questionDao.getArea(startdate,enddate);
			
			return list;
		}
		@Override
		public int getStreetEvaluatedCount(String userId) {
			int cou=0;
			if(!StringUtil.isBlank(userId)){
				List<Long> list = questionDao.getStreetEvaluatedCount(userId);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		@Override
		public int getVillageEvaluatedCount(String userId) {
			int cou=0;
			if(!StringUtil.isBlank(userId)){
				List<Long> list = questionDao.getVillageEvaluatedCount(userId);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		@Override
		public int getADEvaluatedCount(String area,String roleId,String city){
			int cou=0;
			if(!StringUtil.isBlank(area)&&!StringUtil.isBlank(roleId)){
				List<Long> list = questionDao.getADEvaluatedCount(area,roleId,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}
		
		@Override
		public int getCDEvaluatedCount(String roleId,String city) {
			int cou=0;
			if(!StringUtil.isBlank(roleId)){
				List<Long> list = questionDao.getCDEvaluatedCount(roleId,city);
					cou = list.get(0).intValue();
					System.out.println(cou);
					return cou;
			}else{
				return cou;
			}
			
		}


		@Override
		public List<Map<Integer, Object>> getAllReplyExcel(String startTime,
				String endTime) {
			List<SwjQuestion> alist = null;
			/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.equals("")){
				enddate = new Date();
				if(startTime == null || startTime.equals("")){
					Calendar cal = Calendar.getInstance();
					//下面的就是把当前日期加一个月
					//cal.add(Calendar.month, -1);
					cal.add(Calendar.YEAR, -1);
					System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
					System.out.println("yesterday is:" + format.format(cal.getTime()));
					//startdate = cal.getTime();
					endTime=format.format(Calendar.getInstance().getTime());
					startTime=format.format(cal.getTime());
				}else{
					try {
						startdate = format.parse(startTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}else{
			}*/
			endTime=DateUtils.StringToDateTime_yMdhms(0)+" 17:00:00";
			startTime=DateUtils.StringToDateTime_yMdhms(7)+" 17:00:00";
			String sql="SELECT IFNULL(s1.cou1,0) AS weekProblem, bb.cou2 AS recoveryRate, aa.cou AS generalProblem, scou.cou1 AS generalRate FROM( ( ( (SELECT s.area AS area, count(1) AS cou FROM waterweixin.swj_question s WHERE ( (s. STATUS NOT IN('-1', '1','6')) AND (s.area <> '') AND (s. STATUS <> '') AND (s.city = '广州市')) GROUP BY s.area ) ) aa LEFT JOIN (SELECT s.area AS area, count(1) AS cou1 FROM waterweixin.swj_question s WHERE ( (s. STATUS NOT IN('-1', '1','6')) AND (s.area <> '') AND (s. STATUS <> '') AND (s.city = '广州市') ) AND ( s.complainDate BETWEEN '"+startTime+"' AND '"+endTime+"' ) GROUP BY s.area ) s1 ON ((s1.area = aa.area)) LEFT JOIN (SELECT s1.area AS area, ifnull(b.count, 0) AS cou1 FROM ( waterweixin.swj_area_SELECT s1 LEFT JOIN (SELECT sq.area AS area, count(1) AS count FROM waterweixin.swj_question sq WHERE ( (sq. STATUS NOT IN('1', '-1','6')) AND (sq.area <> '') AND (sq.city = '广州市') ) AND area_person_id is NOT null GROUP BY sq.area ) b ON ((s1.area = b.area)) ) ) scou ON ((scou.area = aa.area)) ) LEFT JOIN (SELECT s1.area AS area, ifnull(b.count, 0) AS cou2 FROM ( waterweixin.swj_area_SELECT s1 LEFT JOIN (SELECT sq.area AS area, count(1) AS count FROM waterweixin.swj_question sq WHERE ( (sq. STATUS NOT IN('-1', '1')) AND (sq.area <> '') AND ( sq.complainDate BETWEEN '"+startTime+"' AND '"+endTime+"' ) AND (sq.city = '广州市') ) AND sq.area_person_id is NOT NULL GROUP BY sq.area ORDER BY sq.area ) b ON ((s1.area = b.area)) ) ) bb ON ((scou.area = bb.area)) LEFT JOIN (SELECT b1.area AS area, ifnull(b1.count, 0) AS cou2 FROM ( waterweixin.swj_area_SELECT s2 LEFT JOIN (SELECT squ.area AS area, count(1) AS count FROM waterweixin.swj_question squ WHERE ( (squ. STATUS NOT IN('1', '-1')) AND (squ.area <> '') AND (squ.city = '广州市') ) AND ( squ.complainDate BETWEEN '"+startTime+"' AND '"+endTime+"' ) AND area_person_id is NOT null GROUP BY squ.area ) b1 ON ((s2.area = b1.area)) ) ) scou1 ON ((scou1.area = aa.area)) ) ORDER BY aa.area";
			System.out.println(sql);
			Query query = entityManager.createNativeQuery(sql);
			alist = query.getResultList();
			System.out.println(alist.listIterator());
			//List<SwjQuestionBean> accounts = new ArrayList<SwjQuestionBean>();
			 List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer,Object>>();
			for (int i = 0; i < alist.size(); i++) {
				Map<Integer, Object> data = new HashMap<Integer,Object>();
				Object  obj=alist.get(i);
				Object [] str=(Object [])obj;
				if(str[0].toString()!=null){
					data.put(1, Integer.valueOf(str[0].toString()));
				}else{
					data.put(1, 0);
				}
				data.put(2, Integer.valueOf(str[1].toString()));
				data.put(3, Integer.valueOf(str[2].toString()));
				data.put(4, Integer.valueOf(str[3].toString()));
				datalist.add(data);
			}
			return datalist;
		}

		@Override
		public List<SwjQuestionBean> getQuestionLocation() {
			// TODO Auto-generated method stub
			List<SwjQuestion> list = questionDao.getQuestionLocation();
			List<SwjQuestionBean> beanList = new ArrayList<SwjQuestionBean>();
			return null;
		}
		
}
