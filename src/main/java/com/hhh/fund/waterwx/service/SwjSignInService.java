package com.hhh.fund.waterwx.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjWxsignin;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.weixin.util.WeixinSave;



public interface SwjSignInService{

	/**
	 * 保存方法
	 * */
	public String save(SwjWxsigninBean bean);
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 保存附件
	 * */
	public void saveAttachment(SwjAttachment att);
	public SwjWxsigninBean findById(String id);
	public List<SwjAttachment> getAttchId(String qId);
	
	/**
	 * 取附件
	 * @param id
	 * @return
	 */
	public SwjAttachment getAttachment(String id);
	public void saveQuestionByWeb(SwjWxsigninBean bean);
	
	/**
	 * 按条件查询签到
	 * */
	public List<SwjWxsigninBean> findSignIn(final String reachname,final String grade,
			final String area);
	/**
	 * 根据userid取用户表人员姓名
	 * @param id
	 * @return
	 */
	public String findUserNameByuserId(String userId);
	/**
	 * 查询签到pc端
	 * */
	public SmsPage<SwjWxsigninBean> findSignInByWeb(final String reachname,final String grade,
			final String area,Pageable page);
	
	/**
	 * 微信下载图片文件
	 * 
	 * @param accessToken
	 *            访问凭证
	 * @param url
	 *            微信下载文件url
	 * @param mediaIds
	 *            下载文件ID
	 * @param load
	 *            保存文件接口
	 */
	public void signInImgDownload(final String[] mediaIds,final String id,
			final SwjSignInService signInService);
	
}
