package com.sportyshoespvtltd.shopsportshoes.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
public class PurchaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private CustomerOrder order;
	
	//private Product product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_emailid")
	private User purchaseduser;
	
	@OneToOne
	@JoinColumn(name = "product_fk")
	private Product poduct;
	
	public User getPurchaseduser() {
		return purchaseduser;
	}
	public void setPurchaseduser(User purchaseduser) {
		this.purchaseduser = purchaseduser;
	}
	public Product getPoduct() {
		return poduct;
	}
	public void setPoduct(Product poduct) {
		this.poduct = poduct;
	}
	private int quantity;
	private BigDecimal unitprice;
	private BigDecimal totaPrice;
	
	public PurchaseItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseItem(Long id, int quantity, BigDecimal unitprice, BigDecimal totaPrice) {
		super();
		this.id = id;
		//this.order = order;
		//this.purchaseduser = user;
		this.quantity = quantity;
		this.unitprice = unitprice;
		this.totaPrice = totaPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CustomerOrder getOrder() {
		return order;
	}
	public void setOrder(CustomerOrder order) {
		this.order = order;
	}
	public User getUser() {
		return purchaseduser;
	}
	public void setUser(User user) {
		this.purchaseduser = user;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}
	public BigDecimal getTotaPrice() {
		return totaPrice;
	}
	public void setTotaPrice(BigDecimal totaPrice) {
		this.totaPrice = totaPrice;
	}
	
	
}
