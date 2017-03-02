package com.hhh.fund.waterwx.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.dao.ResponsibilityDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SysUcenterDepartment;
import com.hhh.fund.waterwx.webmodel.ComplainListJsonBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjComplainBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterDepartmentBean;



public interface SwjQuestionService {
	/**
	 * 查找所有问题
	 * */
	public List<SwjQuestion> findAll();
	public SmsPage<SwjQuestionBean> findQuestionAll(Pageable page);
	/**
	 * 按ＩＤ查询问题
	 * @param id
	 * @return
	 */
	public SwjQuestion findByOpenIdAndId(String openId,String id);
	
	
	/**
	 * 微信（我的投诉） 
	 * @param openId 微信编号 page 分页所需参数
	 * @return map
	 * */
	public Map<String,Object> findByOpenid(String openId,Pageable page);
	/**
	 * 拿到问题类型
	 * */
	public List<SwjQuestiontype> getAllQuestionType();
	/**
	 * 拿到河段
	 * */
	public List<Responsibility> getAllRiver();
	/**
	 * 拿到左右岸
	 * @param 河流名称
	 * */
	public List<Responsibility> getCoast(String rivername);
	/**
	 * 拿到河段
	 * @param 河流名称 左右岸
	 * */
	public List<Responsibility> getReach(String rivername,String lr);
	/**
	 * 修改状态
	 * */
	public void updateStatus(String id,int status);
	/**
	 * 退回修改状态
	 * */
	public void updateBackStatus(String id,int status);
	/**
	 * 保存方法
	 * */
	public String save(SwjQuestionBean bean);
	/**
	 * 保存entity
	 * @param question
	 * @return
	 */
	public String save(SwjQuestion question);
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 保存附件
	 * */
	public void saveAttachment(SwjAttachment att);
	public SwjQuestionBean findById(String id);
	public SwjQuestion findQuestionById(String id);
	public List<SwjAttachment> getAttchId(String qId);
	
	/**
	 * 取附件
	 * @param id
	 * @return
	 */
	public SwjAttachment getAttachment(String id);
	public void saveQuestionByWeb(SwjQuestionBean bean);
	
	public List<River> getTj(String startTime,String endTime);
	
	public List<SwjQuestion> getAreaQuestionTj(String startTime,String endTime);
	
	public List<SwjQuestion> getArea(String startTime,String endTime);
	
	/**
	 * 根据河道编号和河段名称查询责任人
	 * @param riverCode 河道编号  partName  河段名称
	 * */
	public List<Responsibility> findByRiverCodeAndPartName(String riverCode,String partName);
	/**
	 * 统计全市问题
	 * */
	public List<SwjQuestion> getAreaQuestion(String startTime,String endTime);
	
	//public Map getQuestionByArea();
	/**
	 * 查询所有地区 统计
	 * */
	public List<River> getAllArea();
	/**
	 * 查询问题类型 统计
	 * */
	public List<SwjQuestiontype> getQuestionTypeByTj();
	/**
	 * 查询已处理的投诉
	 * */
	public List<SwjQuestion> getYcl();
	/**
	 * 查询未处理的投诉
	 * */
	public List<SwjQuestion> getWcl();
	/**
	 * 根据河段名称查找河段编号
	 * */
	public River getRiverByRiverName(String riverName);
	/**
	 * 根据地区查河段
	 * @param area 地区
	 * */
	public List<River> getRiversByArea(String area);
	/**
	 * 根据地区查问题 并统计
	 * @param area 地区
	 * */
	public List<SwjQuestion> getRiversByAreaToTj(String area,String startTime,String endTime);
	/**
	 * 根据河段查问题 并统计
	 * @param rivername 河段名称
	 * */
	public List<SwjQuestion> getRiversByRiverToTj(String rivername);
	/**
	 * 无效投诉保存状态
	 * */
	public void saveWxQuestion(String id,String content);
	/**
	 * 获取已处理的问题 地区分类统计
	 * */
	public List<SwjQuestion> getCountByAreaYcl(String startTime,String endTime);
	/**
	 * 获取已处理的问题 地区分类统计
	 * */
	public List<SwjQuestion> getQuestionCountByAreaYcl(String startTime,String endTime);
	/**
	 * 获取所有的问题 地区分类统计
	 * */
	public List<SwjQuestion> getCountByAreaAll(String startTime,String endTime);
	/**
	 * 获取所有的问题 地区分类统计(不关联河流)
	 * */
	public List<SwjQuestion> getQuestionCountByAreaAll(String startTime,String endTime);
	/**
	 * 获取正在处理的问题 地区分类统计
	 * */
	public List<SwjQuestion> getCountAreaZzcl(String area);
	/**
	 * 获取已处理的问题 地区分类统计
	 * */
	public List<SwjQuestion> getCountAreaYcl(String area);
	/**
	 * 根据地区和河段获取所有问题
	 * */
	public List<SwjQuestion> getTypeByRiverNameAndArea(String riverName,String area,String startTime,String endTime);
	/**
	 * 根据地区和河段和问题类型获取问题
	 * */
	public List<SwjQuestion> getQuestionByRiverNameAndArea(String riverName,String area,String questiontype);
	/**
	 * 获得河道信息
	 * */
	public List<Responsibility> getAllResponsibility(String area,String rivername,String leftRight);
	/**
	 * 获得河道信息
	 * */
	public List<Responsibility> getAllResponseByAreaAndRiverName(String area,String rivername);
	/**
	 * 获得河道信息
	 * */
	public List<Responsibility> getAllResponseByFour(String area,String rivername,String lr,String reach);
	/**
	 * 河道地区查询 河道 模糊查询
	 * 
	 * */
	public List<River> getRiverToWx(String rivername,String area);
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	//public boolean exportCsv(File file, List<SwjQuestionBean> dataList);
	/**
	 * 导出list
	 * */
	public List<SwjQuestionBean> getAllToExport(final String code,final String type,final int status,final String river,final String coast,final String reach,final String startTime,final String endTime,final String area);
	//投诉问题数量统计表(投诉的数量)
	public List<Map<Integer, Object>> getAllToExcel(String startTime,String endTime);
	
