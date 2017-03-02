package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the sys_ucenter_userrole database table.
 * 
 */
@Entity
@Table(name="sys_ucenter_userrole")
@NamedQuery(name="SysUcenterUserrole.findAll", query="SELECT s FROM SysUcenterUserrole s")
public class SysUcenterUserrole implements Serializable {
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