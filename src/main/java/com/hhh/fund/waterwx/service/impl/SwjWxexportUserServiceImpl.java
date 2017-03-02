package com.hhh.fund.waterwx.service.impl;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjQuestionDao;
import com.hhh.fund.waterwx.dao.SwjWxexportUserDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.entity.SwjWxfollowuserinfo;
import com.hhh.fund.waterwx.service.SwjWxexportUserService;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;
import com.hhh.weixin.util.FuwuhaoConst;
@Component
@Transactional
public class SwjWxexportUserServiceImpl implements SwjWxexportUserService {

	@Autowired
	private SwjWxexportUserDao exportUserDao;
	@Autowired
	private SwjQuestionDao questionDao;
	
	/**
	 * 查找所有导出用户
	 * */
	@Override
	public SmsPage<SwjWxexportUserBean> findExportUserAll(Pageable page) {
		Page<SwjWxexportUser> alist = null;
		alist = exportUserDao.findAll(null,page);
		List<SwjWxexportUserBean> accounts = new ArrayList<SwjWxexportUserBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(SwjWxexportUser a : alist.getContent()){
				SwjWxexportUserBean bean = new SwjWxexportUserBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return null;
		}
		return new SmsPage<SwjWxexportUserBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}

	@Override
	public void deleteexportUserTable() {
		exportUserDao.deleteAll();
	}

	@Override
	public List<SwjWxexportUser> getAllToExport() {
		List<SwjWxexportUser> list=exportUserDao.getAllToExport();
		return list;
	}

	/**
	 * 导出为txt格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	@Override
	public boolean exportTxt(File file, List<SwjWxexportUser> dataList) {
		boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
            	bw.append(FuwuhaoConst.ServiceNumber_APP_ID+"\r\n");
            	for (SwjWxexportUser bean : dataList) {
            		bw.append(bean.getOpenid()).append("\r\n");
				}
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }

	@Override
	public void changequestiontype() {
		List<SwjWxexportUser> list=exportUserDao.getAllToExport();
		for (SwjWxexportUser bean : list) {
			String questionid = bean.getQuestionid();
			SwjQuestion question = questionDao.findById(questionid);
			question.setType("1");
			question.setExporttime(new Date());
			questionDao.save(question);
		}
		
	}
	
}