	//投诉问题数量统计表(投诉的数量)
	public List<Map<Integer, Object>> getAllReplyExcel(String startTime,String endTime);
	//投诉问题数量统计表(河长的回复率)
	//public List<Map<Integer, Object>> getAllToExcel(String startTime,String endTime);
	
	public Map<String, Object> getTotalToExcel(String startTime,String endTime);
	/**
	 * 查询条件查询投诉
	 * */
	public SmsPage<SwjQuestionBean> findBySearch(final String code,final String type,final int status,final String river,final String coast,final String reach,final String startTime,final String endTime,final String area,final String areaname,final String streetname,final String villagename,final String importance,final String iscross,Pageable page);
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	public boolean exportCsv1(File file, List<SwjQuestionBean> dataList,String content);
	/**
	 * 获取区级，街道，村级河长，姓名及联系电话
	 * */
	public List<Responsibility> getPersonName(String area,String rivername,String lr,String reach);
	/**
	 * 获取街镇河长电话
	 * */
	public List<Responsibility> getTelByStreetName(String streetName);
	/**
	 * 获取村级河长电话
	 * */
	public List<Responsibility> getTelByVillageName(String villageName);
	/**
	 * 获取区级河长电话
	 * */
	public List<Responsibility> getTelByAreaName(String areaName);
	/**
	 * 查询所有的问题，地图显示
	 * @param area 行政区域
	 * @param sTime 开始时间
	 * @param eTime 结束时间
	 * */
	public List<SwjQuestionBean> getAllQuestion(final String area,final Date sdate,final Date edate);
	/**
	 * 完结操作
	 * @Param id  问题id
	 * */
	public void endQuestion(String id);
	/**
	 *是否为越级下发的投诉
	 *@param qId  问题id
	 * */
	public String getIscross(String qId);
	/**
	 * 微信处理投诉过滤
	 * @param status 
	 * */
	public List<SwjQuestionBean> getComplainList(final int status,final String areaname,final String streetname,final String villagename);
	/**
	 * 投诉查询--按编号、河涌名、问题类型、时间、河长查询投诉
	 * */
	public List<SwjQuestionBean> findQuestion(final String code,final String reachname,
			final String qtype,final String startTime,final String endTime,final String person);
	/**
	 * 到期提醒
	 * */
	public List<SwjQuestionBean> remind();
	/**
	 * 获取村级河长
	 * */
	public List<Responsibility> getVillagePersonName(String area,String rivername);
	/**
	 * 统计河道问题数量
	 * @param area 行政区域
	 * */
	public List<SwjQuestion> getCountByAreaTj(String area,String startTime,String endTime);

	
	public SwjComplainBean findByQuestionId(String complainId,HttpServletRequest request);
	
