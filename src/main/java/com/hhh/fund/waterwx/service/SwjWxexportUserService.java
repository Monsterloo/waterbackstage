package com.hhh.fund.waterwx.service;

import java.io.File;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;

public interface SwjWxexportUserService {
	
	/**
	 * 查找所有问题
	 * */
	public SmsPage<SwjWxexportUserBean> findExportUserAll(Pageable page);

	/**
	 * 清空表
	 * */
	public void deleteexportUserTable();
	
	/**
	 * 导出数据查找
	 * */
	public List<SwjWxexportUser> getAllToExport();
	
	/**
	 * 导出txt
	 * */
	public boolean exportTxt(File file, List<SwjWxexportUser> list);
	/**
	 * 改变投诉表状态
	 * */
	public void changequestiontype();
}
