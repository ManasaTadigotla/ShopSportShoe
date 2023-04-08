package com.sportyshoespvtltd.shopsportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportyshoespvtltd.shopsportshoes.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from productdetails p where p.productName=?1")
	public List<Product> searchProductByProductName(String productName);
	
	@Query("select p from productdetails p where p.brandName=?1")
	public List<Product> searchProductByBrandName(String breandName);

	
	/*
	@Query("select p.productName,p.size,p.price,c.name from productdetails p join ProductCategory c where p.categoryId=c.catgoryId")
	public List<Product> getProdcutsCategorywise();
*/
}
