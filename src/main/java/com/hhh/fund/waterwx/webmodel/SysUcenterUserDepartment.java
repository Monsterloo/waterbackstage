package com.hhh.fund.waterwx.webmodel;

import java.io.Serializable;
import javax.persistence.*;



public class SysUcenterUserDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String accountId;

	private String departId;

	public SysUcenterUserDepartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDepartId() {
		return this.departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

}