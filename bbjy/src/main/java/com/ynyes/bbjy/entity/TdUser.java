package com.ynyes.bbjy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	// 头像
	@Column
	private String headImageUrl;
	
	// 用户名
	@Column(nullable=false, unique=true)
	private String username;
    
	// 状态
    @Column
    private Long statusId;
    
    //角色：【0】普通用户
    @	Column
    private Long roleId;
    
	// 昵称
    @Column
    private String nickname;
	
	// 密码
	@Column(nullable=false)
	private String password;
	
	// 真实姓名
	@Column
	private String realName;
    
	// 注册时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registerTime;
	
	// 最后登录时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	// 手机号码
	@Column
	private String mobile;
	
	// 电子邮箱
	@Column
	private String email;
    
    // 排序号
    @Column
    private Long sortId;
    
    //编号
    @Column
    private String number;


	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getHeadImageUrl() {
		return headImageUrl;
	}




	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public Long getStatusId() {
		return statusId;
	}




	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}




	public String getNickname() {
		return nickname;
	}




	public void setNickname(String nickname) {
		this.nickname = nickname;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getRealName() {
		return realName;
	}




	public void setRealName(String realName) {
		this.realName = realName;
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




	public Long getSortId() {
		return sortId;
	}




	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}




	public String getNumber() {
		return number;
	}




	public void setNumber(String number) {
		this.number = number;
	}




	public Long getRoleId() {
		return roleId;
	}




	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}




	@Override
	public String toString() {
		return "TdUser [id=" + id + ", lastLoginTime=" + lastLoginTime + "]";
	}
}
