package com.ynyes.bbjy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 家庭成员
 * @author Max
 *
 */
@Entity
public class TdFamily {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	// 关系 1、配偶   2、子女   3、父母  
	@Column
	private Long relation;
	
	// 用户名
	@Column(nullable=false)
	private String username;
    
	// 英文名
	@Column
	private String englishName;
	
	 // 身份证
    @Column
    private String idCard;
    
    // 婚姻状态
    @Column
    private String marital;
    
    // 国籍
    @Column
    private String nationality;
    
    // 性别
    @Column
    private String sex;
    
    // 生日
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    
    // 公司业务性质
    @Column
    private String nature;
    
    // 公司名称
    @Column
    private String company;
    
    // 公作年期
    @Column
    private String workYear;
    
    // 收入
    @Column
    private Double income;
    
    // 职务
    @Column
    private String position;
    
    // 公司地址
    @Column
    private String comAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRelation() {
		return relation;
	}

	public void setRelation(Long relation) {
		this.relation = relation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWorkYear() {
		return workYear;
	}

	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
		
    
    
    
}
