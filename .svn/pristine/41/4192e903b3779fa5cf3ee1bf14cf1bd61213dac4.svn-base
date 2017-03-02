package com.hhh.fund.waterwx.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.entity.SwjUser;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.fund.web.model.UserBean;

@Controller
@RequestMapping("/swjUser")
public class SwjUserController {
	private final static String PAGE_SIZE="7";
	@Autowired
	private SwjUserService userService;
	
	@Autowired
	private UserCenterService ucs;
	
	@RequestMapping(value="/main")
	public String type(){
		return "waterwx/swjUser";
	}
	@RequestMapping(value="/echar")
	public String chars(){
		return "waterwx/userEchar";
	}
	/**
	 * 查询所有的河道
	 * */
	@RequestMapping(value="/searchAllUser")
	public @ResponseBody DataTablesResult<SwjUserBean> searchAlltype(int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize){
		int page = userService.getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		SmsPage<SwjUserBean> records = userService.findUserAll(pr);
		Integer count = null;
		DataTablesResult<SwjUserBean> dtr = new DataTablesResult<SwjUserBean>();
		dtr.setData(records.getContent());
		dtr.setDraw(draw);
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}
	/**
	 * 保存河道
	 * */
	@RequestMapping(value="/saveUser")
	public @ResponseBody String saveWx(SwjUserBean bean){
		SwjUserBean b=userService.save(bean);
		//userService.save(bean);
		if(b!=null){
			return "true";
		}else{
			return "false";
		}
		
	}
	/**
	 * 批量更新用户数据
	 * @throws IOException 
	 * */
	@RequestMapping(value="/updateUserInfo")
	public @ResponseBody void updateUserInfo() throws IOException{
		userService.updateUserInfo();
	}
	/**
	 * 删除河道
	 * */
	@RequestMapping(value = "deleteUser/{id}", method = RequestMethod.GET)
	public @ResponseBody String deleteWx(@PathVariable String id){
		System.out.println(id+"88888");
		userService.delete(id);
		return "true";
	}
	/**
	 * 修改河道，返回数据
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody SwjUserBean findById(String id){
		SwjUserBean bean = userService.findById(id);
		return bean;
	}
	/**
	 * 获取所有行政区域
	 * */
	@RequestMapping(value="/findArea")
	public @ResponseBody List<SysUcenterDict> findArea(){
		List<SysUcenterDict> list = userService.findArea();
		return list;
	}
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	@RequestMapping(value="/exportUser")
	public String exportUser(HttpServletRequest request,SwjUserBean userBean,HttpServletResponse response){
		Date sdate = null, edate = null;
		String area = userBean.getArea();
		String name = userBean.getName();
		List<SwjUserBean> list = userService.getAllToExport(area,name);
		System.out.println(list.size()+"1234");
		Date da= new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(da);
		String path = request.getSession().getServletContext().getRealPath("waterwx/exportUser");// path表示创建文件的路径
		//File file = new File("d:/fenfa/download/export"+date+".csv");
		String fileName = "export"+date+".csv";
		File file = new File(path+"/"+fileName);
		 File parent = file.getParentFile();  
         if (parent != null && !parent.exists()) {  
             parent.mkdirs();  
         }  
         System.out.println(list.size()+"99999");
		boolean is = userService.exportUser(file, list);
		//if(is == true){
			String message = downloadCsv(request,response,fileName);
			return message;
		//}else{
		//	return null;
		//}
	}
	private String downloadCsv(HttpServletRequest request, HttpServletResponse response,  String fileName) {
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			// 获取项目根目录
			String ctxPath = request.getSession().getServletContext().getRealPath("waterwx/exportUser");

			// 获取文件名
			String downLoadPath = ctxPath + "/" + fileName;

			// 获取文件
			File file = new File(downLoadPath);
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
				return "您要下载的资源已被删除！！";
			}
			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错了！请联系管理员";
		}
	}
	/**
	 * 根据角色统计用户
	 * 
	 * */
	@RequestMapping(value="/findUserByRoleTj")
	public @ResponseBody Map<String,Integer> findUserByRoleTj(){
		return userService.findUserByRole();
	}
	
	/**
	 * 查询用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "list/{pageno}/pagesize/{pagesize}", method = RequestMethod.POST)
	public FundPage<UserBean> list(@PathVariable Integer pageno, @PathVariable Integer pagesize, UserBean bean,
			HttpSession session) {
		if (bean == null) {
			bean = new UserBean();
		}
		bean.setCustomerId(StringUtil.getCustomerId(session));
		FundPage<UserBean> page = ucs.findUserAll(bean, new PageRequest(pageno, pagesize));
		return page;
	}
}
