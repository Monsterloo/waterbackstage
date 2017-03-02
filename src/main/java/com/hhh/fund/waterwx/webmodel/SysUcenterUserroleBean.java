package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;

import javax.persistence.*;



public class SysUcenterUserroleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String accountid;
	
	@Id
	private String roleid;

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	

}