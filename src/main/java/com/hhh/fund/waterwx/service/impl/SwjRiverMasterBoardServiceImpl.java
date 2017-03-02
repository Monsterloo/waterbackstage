package com.hhh.fund.waterwx.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjRiverMasterBoardDao;
import com.hhh.fund.waterwx.entity.SwjRivermasterboard;
import com.hhh.fund.waterwx.service.SwjRiverMasterBoardService;
import com.hhh.fund.waterwx.webmodel.RiverMasterBoardListJsonBean;
import com.hhh.fund.waterwx.webmodel.SwjRivermasterboardBean;


@Component
@Transactional
public class SwjRiverMasterBoardServiceImpl implements SwjRiverMasterBoardService {
	@Autowired
	private SwjRiverMasterBoardDao swjRiverMasterBoardDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SwjRivermasterboard bean) {
		swjRiverMasterBoardDao.save(bean);
	}


	@Override
	public RiverMasterBoardListJsonBean findByArea(String area) {
		SwjRivermasterboard bean1 = swjRiverMasterBoardDao.findByArea(area);
		if(bean1!=null){
			SwjRivermasterboardBean bean = new SwjRivermasterboardBean();
			BeanUtils.copyProperties(bean1, bean);
			List<SwjRivermasterboardBean> rivermasterboardList = new ArrayList<>();
			RiverMasterBoardListJsonBean jsonBean = new RiverMasterBoardListJsonBean();
			rivermasterboardList.add(bean);
	        jsonBean.setRivermasterboardList(rivermasterboardList);
			return jsonBean;
		}else{
			return null;
		}
	}


	@Override
	public RiverMasterBoardListJsonBean findAllRiverMasterBoard() {
		StringBuffer sql = new StringBuffer();
	  	
	  	sql.append(" select s.area ,b.id,ifnull(b.allRiverNumber,0) as allRiverNumber,ifnull(b.oneRiverNumber,0) as oneRiverNumber, ");
	  	sql.append(" ifnull(b.oneBoardNumber,0) as oneBoardNumber,ifnull(b.twoRiverNumber,0) as twoRiverNumber, ");
	  	sql.append(" ifnull(b.twoBoardNumber,0) as twoBoardNumber,ifnull(b.threeRiverNumber,0) as threeRiverNumber, ");
	  	sql.append(" ifnull(b.threeBoardNumber,0) as threeBoardNumber,ifnull(b.otherRiverNumber,0) as otherRiverNumber,ifnull(b.otherBoardNumber,0) as otherBoardNumber, ");
	  	sql.append(" ifnull(b.notSetRiverNumber,0) as notSetRiverNumber,ifnull(b.notSetBoardNumber,0) as notSetBoardNumber ");
	  	sql.append(" from swj_area_select as s left join(select * FROM swj_riverMasterboard GROUP BY area) as b on s.area=b.area order by b.area desc");
	  	
		Query query = entityManager.createNativeQuery(sql.toString());
		
		List<Object[]>objs = query.getResultList();
		
        //设置返回页面的数据
        List<SwjRivermasterboardBean> rivermasterboardList = new ArrayList<>();
		
	 	for(Object[] q:objs){
            
	 		SwjRivermasterboardBean bean = new SwjRivermasterboardBean();
	 		bean.setArea((String)q[0]);
            bean.setId((String)q[1]);
            bean.setAllRiverNumber(((BigInteger)q[2]).intValue());
            bean.setOneRiverNumber(((BigInteger)q[3]).intValue());
            bean.setOneBoardNumber(((BigInteger)q[4]).intValue());
            bean.setTwoRiverNumber(((BigInteger)q[5]).intValue());
            bean.setTwoBoardNumber(((BigInteger)q[6]).intValue());
            bean.setThreeRiverNumber(((BigInteger)q[7]).intValue());
            bean.setThreeBoardNumber(((BigInteger)q[8]).intValue());
            bean.setOtherRiverNumber(((BigInteger)q[9]).intValue());
            bean.setOtherBoardNumber(((BigInteger)q[10]).intValue());
            bean.setNotSetRiverNumber(((BigInteger)q[11]).intValue());
            bean.setNotSetBoardNumber(((BigInteger)q[12]).intValue());
            rivermasterboardList.add(bean);
        }
            
	 	RiverMasterBoardListJsonBean jsonBean = new RiverMasterBoardListJsonBean();
        jsonBean.setRivermasterboardList(rivermasterboardList);
        return jsonBean;
	}
	
	

	
	
}
