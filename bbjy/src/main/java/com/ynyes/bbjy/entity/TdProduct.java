package com.ynyes.bbjy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 产品
 * @author Max
 *
 */

@Entity
public class TdProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String title;
	
	@Column(scale=2)
	private Double firstYear;
	
	@Column(scale=2)
	private Double second;
	
	@Column(scale=2)
	private Double third;
	
	@Column(scale=2)
	private Double fourth;
	
	@Column(scale=2)
	private Double fifth;
	
	@Column(scale=2)
	private Double sixth;
	
	@Column(scale=2)
	private Double seventh;
	
	@Column
	private Long sortId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(Double firstYear) {
		this.firstYear = firstYear;
	}

	public Double getSecond() {
		return second;
	}

	public void setSecond(Double second) {
		this.second = second;
	}

	public Double getThird() {
		return third;
	}

	public void setThird(Double third) {
		this.third = third;
	}

	public Double getFourth() {
		return fourth;
	}

	public void setFourth(Double fourth) {
		this.fourth = fourth;
	}

	public Double getFifth() {
		return fifth;
	}

	public void setFifth(Double fifth) {
		this.fifth = fifth;
	}

	public Double getSixth() {
		return sixth;
	}

	public void setSixth(Double sixth) {
		this.sixth = sixth;
	}

	public Double getSeventh() {
		return seventh;
	}

	public void setSeventh(Double seventh) {
		this.seventh = seventh;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	
	
	
}
