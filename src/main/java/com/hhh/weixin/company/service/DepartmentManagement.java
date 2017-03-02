package com.hhh.weixin.company.service;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhh.weixin.company.entity.WeixinDepartment;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;



/**
 * 通讯录部门管理类
 **/
public class DepartmentManagement {
	private static Logger log = LoggerFactory.getLogger(DepartmentManagement.class);
	
	/**
	 * 创建部门
	 * @param accesstoken 
	 * @param name 部门名称。长度限制为1~64个字符
	 * @param parentid 父亲部门id。根部门id为1
	 * */
	public static int Create(String name , int parentid){
		int errCode=0;
		String accesstoken = CommonUtil.getToken();
		String requestUrl=String.format(QiyehaoConst.URL_DEPART_CREATE, accesstoken);
		//需要提交的数据
		String postJson = "{\"name\":\" %s\",\"parentid\": %s}";
		String outputStr=String.format(postJson, name,parentid);
		//创建部门
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "POST", outputStr);
		
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("创建部门成功");
			}else{
				errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("创建部门失败  errorcode:{} errmsg:{}",errCode,errMsg);
			}
		}
		return errCode;
	}
	
	/**
	 * 更新部门
	 * @param id 部门ID
	 * @param name 名称
	 * @return
	 */
	public static int Update(int id , String name){
		int errCode=0;
		String accesstoken = CommonUtil.getToken();
		String requestUrl=String.format(QiyehaoConst.URL_DEPART_UPDATE, accesstoken);
		//需要提交的数据
		String postJson = "{\"id\": %d,\"name\":\"%s\"}";
		String outputStr=String.format(postJson, id, name);
		//创建部门
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "POST", outputStr);
		
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("更新部门成功");
			}else{
				errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("更新部门失败  errorcode:{} errmsg:{}",errCode,errMsg);
			}
		}
		return errCode;
	}
	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	public static int Delete(int id){
		int errCode=0;
		String accesstoken = CommonUtil.getToken();
		String requestUrl=String.format(QiyehaoConst.URL_DEPART_DELETE, accesstoken, id);
		//删除部门
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				log.info("删除部门成功");	
			}else{
				errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("删除部门失败  errorcode:{} errmsg:{}",errCode,errMsg);
			}
		}
		return errCode;
	}
	/**
	 * 获取部门列表
	 * @param id 部门id。获取指定部门及其下的子部门 ,根部门ID为1
	 * @return
	 */
	public static List<WeixinDepartment> GetList(int id){
		List<WeixinDepartment> weixinDepartment=null;
		if(id < 1){
			id = 1;
		}
		String accesstoken = CommonUtil.getToken();
		String requestUrl=String.format(QiyehaoConst.URL_DEPART_LIST, accesstoken, id);
		//获取部门列表
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, "GET", null);	
		if(null!=jsonObject){
			if(0==jsonObject.getInt("errcode")){
				weixinDepartment = new ArrayList<>();
				log.info("获取部门列表成功");
				JSONArray departs = jsonObject.getJSONArray("department");
				for(int i=0; i<departs.size(); i++){
					JSONObject obj = departs.getJSONObject(i);
					WeixinDepartment depart = new WeixinDepartment();
					depart.setId(obj.getInt("id"));
					depart.setName(obj.getString("name"));
					depart.setOrder(obj.getInt("order"));
					depart.setParentid(obj.getInt("parentid"));
					weixinDepartment.add(depart);
				}
			}else{
				weixinDepartment=null;
				int errCode=jsonObject.getInt("errcode");
				String errMsg=jsonObject.getString("errmsg");
				log.error("获取部门列表失败  errorcode:{} errmsg:{}",errCode,errMsg);	
			}
		}
		return weixinDepartment;
	}
	//示例
	public static void main(String[] args) {
		// 调用接口获取凭证
		String token = CommonUtil.getToken();
		if (null != token) {
			// 创建部门
			//int errCode=Create(token.getAccessToken(),"研发中心" ,"1");
			//int errCode=Update(token.getAccessToken(),"3" ,"成都研发中心");
			//int errCode=Delete(token.getAccessToken(),"2");
			
			List<WeixinDepartment> departmentList=GetList(1);
			
//			if(0==errCode){
//				System.out.println("操作成功");
//			}
//			else {
//				System.out.println("操作失败");
//			}
			for(WeixinDepartment department:departmentList){
				System.out.println(String.format("ID: %d Name: %s ParentId: %d", department.getId(),department.getName(),department.getParentid()));
			}
			
		}
	}
}
