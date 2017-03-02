package com.hhh.fund.waterwx.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.util.ComplainConstants;
import com.hhh.fund.waterwx.webmodel.ComplainListJsonBean;

public class InitDataServlet implements Servlet{
	
	@Autowired
	RiverDao riverDao;
	
	@Autowired
	SysUcenterDictDao dictDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("初始化缓存数据start......");
		
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());  
	    
	    List<Object[]>list = riverDao.findAllRiverParams();
	    Map<String,Object>map = changeToMap(list);
	    config.getServletContext().setAttribute(River.class.getName(), map);
	    Map<String,Map<String,Object>>sessionMap = new HashMap<String,Map<String,Object>>();
	    //缓存用户之间交互的信息
	    List<SysUcenterDict> account = dictDao.findByParent("test_account");
	    config.getServletContext().setAttribute("recordUserMessageMap", sessionMap);
	    
	    //测试账号
	    config.getServletContext().setAttribute(ComplainConstants.TESTACCOUNT, account);
	    
	    //防止找不到这个目录报错
	    File file = new File(config.getServletContext().getRealPath("/cache/action"));
	    if(!file.exists()){
	    	file.mkdirs();
	    }
	    
	}

	private Map<String, Object> changeToMap(List<Object[]> list) {
		Map<String,Object>map = new HashMap<String,Object>();
		for(Object[] r:list){
			River river = new River();
			river.setRiverCode((String)r[0]);
			river.setRiverName((String)r[1]);
			river.setArea((String)r[2]);
			river.setStart((String)r[3]);
			String riverCode = (String) r[0];
			map.put(riverCode, river);
		}
		return map;
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
