package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_ucenter_dict database table.
 * 
 */
@Entity
@Table(name="sys_ucenter_dict")
@NamedQuery(name="SysUcenterDict.findAll", query="SELECT s FROM SysUcenterDict s")
public class SysUcenterDict implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String category;

	private String code;

	private String customerId;

	private String name;

	private String parent;

	private int state;

	public SysUcenterDict() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

}