	/**
	 * 根据userId查找出用户所属区域
	 * @param userId
	 * @return 
	 */
	public List<SysUcenterDepartment> findQuestionByUserId(String userId);
	/**
	 * 按条件查询出投诉明细
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestionBean> getQuestionByParameter(final String area,final int status,final String userId);
	
	/**
	 * 按条件查询出投诉数(市)
	 * @param userId
	 * @return 
	 */
	public int getCountByParameter(int status,String area,String dealmanid,String city);
	
	/**
	 * 按条件查询出投诉数(区属职能部门)
	 * @param userId
	 * @return 
	 */
	public int getDCountByParameter(int status,String area,String dealmanid,String city);
	
	/**
	 * 按条件查询出投诉数(区属职能部门)
	 * @param userId
	 * @return 
	 */
	public int getADCountByParameter(int status,String area,String roleId,String city);
	
	/**
	 * 按条件查询出投诉(区属职能部门)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getDQuesListByParameter(int status,String area,String dealmanid,String city);
	
	/**
	 * 按条件查询出投诉(市、区属职能部门)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getADQuesListByParameter(int status,String area,String roleId,String city);
	
	/**
	 * 按条件查询出投诉(市)
	 * @param userId
	 * @return 
	 */
	
	public List<SwjQuestion> getQuesListByParameter(int status,String area,String dealmanid,String city);
	
	
	/**
	 * 按条件查询出投诉数(市)
	 * @param userId
	 * @return 
	 */
	public int getSCountByParameter(int status,String city);
	
	
	/**
	 * 按条件查询出投诉(市)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getQuesListByParameter(int status,String city);
	
	
	/**
	 * 按条件查询出投诉数(区)
	 * @param userId
	 * @return 
	 */
	public int getQCountByParameter(int status,String area,String roleId,String city);
	
	/**
	 * 按条件查询出投诉(区)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getListByParameter(int status,String area,String roleId,String city);
	
	
	/**
	 * 按条件查询出投诉数(镇街)
	 * @param userId
	 * @return 
	 */
	public int getStreetCountByParameter(int status,String dealmanid);
	
	/**
	 * 按条件查询出投诉数(镇街)
	 * @param userId
	 * @return 
	 */
	public int getSStreetCountByParameter(int status,String roleId);
	
	/**
	 * 按条件查询出投诉数(镇街)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getStreetListByParameter(int status,String dealmanid);
	
	/**
	 * 按条件查询出投诉数(镇街)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getSStreetListByParameter(int status,String roleId);
	

	/**
	 * 按条件查询出投诉数(村居)
	 * @param userId
	 * @return 
	 */
	public int getVillageCountByParameter(int i, String dealmanid);
	
	/**
	 * 按条件查询出投诉数(村居)
	 * @param userId
	 * @return 
	 */
	public int getVVillageCountByParameter(int i, String roleId);
	
	/**
	 * 按条件查询出投诉数(村居)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getVillageListByParameter(int i, String dealmanid);
	
	/**
	 * 按条件查询出投诉数(村居)
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> getVVillageListByParameter(int i, String roleId);
	
	/**
	 * 按ID查询出河长备注
	 * @param userId
	 * @return 
	 */
	public List<SwjQuestion> findRemarkById(String complainId);
	
	/**
	 * 点击催办更新状态和催办日期
	 * @param userId
	 * @return 
	 */
	public void updateUrgency(String complainId, int i);
	
	public void updatePositionById(String questonposition, String id);
	
	public void updateAttentionCnt(String complainId,int addNum);
	
	public void updatePraiseCnt(String complainId, int i);
	
	public void updateRepostCnt(String complainId, int i);
	
	public SwjQuestion findById_(String id);
	
	public void saveQuestion(SwjQuestion bean);
	
	public ComplainListJsonBean findQuestionByAllCondition(final SwjQuestionBean questionBean,Pageable page,HttpServletRequest request);
	
	public int getCDCountByParameter(int status, String roleId, String city);
	
	public int getCDCountByDealmanid(int status, String roleId, String city);
	
	/**
	 * 查询已完结投诉
	 * 
	 * 
	 */
	public int getCityEvaluatedCount(String city);
	
	public int getAreaEvaluatedCount(String area,String roleId,String city);
	
	public int getStreetEvaluatedCount(String useriId);
	
	public int getVillageEvaluatedCount(String useriId);
	
	public int getADEvaluatedCount(String area,String roleId,String city);
	
	public int getCDEvaluatedCount(String roleId, String city);
	
	public List<SwjQuestion> findQuestionAllArea();
	
	// 查询所有有效坐标
	public List<SwjQuestionBean> getQuestionLocation();
	
}
