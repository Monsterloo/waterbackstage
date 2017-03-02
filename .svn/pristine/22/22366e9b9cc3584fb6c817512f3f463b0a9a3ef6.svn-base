package com.hhh.fund.waterwx.dao;



import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SysUcenterDepartment;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
@Repository
public interface SwjQuestionDao extends SpecificationsRepository<SwjQuestion, String> {
	
	/**
	 * 按用户的openId 与　投诉id查询
	 * @param openId
	 * @param id
	 * @return
	 */
	public SwjQuestion findByOpenidAndId(String openId,String id);
	//public SmsPage<SwjQuestion> findAll();
	//微信通过openId查找我的投诉
	
	public Page<SwjQuestion> findByOpenidOrderByComplainDateDesc(String openId,Pageable page);
	
	/**
	 * 根据河道编号查找所有投诉信息
	 * */
	public List<SwjQuestion> findByRiverCode(String riverCode);
	/**
	 * 根据问题类型查找所有投诉信息
	 * */
	public List<SwjQuestion> findByQuestiontype(String questiontype);
	
	/**
	 * 查询所有问题
	 * */
	@Query("select questiontype,count(id) as cou from SwjQuestion where questiontype !='' and complainDate between :startdate and :enddate group by questiontype")
	public List<SwjQuestion> getQuestionType(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	
/*	@Query("SELECT riverCode,count(riverCode) as cou from SwjQuestion  GROUP BY riverCode")
	public List<SwjQuestion> getQuestionByArea();*/
	/**
	 * 查询已处理的投诉 按照月份分组
	 * */
	@Query("SELECT DATE_FORMAT(a.answerDate, '%Y-%m') AS month,count(q.id) as cou FROM SwjQuestion q,SwjAnswer a WHERE q.status = 0 AND q.id = a.questionId and DATE_FORMAT(a.answerDate, '%Y') = 2016 GROUP BY DATE_FORMAT(a.answerDate, '%Y-%m') ORDER BY month ")
	public List<SwjQuestion> getYcl();
	/**
	 * 查询未处理的投诉 按照月份分组lazy
	 * */
	@Query("SELECT DATE_FORMAT(complainDate, '%Y-%m') AS month,count(id) AS cou FROM SwjQuestion WHERE status=1 and DATE_FORMAT(complainDate, '%Y') = 2016 GROUP BY DATE_FORMAT(complainDate, '%Y-%m') ORDER BY month ")
	public List<SwjQuestion> getWcl();
	
	/**
	 * 查询此地区的所有问题
	 * */
	@Query("select s.questiontype,count(s.id) as cou from SwjQuestion s,River r where s.riverCode = r.riverCode and r.area=:area and s.complainDate between :startdate and :enddate group by s.questiontype")
	public List<SwjQuestion> getTypeByArea(@Param("area") String area,@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	/**
	 * 查询此地区的所有问题 问题类型分组
	 * */
	@Query("select distinct s.questiontype,count(s.id) as cou from SwjQuestion s,River r where s.riverCode = r.riverCode and r.riverName=:rivername group by s.questiontype")
	public List<SwjQuestion> getTypeByRiverName(@Param("rivername") String rivername);
	
	@Query("select max(substring(code,-5)) as code from SwjQuestion ")
	public String getMaxCode();
	@Query("select count(q.id) as cou,r.area from SwjQuestion q,River r where r.riverCode=q.riverCode and q.status =0 and q.complainDate between :startdate and :enddate group by r.area")
	public List<SwjQuestion> getCountByAreaYcl(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	@Query("SELECT count(s.id) as cou,s.area FROM SwjQuestion s where s.complainDate between :startdate and :enddate and s.status in ('5','10') and s.area<>'' and s.status<>'' group by s.area")
	public List<SwjQuestion> getQuestionCountByAreaYcl(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	@Query("select count(q.id) as cou,r.area from SwjQuestion q,River r where r.riverCode=q.riverCode and q.status !=3 and q.complainDate between :startdate and :enddate group by r.area")
	public List<SwjQuestion> getCountByAreaAll(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	@Query("SELECT count(s.id) as cou,s.area FROM SwjQuestion s where s.complainDate between :startdate and :enddate and s.status not in('-1','1') and s.area<>'' and s.status<>'' group by s.area")
	public List<SwjQuestion> getQuestionCountByAreaAll(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	@Query("select count(q.id) as cou,r.area from SwjQuestion q,River r where r.riverCode=q.riverCode and q.status =2 and r.area=:area")
	public List<SwjQuestion> getCountAreaZzcl(@Param("area") String area);
	@Query("select count(q.id) as cou,r.area from SwjQuestion q,River r where r.riverCode=q.riverCode and q.status =0 and r.area=:area")
	public List<SwjQuestion> getCountAreaYcl(@Param("area") String area);
	/**
	 * 查询此地区河段的所有问题
	 * */
	@Query("select distinct s.questiontype,count(s.id) as cou from SwjQuestion s,River r where s.riverCode = r.riverCode and r.riverName=:rivername and r.area=:area and s.complainDate between :startdate and :enddate group by s.questiontype")
	public List<SwjQuestion> getTypeByRiverNameAndArea(@Param("rivername") String rivername,@Param("area") String area,@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	
	@Query("select  s from SwjQuestion s,River r where s.riverCode = r.riverCode and r.riverName=:rivername and r.area=:area and s.questiontype=:questiontype")
	public List<SwjQuestion> getQuestionByRiverNameAndArea(@Param("rivername") String rivername,@Param("area") String area,@Param("questiontype") String questiontype);
	
	@Query("select  s from SwjQuestion s,River r where s.riverCode = r.riverCode and r.area=:area and s.questiontype=:questiontype")
	public List<SwjQuestion> getQuestionByQuestiontypeAndArea(@Param("area") String area,@Param("questiontype") String questiontype);
	
	@Query("select  s from SwjQuestion s,River r where s.riverCode = r.riverCode and r.riverName=:rivername and s.questiontype=:questiontype")
	public List<SwjQuestion> getQuestionByRiverNameAndQuestiontype(@Param("rivername") String rivername,@Param("questiontype") String questiontype);
	
	/**
	 * 查询所有问题
	 * */
	@Query("select s from SwjQuestion s group by questiontype")
	public List<SwjQuestion> getQuestionTypeWx();
	
	/**
	 * 按用户的投诉id查询
	 * */
	public SwjQuestion findById(String id);
	/**
	 * 统计河道问题数量 
	 * */
	@Query("select s.reachname,count(s.id) as cou from SwjQuestion s,River r where s.riverCode = r.riverCode and r.area=:area and s.complainDate between :startdate and :enddate  and s.city='广州市'  group by r.riverCode")
	public List<SwjQuestion> getCountByAreaToRiverTj(@Param("area") String area,@Param("startdate") Date startdate,@Param("enddate") Date enddate);

	/**
	 * 统计河道问题数量 
	 * */
	@Query("select s from SwjQuestion s where id=:id")
	public SwjQuestionBean findQuestionById(@Param("id") String id);
	
	/**
	 * 查询用户等级 
	 * */
	@Query("SELECT d from SysUcenterDepartment d where d.id=(SELECT b.departId FROM SwjUser a,SysUcenterUserDepartment b where a.userid=b.accountId and a.userid=(SELECT c.userid FROM SwjUser c where c.id=:userid))")
	public List<SysUcenterDepartment> findQuestionByUserId(@Param("userid") String userId);

	/**
	 * 根据地区和状态查询投诉数量(市级)
	 * @param city 
	 * @param roleId 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and city=:city")
	public List<Long> getSCountByStatusAndCity(@Param("status") int status,@Param("city") String city);
	
	/**
	 * 根据地区和状态查询投诉(市级)
	 * @param city 
	 * @param roleId 
	 * */
	@Query("SELECT s from SwjQuestion s where s.status=:status and city=:city")
	public List<SwjQuestion> getQuesListByStatusAndCity(@Param("status") int status,@Param("city") String city);
	
	
	/**
	 * 根据地区、状态和当前处理人查询
	 * @param city 
	 * @param roleId 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and dealmanid=:dealmanid and city=:city")
	public List<Long> getCountByStatusAndCity(@Param("status") int status,@Param("dealmanid") String dealmanid,@Param("city") String city);
	
	/**
	 * 根据地区、状态和当前处理人查询
	 * @param city 
	 * @param city2 
	 * @param roleId 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and area=:area and dealmanid=:dealmanid and city=:city")
	public List<Long> getDCountByStatusAndCity(@Param("status") int status,@Param("area") String area,@Param("dealmanid") String dealmanid,@Param("city") String city);
	
	/**
	 * 根据地区、状态和当前处理人查询
	 * @param city 
	 * @param city2 
	 * @param roleId 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and area=:area and departmentPersonId=:departmentPersonId and city=:city")
	public List<Long> getADCountByStatusAndCity(@Param("status") int status,@Param("area") String area,@Param("departmentPersonId") String departmentPersonId,@Param("city") String city);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and dealmanid=:dealmanid and city=:city")
	public List<Long> getCDCountByDealmanid(@Param("status") int status,@Param("dealmanid") String dealmanid,@Param("city") String city);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and departmentPersonId=:departmentPersonId and city=:city")
	public List<Long> getCDCountByParameter(@Param("status") int status,@Param("departmentPersonId") String departmentPersonId,@Param("city") String city);
	
	/**
	 * 根据地区、状态和当前处理人查询 数据
	 * @param status
	 * @param dealmanid
	 * @param city
	 * @return
	 */
	@Query("SELECT s from SwjQuestion s where s.status=:status and s.dealmanid=:dealmanid and s.city=:city")
	public List<SwjQuestion> getQuestionByStatusAndCity(@Param("status") int status,@Param("dealmanid") String dealmanid,@Param("city") String city);

	

	/**
	 * 根据状态和角色ID查询投诉数量(区级)
	 * @param city 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and s.area=:area and s.areaPersonId=:areaPersonId and city=:city")
	public List<Long> getQCountByAreaAndRoleId(@Param("status") int status,@Param("area") String area,@Param("areaPersonId") String areaPersonId,@Param("city") String city);
	
	/**
	 * 根据状态和角色ID查询投诉(区级)
	 * @param city 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and s.area=:area and s.dealmanid=:dealmanid and s.city=:city")
	public List<Long> getQQListByStatusAndRoleId(@Param("status") int status,@Param("area") String area,@Param("dealmanid") String dealmanid,@Param("city") String city);
	
	/**
	 * 根据状态和角色ID查询投诉(区级)
	 * @param city 
	 * */
	@Query("SELECT s from SwjQuestion s where s.status=:status and s.area=:area and s.areaPersonId=:areaPersonId and s.city=:city")
	public List<SwjQuestion> getQListByAreaAndRoleId(@Param("status") int status,@Param("area") String area,@Param("areaPersonId") String areaPersonId,@Param("city") String city);

	/**
	 * 根据状态和角色ID查询投诉(区级)
	 * @param city 
	 * */
	@Query("SELECT s from SwjQuestion s where s.status=:status and s.area=:area and s.dealmanid=:dealmanid and s.city=:city")
	public List<SwjQuestion> getQQListByAreaAndRoleId(@Param("status") int status,@Param("area") String area,@Param("dealmanid") String dealmanid,@Param("city") String city);
	
	/**
	 * 根据状态和角色ID查询投诉数量(区级)
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and s.area=:area ")
	public List<Long> getQCountByArea(@Param("area") String area,@Param("status") int status);

	/**
	 * 镇街查询问题合计
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and s.dealmanid=:dealmanid")
	public List<Long> getStreetCountByParameter(@Param("status")int status, @Param("dealmanid")String dealmanid);
	
	/**
	 * 镇街查询问题合计
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and s.streetPersonId=:streetPersonId")
	public List<Long> getSStreetCountByParameter(@Param("status")int status, @Param("streetPersonId")String streetPersonId);
	
	/**
	 * 镇街查询问题合计
	 * */
	@Query("SELECT s from SwjQuestion s where s.status=:status and s.dealmanid=:dealmanid")
	public List<SwjQuestion> getStreetListByParameter(@Param("status")int status, @Param("dealmanid")String dealmanid);
	
	/**
	 * 镇街查询问题合计
	 * */
	@Query("SELECT s from SwjQuestion s where s.status=:status and s.streetPersonId=:streetPersonId")
	public List<SwjQuestion> getSStreetListByParameter(@Param("status")int status, @Param("streetPersonId")String streetPersonId);
	
	
	/**
	 * 村居查询问题合计
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.dealmanid=:dealmanid and s.status=:status ")
	public List<Long> getVillageCountByParameter(@Param("status")int status, @Param("dealmanid")String dealmanid);
	
	/**
	 * 村居查询问题合计
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status=:status and s.villagePersonId=:villagePersonId")
	public List<Long> getVVillageCountByParameter(@Param("status")int status, @Param("villagePersonId")String villagePersonId);
	
	/**
	 * 村居查询问题合计
	 * */
	@Query("SELECT s from SwjQuestion s where s.dealmanid=:dealmanid and s.status=:status ")
	public List<SwjQuestion> getVillageListByParameter(@Param("status")int status, @Param("dealmanid")String dealmanid);
	
	/**
	 * 村居查询问题合计
	 * */
	@Query("SELECT s from SwjQuestion s where s.status=:status and s.villagePersonId=:villagePersonId")
	public List<SwjQuestion> getVVillageListByParameter(@Param("status")int status, @Param("villagePersonId")String villagePersonId);
	

	@Modifying
	@Query("update SwjQuestion set questionposition=:questionposition where id=:id ")
	public void updatePositionById(@Param("questionposition")String questonposition, @Param("id")String id);

	@Modifying
	@Query("update SwjQuestion set attentionCount=attentionCount+:addNum where id=:complainId ")
	public void updateAttentionCnt(@Param("complainId")String complainId, @Param("addNum")int addNum);

	@Modifying
	@Query("update SwjQuestion set praiseCount=praiseCount+:addNum where id=:complainId ")
	public void updatePraiseCnt(@Param("complainId")String complainId, @Param("addNum")int addNum);

	@Modifying
	@Query("update SwjQuestion set repostCount=repostCount+:addNum where id=:complainId ")
	public void updateRepostCnt(@Param("complainId")String complainId, @Param("addNum")int addNum);

	@Query("SELECT s from SwjQuestion s where s.area=:area and s.dealmanid=:dealmanid and s.status=:status and city=:city")
	public List<SwjQuestion> getDQuestionByStatusAndCity(@Param("status") int status,@Param("area") String area,@Param("dealmanid") String dealmanid,@Param("city") String city);
	
	@Query("SELECT s from SwjQuestion s where s.area=:area and s.departmentPersonId=:departmentPersonId and s.status=:status and city=:city")
	public List<SwjQuestion> getADQuestionByStatusAndCity(@Param("status") int status,@Param("area") String area,@Param("departmentPersonId") String departmentPersonId,@Param("city") String city);

	@Query("SELECT s.remark from SwjQuestion s where s.id=:complainId")
	public List<SwjQuestion> findRemarkById(@Param("complainId") String complainId);
	
	/**
	 * 查询完结投诉数量
	 * @param city 
	 * @param roleId 
	 * */
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status in('5','10') and city=:city")
	public List<Long> getCityEvaluatedCount(@Param("city") String city);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status in('5','10') and s.area=:area and s.areaPersonId=:areaPersonId and city=:city")
	public List<Long> getAreaEvaluatedCount(@Param("area") String area,@Param("areaPersonId") String areaPersonId,@Param("city") String city);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status in('5','10') and s.streetPersonId=:streetPersonId")
	public List<Long> getStreetEvaluatedCount(@Param("streetPersonId")String streetPersonId);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status in('5','10') and s.villagePersonId=:villagePersonId")
	public List<Long> getVillageEvaluatedCount(@Param("villagePersonId")String villagePersonId);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status in('5','10') and area=:area and departmentPersonId=:departmentPersonId and city=:city")
	public List<Long> getADEvaluatedCount(@Param("area") String area,@Param("departmentPersonId") String departmentPersonId,@Param("city") String city);
	
	@Query("SELECT count(s.id) as cou from SwjQuestion s where s.status in('5','10') and departmentPersonId=:departmentPersonId and city=:city")
	public List<Long> getCDEvaluatedCount(@Param("departmentPersonId") String departmentPersonId,@Param("city") String city);

    //查询各个地区投诉数量
	@Query("SELECT s.area, count(s.id) as cou FROM SwjQuestion s where s.complainDate between :startdate and :enddate and s.status not in('-1','1') and s.area<>'' and s.status<>'' and s.city='广州市' GROUP BY s.area")
	public List<SwjQuestion> getAreaQuestionTj(@Param("startdate") Date startdate,@Param("enddate") Date enddate);

	/**
	 * 查询所有地区
	 * */
	@Query(" select distinct substring(area,1,3) as area from SwjQuestion where area != '' ")
	public List<SwjQuestion> findQuestionAllArea();
	/**
	 * 查询所有问题
	 * */
	@Query("select questiontype,count(id) as cou from SwjQuestion where questiontype !='' and complainDate between :startdate and :enddate group by questiontype")
	public List<SwjQuestion> getQuestionTime(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	
	/**
	 * 查询所有有效坐标
	 * @return
	 */
	@Query("SELECT code,x,y FROM SwjQuestion WHERE x IS NOT NULL AND x !='' AND y IS NOT NULL AND y != '' GROUP BY CODE")
	public List<SwjQuestion> getQuestionLocation();
	
}
