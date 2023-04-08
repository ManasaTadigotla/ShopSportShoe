package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoespvtltd.shopsportshoes.entity.Product;
import com.sportyshoespvtltd.shopsportshoes.repository.ProductRepository;
import com.sportyshoespvtltd.shopsportshoes.repository.ProductCategoryRepository;

@Service(value="productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	   private ProductRepository productRepo;

		@Autowired
		private ProductCategoryRepository categoryRepo;
		@Override
		public Product getProductById(long productId) {
			
			return productRepo.findById(productId).get();
		}

		@Override
		public List<Product> getProductsByCategory(Long categoryId) {
		  // ProductCategory categoryType= categoryRepo.findById(categoryId).get();
			//Example<Product> example=Example.of(cat);
			return null;
		}

		@Override
		public int Update(Product product) {
			if(productRepo.findById(product.getProductId())!=null)
			{
			productRepo.save(product);
			return 1;
			}
			else
				return -1;
		}

		@Override
		public Product save(Product product) {
			return productRepo.save(product);
			//return 1;
		}

		@Override
		public int delete(Long productId) {
		if(productRepo.findById(productId)==null)
			return -1;
		else
		{
			productRepo.deleteById(productId);
			return 1;
		}
		}

		@Override
		public int deleteAll() {
			productRepo.deleteAll();
			return 1;
		}

		@Override
		public List<Product> getAllProducts() {
			// TODO Auto-generated method stub
			return productRepo.findAll();
		}

		@Override
		public List<Product> searchProductByProductName(String productName) {
			// TODO Auto-generated method stub
			return productRepo.searchProductByProductName(productName);
		}

		@Override
		public List<Product> searchProductByBrandName(String brandName) {
			// TODO Auto-generated method stub
			return productRepo.searchProductByBrandName(brandName);
		}
/*
		@Override
		public List<Product> getProdcutsCategoryName(String categoryName) {
			List<Product> productsAvailable=productRepo.getProdcutsCategorywise();
			if(!productsAvailable.isEmpty())
			{
				List<Product> result = productsAvailable.stream()
					     .filter(item -> item.getCategory().getName().equals(categoryName))
					     .collect(Collectors.toList());
				return result;

			}
			else
				return null;
		}
		*/
		


}
