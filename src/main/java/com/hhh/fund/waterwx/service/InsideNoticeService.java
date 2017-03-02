package com.hhh.fund.waterwx.service;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.util.FundPage;
import com.hhh.fund.waterwx.webmodel.InsideNoticeBean;

public interface InsideNoticeService {

	/**
	 * 分页显示内部通知
	 * @param pageable
	 * @return
	 */
	public FundPage<InsideNoticeBean> findNoticeAll(Pageable pageable);
	
	/**
	 * 保存内部通知
	 * @param notice
	 * @return
	 */
	public InsideNoticeBean save(InsideNoticeBean notice);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * 取内部消息
	 * @param id
	 * @return
	 */
	public InsideNoticeBean findById(String id);
}
