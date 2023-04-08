package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import com.sportyshoespvtltd.shopsportshoes.entity.ProductCategory;

public interface ProductCategoryService {

	public ProductCategory getCategoryById(long categoryId);
	public List<ProductCategory> getAllProductCategories();
	public int update(ProductCategory productCategory);
	public ProductCategory save(ProductCategory productCategory);
	public int delete(ProductCategory productCategory);
	public int deleteById(Long id);
	public int deleteAll();
	public ProductCategory searchCategoryByName(String matchingName);	
}
