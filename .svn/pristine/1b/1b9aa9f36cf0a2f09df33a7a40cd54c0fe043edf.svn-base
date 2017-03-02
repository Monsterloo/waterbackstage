package com.hhh.fund.config;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.service.SwjQuestionItemService;


//https://my.oschina.net/tanweijie/blog/190045
@Configuration
@EnableScheduling
public class SchedulingConfig  implements SchedulingConfigurer {

//	@Autowired
//	private SwjQuestionItemService service;
	
	@Autowired
	RiverDao riverDao;
	
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        
       //   根据自己设置时间来执行定时
        taskRegistrar.addTriggerTask(
            new Runnable() {
                public void run() {
    				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
    				javax.servlet.ServletContext servletContext = webApplicationContext.getServletContext();
    				servletContext.removeAttribute(River.class.getName());
    			    List<Object[]>list = riverDao.findAllRiverParams();
    			    Map<String,Object>map = changeToMap(list);
    			    servletContext.setAttribute(River.class.getName(), map);
    			    System.out.println(".......................");
                }
            },
            new Trigger(){
				@Override
				public Date nextExecutionTime(TriggerContext context) {
					 Calendar nextExecutionTime =  new GregorianCalendar();
					 nextExecutionTime.set(Calendar.SECOND, 59);
					 nextExecutionTime.set(Calendar.MINUTE, 25);
					 nextExecutionTime.set(Calendar.HOUR, 15);
//                     Date lastActualExecutionTime = context.lastActualExecutionTime();
//					 nextExecutionTime.setTime(lastActualExecutionTime==null?new Date():lastActualExecutionTime);
//					 nextExecutionTime.add(Calendar.SECOND,10);
					 //                     nextExecutionTime.set(Calendar.SECOND, 0);
                     return nextExecutionTime.getTime();
				}}
        );
        
        
//        0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
        
        
        
        //根据corn规则来执行定时任务
//        taskRegistrar.addCronTask(new Runnable(){
//			@Override
//			public void run() {
//				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//				javax.servlet.ServletContext servletContext = webApplicationContext.getServletContext();
//				servletContext.removeAttribute(River.class.getName());
//				
//			    List<Object[]>list = riverDao.findAllRiverParams();
//			    Map<String,Object>map = changeToMap(list);
//			    servletContext.setAttribute(River.class.getName(), map);
				
				
				
//			}}, "5/* * * * * ?");//每天23:59:59执行
        
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
	
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskExecutor());
//	}
	
    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(4);
    }

}
