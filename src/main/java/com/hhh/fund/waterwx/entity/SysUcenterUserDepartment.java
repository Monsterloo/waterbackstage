package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_ucenter_user_department database table.
 * 
 */
@Entity
@Table(name="sys_ucenter_user_department")
@NamedQuery(name="SysUcenterUserDepartment.findAll", query="SELECT s FROM SysUcenterUserDepartment s")
public class SysUcenterUserDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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