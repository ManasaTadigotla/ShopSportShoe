package com.sportyshoespvtltd.shopsportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import com.sportyshoespvtltd.shopsportshoes.entity.Product;
import com.sportyshoespvtltd.shopsportshoes.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

	@Query("select c from ProductCategory c where c.name=?1")
	public ProductCategory searchCategoryByName(String categoryName);
	
}
