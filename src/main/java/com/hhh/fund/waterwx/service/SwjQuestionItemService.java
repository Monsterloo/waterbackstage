package com.hhh.fund.waterwx.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionItemBean;

public interface SwjQuestionItemService {

	void save(SwjQuestionItem item);

	List<SwjQuestionItem> findByDataIdAndType(String complainId, String type);
	
	void cancelPraiseByComplainIdAndOpenId(String complainId, String openId, String string);

	List<SwjQuestionItem> findByDataIdAndOpenIdAndType(String complainId, String openId,
			String type);

	void updateStatusByItemId(String id, int status);
	
	List<SwjQuestionItems> findByDataId(String complainId);
	
	List<SwjQuestionItems> findByDataId(String complainId,String type);

	List<SwjQuestionItem> findByDataIdAndStatusAndType(String complainId, int i, String type);

	List<SwjQuestionItems> getReplyList(String complainId, String string);

	void updateStatusByDataIdAndTypeAndOpenId(String complainId, String openId, String string, int i);

	SmsPage<SwjQuestionItemBean> findQuestionItemByAllCondition(SwjQuestionItemBean questionItemBean,Pageable page);
	
	void saveReply(SwjQuestionItem item);
	
}
