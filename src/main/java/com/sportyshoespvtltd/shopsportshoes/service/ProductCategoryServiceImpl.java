package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoespvtltd.shopsportshoes.entity.ProductCategory;
import com.sportyshoespvtltd.shopsportshoes.repository.ProductCategoryRepository;

@Service(value = "productCategoryServiceImpl")
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Override
	public ProductCategory getCategoryById(long categoryId) {
		
		return productCategoryRepository.findById(categoryId).orElse(null);
	}

	@Override
	public List<ProductCategory> getAllProductCategories() {
		return productCategoryRepository.findAll();
	}

	@Override
	public int update(ProductCategory productCategory) {
		if(productCategoryRepository.findById(productCategory.getCategoryId())!=null)
		{
			productCategoryRepository.save(productCategory);
		return 1;
		}
		else
			return -1;
		 
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
	
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public int delete(ProductCategory category ) {
		
		if(productCategoryRepository.existsById(category.getCategoryId()))
		{
		   //ProductCategory category= productCategoryRepository.getById(categoryId);
		   productCategoryRepository.delete(category);
		   return 1;
		}
		else
			return -1;
	
	}

	@Override
	public int deleteAll(){
		
		productCategoryRepository.deleteAll();
		return 1;
	}

	@Override
	public int deleteById(Long id) {
	
		//check if the given category present in the database 
		// If present deletes and return 1 otherwise returns 0
		if(!productCategoryRepository.findById(id).isEmpty())
		{
			productCategoryRepository.deleteById(id);
	        return 1;
		}
		else
			return -1;
	}

	@Override
	public ProductCategory searchCategoryByName(String matchingName) {
		// TODO Auto-generated method stub
		return productCategoryRepository.searchCategoryByName(matchingName);
	}


}
