package com.hhh.fund.waterwx.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

public interface SwjOutfallPolluateResourceService {

	void save(OutfallPolluateResourceBean resource);

	SmsPage<OutfallPolluateResourceBean> findAll(PageRequest pr, OutfallPolluateResourceBean bean);

	boolean saveList(List beanList);

	OutfallPolluateResourceBean findById(String id);

}
