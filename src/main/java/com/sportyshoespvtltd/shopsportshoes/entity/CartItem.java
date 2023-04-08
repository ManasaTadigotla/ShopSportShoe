package com.sportyshoespvtltd.shopsportshoes.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;


public class CartItem {

	//private Long id;
	private Long productId;
	private String producName;
	private BigDecimal rate;
	private int quantity;
	private String imageUrl;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getProducName() {
		return producName;
	}
	public void setProducName(String producName) {
		this.producName = producName;
	}
	
	
}
