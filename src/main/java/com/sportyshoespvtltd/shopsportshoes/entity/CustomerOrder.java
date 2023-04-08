package com.sportyshoespvtltd.shopsportshoes.entity;

import java.math.BigDecimal;
//import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_fk")
	private User user;
	
	@OneToMany(mappedBy = "order")
	private List<PurchaseItem> puchaseItems=new ArrayList<>();
	
	
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	private BigDecimal totalPrice;
	
	public CustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerOrder(Long orderId, Date orderDate, BigDecimal totalPrice) {
		super();
		this.orderId = orderId;
		//this.user = user;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
