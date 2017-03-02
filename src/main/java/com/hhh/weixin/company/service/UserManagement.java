package com.hhh.weixin.company.service;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhh.weixin.company.entity.WeixinUserInfo;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;

/**
 * 通讯录成员管理类
 **/
public class UserManagement {
	
	private static Logger log = LoggerFactory.getLogger(UserManagement.class);
	
	/**
	 * 创建成员
	 * @param userid 员工UserID。对应管理端的帐号，企业内必须唯一。长度为1~64个字符
	 * @param name 成员名称。长度为1~64个字符
	 * @param departmentId 成员所属部门id列表 格式： "department": [x, y]
	 * @param position 职位信息
	 * @param mobile 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	 * @param gender 性别。gender=0表示男，=1表示女。默认gender=0
	 * @param email 邮箱。长度为0~64个字符。企业内必须唯一
	 * @param weixinid 微信号。企业内必须唯一
	 * */
	public static int Create(String accesstoken , String  userid,String name,String departmentId, String position ,String mobile ,String gender ,String email,String weixinid){
		int errCode=0;
		//拼接请求地址
		String requestUrl = String.format(QiyehaoConst.URL_CREATE_USER, accesstoken);
		//需要提交的数据
		String postJson = "{\"userid\":\"%s\",\"name\":\"%s\",\"department\": [%s]";
		String outputStr=String.format(postJson, userid,name,departmentId);
		if(position != null && !"".equals(position)){
			outputStr += ",\"position\":\""+position+"\"";
		}
		if(mobile != null && !"".equals(mobile)){
			outputStr += ",\"mobile\": \""+mobile+"\"";
		}
		if(gender != null && !"".equals(gender)){
			outputStr += ",\"gender\": \""+gender+"\"";
		}
		if(email != null && !"".equals(email)){
			outputStr += ",\"email\": \""+email+"\"";
		}
		if(weixinid != null && !"".equals(weixinid)){
			outputStr += ",\"weixinid\": \""+weixinid+"\"";
		}
		outputStr +="}";	

		//创建成员
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "POST", outputStr);
		
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("创建成员成功");
				
			}else{
				errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("创建成员失败  errorcode:{} errmsg:{}",errCode,errMsg);	
			}
		}
		return errCode;
	}
	
	/**
	 * 更新成员
	 * @param userid 员工UserID。对应管理端的帐号，企业内必须唯一。长度为1~64个字符
	 * @param name 成员名称。长度为1~64个字符
	 * @param department 成员所属部门id列表 格式： "department": [x]
	 * @param position 职位信息
	 * @param mobile 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	 * @param gender 性别。gender=0表示男，=1表示女。默认gender=0
	 * @param tel 办公电话。长度为0~64个字符
	 * @param email 邮箱。长度为0~64个字符。企业内必须唯一
	 * @param weixinid 微信号。企业内必须唯一
	 * @param enable 启用/禁用成员。1表示启用成员，0表示禁用成员
	 * */
	public static int Update(String accesstoken ,String  userid,String name ,String department, String position ,String mobile ,String gender, String email,String weixinid, int enable){
		int errCode=0;
		//拼接请求地址
		String requestUrl=String.format(QiyehaoConst.URL_UPDATE_USER, accesstoken);
		//需要提交的数据
		String postJson = "{\"userid\":\"%s\"";
		String outputStr=String.format(postJson, userid);
		if(name != null && !"".equals(name)){
			outputStr += ",\"name\":\""+name+"\"";
		}
		if(department != null && !"".equals(department)){
			outputStr += ",\"department\":["+department+"]";
		}
		if(position != null && !"".equals(position)){
			outputStr += ",\"position\":\""+position+"\"";
		}
		if(mobile != null && !"".equals(mobile)){
			outputStr += ",\"mobile\": \""+mobile+"\"";
		}
		if(gender != null && !"".equals(gender)){
			outputStr += ",\"gender\": \""+gender+"\"";
		}
		if(email != null && !"".equals(email)){
			outputStr += ",\"email\": \""+email+"\"";
		}
		if(weixinid != null && !"".equals(weixinid)){
			outputStr += ",\"weixinid\": \""+weixinid+"\"";
		}
		if(enable == 0 || enable == 1){
			outputStr += ",\"enable\": "+enable+"";
		}
		outputStr +="}";	
		//更新成员
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "POST", outputStr);
		
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("更新成员成功");
			}else{
				errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("更新成员失败  errorcode:{} errmsg:{}",errCode,errMsg);
			}
		}
		return errCode;
	}

	/**
	 * 删除成员
	 * @param accesstoken 
	 * @param userid 员工UserID。对应管理端的帐号
	 * 
	 */
	public static int Delete(String accesstoken , String userid){
		int errCode=0;
		//拼接请求地址
		String requestUrl=String.format(QiyehaoConst.URL_DELETE_USER, accesstoken, userid);
		//删除成员
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("删除成员成功");	
			}else{
				errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("删除成员失败  errorcode:{} errmsg:{}",errCode,errMsg);
			}
		}
		return errCode;
	}
	/**
	 * 获取成员
	 * @param accesstoken 
	 * */
	public static WeixinUserInfo GetPerson(String accesstoken,String userId){
		WeixinUserInfo weixinUserList=new WeixinUserInfo();
		//拼接请求地址
		String requestUrl=String.format(QiyehaoConst.URL_GET_PERSON, accesstoken, userId);

		//获取成员
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);	
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("获取成员成功");
				weixinUserList.setUserid(jsonObject.getString("userid"));
				weixinUserList.setName(jsonObject.getString("name"));
				weixinUserList.setDepartment(jsonObject.get("department"));
				System.out.println(jsonObject.get("department"));
			}else{
				
				int errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("获取成员失败  errorcode:{} errmsg:{}",errCode,errMsg);	
			}
		}
		return weixinUserList;
	}
	/**
	 * 获取部门成员
	 * @param accesstoken 
	 * @param department_id 获取的部门id
	 * @param fetch_child 1/0：是否递归获取子部门下面的成员 （可选）
	 * @param status 0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加 （可选）
	 * */
	@SuppressWarnings({"unchecked","deprecation"})
	public static List<WeixinUserInfo> GetGroup(String accesstoken, String departmentid,
			int fetch_child, int status){
		List<WeixinUserInfo> WeixinUserInfo=null;
		//拼接请求地址
		String requestUrl=String.format(QiyehaoConst.URL_GET_GROUP, accesstoken, departmentid, fetch_child, status);;
		//获取成员
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);	
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("获取部门成员成功");
				WeixinUserInfo=JSONArray.toList
						(jsonObject.getJSONArray("userlist"),WeixinUserInfo.class);
			}else{
				WeixinUserInfo=null;
				int errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("获取部门成员失败  errorcode:{} errmsg:{}",errCode,errMsg);	
			}
		}
		return WeixinUserInfo;
	}
	//示例
	public static void main(String[] args) {
		// 调用接口获取凭证
		String token = CommonUtil.getToken();
		// 创建成员
		//int errCode=Update(token.getAccessToken(),"cj00011", "lalal", "研发", "18299999999", "0", "xxx", "lyf6238@126.com", "sssss","1");
//		int errCode=Delete(token.getAccessToken(),"cj00011");
//		if(0==errCode){
//			System.out.println("操作成功");
//		}
//		else {
//			System.out.println("操作失败");
//		}
		//获取部门成员列表
//		List<WeixinUserInfo> personList=GetGroup(token.getAccessToken(),"3");
//		
//		for(WeixinUserInfo department:personList){
//			System.out.println(String.format("ID: %s Name: %s ", department.getUserid(),department.getName()));
//		}
		//获取成员实例
		//获取成员实例
		WeixinUserInfo personList=GetPerson(token,"cj00020");
		Object personDepartment=personList.getDepartment();
		if(personDepartment.toString().equals("[3]")){
			System.out.println("成都研发中心");
		}else if(personDepartment.toString().equals("[4]")){
			System.out.println("广州研发中心");
		}
		System.out.println(String.format("ID: %s Name: %s ", personList.getUserid(),personList.getName()));
	}
}
