package com.hhh.fund.waterwx.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

public interface SwjPollutantSourceService {

	void save(PollutantSourceBean source);

	SmsPage<PollutantSourceBean> findAll(PageRequest pr, PollutantSourceBean bean);

	boolean saveList(List beanList);

	PollutantSourceBean findById(String id);

	List<Object[]> getAreaStatisticsList(String sql);

}
