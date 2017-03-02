package com.hhh.fund.waterwx.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_ucenter_department database table.
 * 
 */
@Entity
@Table(name="sys_ucenter_department")
@NamedQuery(name="SysUcenterDepartment.findAll", query="SELECT s FROM SysUcenterDepartment s")
public class SysUcenterDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int child;

	private String customerId;

	private String name;

	private String parentId;

	private String path;

	public SysUcenterDepartment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getChild() {
		return this.child;
	}

	public void setChild(int child) {
		this.child = child;
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

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}