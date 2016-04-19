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

@Entity
public class TdOrder {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	// 保单号码
	@Column(unique=true)
	private String orderNumber;
	
	// 保单计划
	@OneToMany
	@JoinColumn(name="orderId")
	private List<TdOrderProduct> productList;
	
	// 被保人
	@Column
	private String assurer;
	
	// 预约日期
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date openDay; 
	
	// 星期
	@Column
	private String weekDay;
	
	// 预约时间
	@Column 
	private String openTime;
	
	@Column 
	private String openTime2;
	
	// 预约顾问
	@Column
	private Long managerId;
	
	@Column
	private String manager;
	
	// 带单顾问
	@Column
	private String orderManager;
	
	// 联系电话
	@Column
	private String phone;
	
	// 邮箱
	@Column
	private String email;
	
	// 人数
	@Column
	private Long number;
	
	// 保单持有人
	@Column
	private String username;
	
	
	// 保单状态
	@Column
	private Long statusId;
	
	// 保单币种 1、RMB  2、港币  3、美金
	@Column
	private Long currency;
	
	// 主约产品
	@Column
	private Long productId;
	
	// 保约名称
	@Column
	private String productTitle;
	
	// 保额
	@Column(scale=2)
	private Double coverage;
	
	// 保费
	@Column(scale=2)
	private Double premium;
	
	// 主约年期
	@Column
	private Long year;
	
	// 缴费方式
	@Column
	private Long paymode;
	
	// 首期保费
	@Column
	private Long paytype;
	
	// 红利分配
	@Column
	private Long share;
	
	// 是否转保
	@Column
	private Boolean reinsurance;
	
	// 说明
	@Column
	private String remark;
	
	
	
	
	
	
	
	
	
	
	
}
