package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import com.sportyshoespvtltd.shopsportshoes.entity.Product;

public interface ProductService {
	public Product getProductById(long productId);
	public List<Product> getProductsByCategory(Long categoryId);
	public int Update(Product product);
	public Product save(Product product);
	public int delete(Long productId);
	//public int deleteAllByCategory(Long categoryId);
	public List<Product> getAllProducts();
	public int deleteAll();
	public List<Product> searchProductByProductName(String productName);
	public List<Product> searchProductByBrandName(String brandName);
	
	//public List<Product> getProdcutsCategoryName(String categoryName);
}
