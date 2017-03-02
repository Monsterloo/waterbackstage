package com.hhh.fund.waterwx.service;

import org.springframework.data.domain.PageRequest;

import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

public interface SwjPublicSignBoardInfoService {

	SmsPage<PublicSignsBoardInfoBean> findTypeAll(PageRequest pr, PublicSignsBoardInfoBean bean);

	PublicSignsBoardInfoBean findById(String id);

}
