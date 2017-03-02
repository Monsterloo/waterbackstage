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
import com.hhh.fund.waterwx.entity.SwjFinishapply;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SysUcenterDepartment;
import com.hhh.fund.waterwx.webmodel.ComplainListJsonBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjComplainBean;
import com.hhh.fund.waterwx.webmodel.SwjFinishapplyBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterDepartmentBean;



public interface SwjFinishApplyService {
	
	/**
	 * 保存投诉完结申请
	 * */
	public String saveFinishApply(SwjFinishapply bean);
	
	
	public SwjFinishapplyBean findByFinishAyyplyId(String complainId,HttpServletRequest request);
}
