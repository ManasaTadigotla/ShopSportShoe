package com.sportyshoespvtltd.shopsportshoes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity(name="ProductCategory")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	private String name;
	private String brandName;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products=new ArrayList<>();
	
	//constructors
	public ProductCategory(Long categoryId, String name, String brandName) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.brandName = brandName;
	}
	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getter and setters
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	
}
