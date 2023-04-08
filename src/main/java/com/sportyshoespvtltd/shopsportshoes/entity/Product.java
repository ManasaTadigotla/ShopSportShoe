package com.sportyshoespvtltd.shopsportshoes.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="productdetails")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productName;
	private String brandName;
	private String imageUrl;
	private int size;
	private BigDecimal price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_fk")
	private ProductCategory category;
	
	@OneToOne
	private PurchaseItem purchaseItem;
	
	
	public PurchaseItem getPurchaseItem() {
		return purchaseItem;
	}
	public void setPurchaseItem(PurchaseItem purchaseItem) {
		this.purchaseItem = purchaseItem;
	}
	//private String 
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Long productId, String productName, String brandName, String imageUrl, int size, BigDecimal price,
			ProductCategory category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.brandName = brandName;
		this.imageUrl = imageUrl;
		this.size = size;
		this.price = price;
		this.category = category;
	}
	
	public Product(Long productId, String productName, String brandName, String imageUrl, int size, BigDecimal price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.brandName = brandName;
		this.imageUrl = imageUrl;
		this.size = size;
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", brandName=" + brandName
				+ ", imageUrl=" + imageUrl + ", size=" + size + ", price=" + price + ", category=" + category + "]";
	}
	
}
