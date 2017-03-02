package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_ucenter_role database table.
 * 
 */
@Entity
@Table(name="sys_ucenter_role")
@NamedQuery(name="SysUcenterRole.findAll", query="SELECT s FROM SysUcenterRole s")
public class SysUcenterRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String customerId;

	private String desp;

	private String name;

	public SysUcenterRole() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}