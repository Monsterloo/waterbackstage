package com.hhh.fund.waterwx.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.waterwx.entity.NoticeConsume;
import com.hhh.fund.waterwx.service.InsideNoticeService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.webmodel.InsideNoticeBean;
import com.hhh.fund.waterwx.webmodel.NoticeConsumeBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.weixin.company.mssage.TextBody;
import com.hhh.weixin.company.mssage.TextMessage;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/insidenotice")
public class InsideNoticeController {

	@Autowired
	private InsideNoticeService noticeService;
	
	@Autowired
	private SwjUserService userSerivce;
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 分页显示内部通知
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}", method=RequestMethod.GET)
	public FundPage<InsideNoticeBean> list(@PathVariable int pageno, @PathVariable int pagesize){
		Pageable page = new PageRequest(pageno, pagesize);
		return noticeService.findNoticeAll(page);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(InsideNoticeBean bean, String userrole, HttpSession session){
		JSONArray jarray = JSONArray.fromObject(userrole);
		Set<NoticeConsumeBean> sonsumes = new HashSet<>();
		for(int i=0; i<jarray.size(); i++){
			JSONObject json = jarray.getJSONObject(i);
			NoticeConsumeBean consume = new NoticeConsumeBean();
			consume.setCid(json.getString("cid"));
			consume.setName(json.getString("name"));
			consume.setCtype(Short.valueOf(json.getString("ctype")));
			sonsumes.add(consume);
		}
		bean.setSonsumes(sonsumes);
		String userId = (String)session.getAttribute(QiyehaoConst.Weixin_UserId);
		if(userId != null && !"".equals(userId)){
			SwjUserBean userbean = userSerivce.findByUserId(userId);
			if(userbean != null)
				bean.setAuthor(userbean.getName());
		}
		bean.setStatus("1");//新增状态
		noticeService.save(bean);
		return "true";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable String id){
		noticeService.deleteById(id);
		return "true";
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public InsideNoticeBean get(@PathVariable String id){
		return noticeService.findById(id);
	}
	
	@RequestMapping(value="/send/{id}", method=RequestMethod.GET)
	public String send(@PathVariable String id){
		InsideNoticeBean bean = noticeService.findById(id);
		List<String> list = new ArrayList<>();
		for(NoticeConsumeBean nc : bean.getSonsumes()){
			if(nc.getCtype() == 1)
				list.add(nc.getCid());
			else if(nc.getCtype() == 2){
				RoleBean role = ucs.findRleById(nc.getCid());
				for(UserBean u : role.getUsers()){
					list.add(u.getUserId());
				}
			}
		}
		TextMessage textmsg = new TextMessage();
		textmsg.setAgentid(QiyehaoConst.AGENTID_WATER);
		textmsg.setTouser(list);
		textmsg.setSafe(0);
		TextBody body = new TextBody();
		String strmsg = bean.getTitle()+"\n";
		strmsg += bean.getContent();
		body.setContent(strmsg);
		textmsg.setText(body);
		CommonUtil.sendMessage(textmsg);
		bean.setStatus("2");//发送
		noticeService.save(bean);
		return "true";
	}
	
	/**
	 * 查询所有用户
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value="/listuser/{pageno}/pagesize/{pagesize}", method=RequestMethod.GET)
	public SmsPage<SwjUserBean> listuser(@PathVariable int pageno, @PathVariable int pagesize){
		Pageable page = new PageRequest(pageno, pagesize);
		return userSerivce.findUserAll(page);
	}
}
