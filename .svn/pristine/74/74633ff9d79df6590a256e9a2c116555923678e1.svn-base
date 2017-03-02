package com.hhh.fund.waterwx.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjRiverMasterCensusDao;
import com.hhh.fund.waterwx.entity.SwjRivermastercensus;
import com.hhh.fund.waterwx.service.SwjRiverMasterCensusService;
import com.hhh.fund.waterwx.webmodel.RiverMasterBoardListJsonBean;
import com.hhh.fund.waterwx.webmodel.RiverMasterCensusListJsonBean;
import com.hhh.fund.waterwx.webmodel.SwjRivermasterboardBean;
import com.hhh.fund.waterwx.webmodel.SwjRivermastercensusBean;

@Component
@Transactional
public class SwjRiverMasterCensusServiceImpl implements
		SwjRiverMasterCensusService {

	@Autowired
	private SwjRiverMasterCensusDao swjRiverMasterCensusDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SwjRivermastercensus swjRivermastercensus) {
		swjRiverMasterCensusDao.save(swjRivermastercensus);
	}

	@Override
	public RiverMasterCensusListJsonBean findByArea(String area) {
		SwjRivermastercensus swjRivermastercensus = swjRiverMasterCensusDao.findByArea(area);
		if(swjRivermastercensus != null){
			SwjRivermastercensusBean bean = new SwjRivermastercensusBean();
			BeanUtils.copyProperties(swjRivermastercensus, bean);
			List<SwjRivermastercensusBean> rivermastercensusList = new ArrayList<SwjRivermastercensusBean>();
			RiverMasterCensusListJsonBean jsonBean = new RiverMasterCensusListJsonBean();
			rivermastercensusList.add(bean);
			jsonBean.setRiverMastercensusList(rivermastercensusList);
			return jsonBean;
		}
		return null;
	}

	@Override
	public RiverMasterCensusListJsonBean findAllRiverMasterBoard() {
		StringBuffer sql = new StringBuffer();

		sql.append(" select s.area ,b.id,ifnull(b.allRiverArea,0) as allRiverArea,ifnull(b.allRiverStreet,0) as allRiverStreet, ");
		sql.append(" ifnull(b.allRiverVillage,0) as allRiverVillage,ifnull(b.oneRiverArea,0) as oneRiverArea, ");
		sql.append(" ifnull(b.oneRiverStreet,0) as oneRiverStreet,ifnull(b.oneRiverVillage,0) as oneRiverVillage, ");
		sql.append(" ifnull(b.twoRiverArea,0) as twoRiverArea,ifnull(b.twoRiverStreet,0) as twoRiverStreet,ifnull(b.twoRiverVillage,0) as twoRiverVillage, ");
		sql.append(" ifnull(b.threeRiverArea,0) as threeRiverArea,ifnull(b.threeRiverStreet,0) as threeRiverStreet,ifnull(b.threeRiverVillage,0) as threeRiverVillage, ");
		sql.append(" ifnull(b.isSetRiverNumber,0) as isSetRiverNumber,ifnull(b.notSetRiverNumber,0) as notSetRiverNumber ");
		sql.append(" from swj_area_select as s left join(select * FROM swj_rivermastercensus GROUP BY area) as b on s.area=b.area order by b.area desc");

		Query query = entityManager.createNativeQuery(sql.toString());

		List<Object[]> objs = query.getResultList();

		// 设置返回页面的数据
		List<SwjRivermastercensusBean> rivermastercensusList = new ArrayList<>();

		for (Object[] q : objs) {
			SwjRivermastercensusBean bean = new SwjRivermastercensusBean();
			bean.setArea((String) q[0]);
			bean.setId((String) q[1]);
			bean.setAllRiverArea(((BigInteger) q[2]).intValue());
			bean.setAllRiverStreet(((BigInteger) q[3]).intValue());
			bean.setAllRiverVillage(((BigInteger) q[4]).intValue());
			bean.setOneRiverArea(((BigInteger) q[5]).intValue());
			bean.setOneRiverStreet(((BigInteger) q[6]).intValue());
			bean.setOneRiverVillage(((BigInteger) q[7]).intValue());
			bean.setTwoRiverArea(((BigInteger) q[8]).intValue());
			bean.setTwoRiverStreet(((BigInteger) q[9]).intValue());
			bean.setTwoRiverVillage(((BigInteger) q[10]).intValue());
			bean.setThreeRiverArea(((BigInteger) q[11]).intValue());
			bean.setThreeRiverStreet(((BigInteger) q[12]).intValue());
			bean.setThreeRiverVillage(((BigInteger) q[13]).intValue());
			bean.setIsSetRiverNumber(((BigInteger) q[14]).intValue());
			bean.setNotSetRiverNumber(((BigInteger) q[15]).intValue());
			rivermastercensusList.add(bean);
		}
		
		RiverMasterCensusListJsonBean jsonBean = new RiverMasterCensusListJsonBean();
        jsonBean.setRiverMastercensusList(rivermastercensusList);

		return jsonBean;
	}

}
