package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.service.WaterTaskService;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.weixin.company.mssage.TextBody;
import com.hhh.weixin.company.mssage.TextMessage;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;

/**
 * 系统可设置每级河长处理/回复的期限，如超过期限时间则提醒(微信)
 * @author 3hhjj
 *
 */
@Component
public class WaterTaskJob implements WaterTaskService{
	Logger log = LoggerFactory.getLogger(WaterTaskJob.class);
	
	@Autowired
	private SwjQuestionService questService;
	@Autowired
	private SwjUserService userService;
	
	@Scheduled(cron="0 0 8 * * ?")  
	public void timeLimitRemind() {  
        log.info("运行每级河长处理/回复超期提醒任务...");
        List<SwjQuestionBean> list = questService.remind();
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.add(Calendar.HOUR_OF_DAY, -8);
        for(SwjQuestionBean bean : list){
        	remind(cal.getTimeInMillis(), bean);
        }
    }
	
	private void remind(long nowtime, SwjQuestionBean bean){
		if(bean.getAreaLimitDate().getTime() <= nowtime && 
				(bean.getStreetPersonName() == null || "".equals(bean.getStreetPersonName()))){
			send(bean.getAreaPersonName(), bean.getCode());
    	}else if(bean.getStreetLimitDate().getTime() <= nowtime && 
    			(bean.getVillagePersonName() == null || "".equals(bean.getVillagePersonName()))){
    		send(bean.getStreetPersonName(), bean.getCode());
    	}else if(bean.getVillageLimitDate().getTime() <= nowtime){
    		send(bean.getVillagePersonName(), bean.getCode());
    	}
	}
	
	private void send(String name, String code){
		SwjUserBean user = userService.findByName(name);
		TextMessage msg = new TextMessage();
		msg.setAgentid(QiyehaoConst.AGENTID_WATER);
		List<String> toUser = new ArrayList<>();
		toUser.add(user.getUserid());
		msg.setTouser(toUser);
		msg.setSafe(0);
		TextBody text = new TextBody();
		String str ="您有编号："+code+"的投诉，已超期未处理，请尽快处理。";
		text.setContent(str);
		CommonUtil.sendMessage(msg);
	}
}
