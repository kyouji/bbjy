package com.ynyes.bbjy.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户
 * 
 * 储存所有用户信息
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 用户名
	@Column(nullable = false)
	private String username;

	// 英文名
	@Column
	private String englishName;

	// 状态 1、潜在 2、跟进 3、成交 4、冻结
	@Column
	private Long statusId;

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

	// 手机号码
	@Column
	private String mobile;

	// 电子邮箱
	@Column
	private String email;

	// 生日
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	// 地址
	@Column
	private String address;

	// 通信地址
	@Column
	private String mailingAddress;

	// 公司电话
	@Column
	private String comPhone;

	// 电话
	@Column
	private String homePgone;

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
	@Column(scale = 2)
	private Double income;

	// 职务
	@Column
	private String position;

	// 公司地址
	@Column
	private String comAddress;

	// 来源 1 自拓
	@Column
	private Long roleId;

	// 注册时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registerTime;

	// 最后联系时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	// 添加人
	@Column
	private Long managerId;
	
	@Column
	private String manager;

	// 家庭成员
	@OneToMany
	@JoinColumn(name = "userId")
	private List<TdFamily> familyList;

	// 事项
	@OneToMany
	@JoinColumn(name = "userId")
	private List<TdUserItem> itemLIst;

	// 排序号
	@Column
	private Long sortId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getComPhone() {
		return comPhone;
	}

	public void setComPhone(String comPhone) {
		this.comPhone = comPhone;
	}

	public String getHomePgone() {
		return homePgone;
	}

	public void setHomePgone(String homePgone) {
		this.homePgone = homePgone;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public List<TdFamily> getFamilyList() {
		return familyList;
	}

	public void setFamilyList(List<TdFamily> familyList) {
		this.familyList = familyList;
	}

	public List<TdUserItem> getItemLIst() {
		return itemLIst;
	}

	public void setItemLIst(List<TdUserItem> itemLIst) {
		this.itemLIst = itemLIst;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

}
