package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;

@Repository
public interface SwjQuestionItemDao extends SpecificationsRepository<SwjQuestionItem, String> {


	@Query("select item from SwjQuestionItem item,SwjWeiXinUser user where item.openId=user.openId and item.dataId = :dataId and item.status=0 and item.type=:type")
	List<SwjQuestionItem> findByDataIdAndType(@Param("dataId")String complainId,@Param("type")String type);

	@Modifying
	@Query("update SwjQuestionItem set status=:status where dataId=:dataId and openId=:openId")
	void updateStatusByDataIdAndOpenId(@Param("dataId")String dataId, @Param("openId")String openId, @Param("status")String status);
	
	@Query("select item from SwjQuestionItems item where item.dataId = :dataId and item.status=0 and item.type in('remark','followReply') order by item.createTime desc,item.type desc")
	List<SwjQuestionItems> findByDataId(@Param("dataId")String complainId);

	@Query("select item from SwjQuestionItems item where item.dataId = :dataId and item.status=0 and item.type in('remark','followReply') and item.cityAreaType=:type order by item.createTime desc,item.type desc")
	List<SwjQuestionItems> findByDataId(@Param("dataId")String complainId,@Param("type")String type);
	
	/*@Query("select item from SwjQuestionItems item where item.dataId = :dataId and item.status=0 and item.type in('remark','followReply') and item.cityAreaType=:type order by item.createTime desc,item.type desc")
	List<SwjQuestionItems> findById(@Param("dataId")String complainId,@Param("type")String type);
	*/

	
	@Query("select item from SwjQuestionItem item where item.dataId=:dataId and item.openId=:openId and type=:type ")
	List<SwjQuestionItem> findByDataIdAndOpenIdAndType(@Param("dataId")String dataId,@Param("openId")String openId,@Param("type")String type);
	
	@Modifying
	@Query("update SwjQuestionItem set status=:status where id=:id")
	void updateStatusByItemId(@Param("id")String id,@Param("status")int status);

	List<SwjQuestionItem> findByDataIdAndStatusAndType(@Param("dataId")String dataId, @Param("status")int i, @Param("type")String type);

	@Query("select item from SwjQuestionItem item where item.status=:status ")
	List<SwjQuestionItem> findByStatus(@Param("status")int status);
	
	@Query("select item from SwjQuestionItems item,SwjWeiXinUser user where item.openId=user.openId and item.dataId = :dataId and item.status=0 and item.type=:type")
	List<SwjQuestionItems> getReplyList(@Param("dataId")String complainId,@Param("type")String type);

	@Modifying
	@Query("update SwjQuestionItem set status=:status where dataId=:dataId and openId=:openId and type=:type")
	void updateStatusByDataIdAndTypeAndOpenId(@Param("dataId")String complainId, @Param("openId")String openId, @Param("type")String type,  @Param("status")int status);

}
