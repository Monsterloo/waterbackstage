package com.hhh.fund.waterwx.webmodel;

import java.util.List;

public class ComplainListJsonBean {

	private int count = 0;
	
	private List<SwjComplainBean> ComplainList = null;
	
	private long lastRecordTime;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<SwjComplainBean> getComplainList() {
		return ComplainList;
	}
	public void setComplainList(List<SwjComplainBean> complainList) {
		ComplainList = complainList;
	}
	public long getLastRecordTime() {
		return lastRecordTime;
	}
	public void setLastRecordTime(long lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
	
}
	
	
