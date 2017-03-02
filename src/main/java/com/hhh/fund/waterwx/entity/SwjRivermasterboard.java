package com.hhh.fund.waterwx.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the swj_rivermasterboard database table.
 * 
 */
@Entity
@Table(name="swj_rivermasterboard")
@NamedQuery(name="SwjRivermasterboard.findAll", query="SELECT s FROM SwjRivermasterboard s")
public class SwjRivermasterboard implements Serializable {
	private static final long serialVersionUID = 1L;

	private int allRiverNumber;

	private String area;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;

	private int notSetBoardNumber;

	private int notSetRiverNumber;

	private int oneBoardNumber;

	private int oneRiverNumber;

	private int otherBoardNumber;

	private int otherRiverNumber;

	private int threeBoardNumber;

	private int threeRiverNumber;

	private int twoBoardNumber;

	private int twoRiverNumber;

	public SwjRivermasterboard() {
	}

	public int getAllRiverNumber() {
		return this.allRiverNumber;
	}

	public void setAllRiverNumber(int allRiverNumber) {
		this.allRiverNumber = allRiverNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNotSetBoardNumber() {
		return this.notSetBoardNumber;
	}

	public void setNotSetBoardNumber(int notSetBoardNumber) {
		this.notSetBoardNumber = notSetBoardNumber;
	}

	public int getNotSetRiverNumber() {
		return this.notSetRiverNumber;
	}

	public void setNotSetRiverNumber(int notSetRiverNumber) {
		this.notSetRiverNumber = notSetRiverNumber;
	}

	public int getOneBoardNumber() {
		return this.oneBoardNumber;
	}

	public void setOneBoardNumber(int oneBoardNumber) {
		this.oneBoardNumber = oneBoardNumber;
	}

	public int getOneRiverNumber() {
		return this.oneRiverNumber;
	}

	public void setOneRiverNumber(int oneRiverNumber) {
		this.oneRiverNumber = oneRiverNumber;
	}

	public int getOtherBoardNumber() {
		return this.otherBoardNumber;
	}

	public void setOtherBoardNumber(int otherBoardNumber) {
		this.otherBoardNumber = otherBoardNumber;
	}

	public int getOtherRiverNumber() {
		return this.otherRiverNumber;
	}

	public void setOtherRiverNumber(int otherRiverNumber) {
		this.otherRiverNumber = otherRiverNumber;
	}

	public int getThreeBoardNumber() {
		return this.threeBoardNumber;
	}

	public void setThreeBoardNumber(int threeBoardNumber) {
		this.threeBoardNumber = threeBoardNumber;
	}

	public int getThreeRiverNumber() {
		return this.threeRiverNumber;
	}

	public void setThreeRiverNumber(int threeRiverNumber) {
		this.threeRiverNumber = threeRiverNumber;
	}

	public int getTwoBoardNumber() {
		return this.twoBoardNumber;
	}

	public void setTwoBoardNumber(int twoBoardNumber) {
		this.twoBoardNumber = twoBoardNumber;
	}

	public int getTwoRiverNumber() {
		return this.twoRiverNumber;
	}

	public void setTwoRiverNumber(int twoRiverNumber) {
		this.twoRiverNumber = twoRiverNumber;
	}

}