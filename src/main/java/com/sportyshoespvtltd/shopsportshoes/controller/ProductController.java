package com.sportyshoespvtltd.shopsportshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoespvtltd.shopsportshoes.entity.Product;
import com.sportyshoespvtltd.shopsportshoes.entity.ProductCategory;
import com.sportyshoespvtltd.shopsportshoes.service.ProductCategoryService;
import com.sportyshoespvtltd.shopsportshoes.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService categoryService; 
	
	@GetMapping("/products/{categoryId}")
	public List<Product> getProductsByCategoryId(Long categoryId)
	{
		return productService.getProductsByCategory(categoryId);
	}
	@GetMapping({"/admin/product/{id}","/user/product/{id}"})
	public Product getProductById(Long id)
	{
		if(id!=null)
		{
		return productService.getProductById(id);
		}
		else
		{
			return null;
		}
	}
	
	@GetMapping("/user/search/{searchkeyword}")
	public List<Product> searchProduct(String searchkeyword)
	{
		List<Product> result;
		result=productService.searchProductByProductName(searchkeyword);
		if(result.isEmpty() || result==null)
		{
			result=productService.searchProductByBrandName(searchkeyword);
			if(result.isEmpty()|| result==null)
			{
				ProductCategory productCategory= categoryService.searchCategoryByName(searchkeyword);
				if(productCategory==null)
				{
					return null;
				}
				else
				{
					result=productService.getProductsByCategory(productCategory.getCategoryId());
				}
								
			}
		}
		return result;
	}
	
	
	//returns 1,if inserted the product
	
	@PostMapping("/admin/product/add")
	public Product createProduct(@RequestBody Product product)
	{
		return productService.save(product);
		
	}
	//returns 1,is inserted new product
	//returns -1,is product is already exist
	@PutMapping("/admin/product/update")
	public String updateProduct(@RequestBody Product product)
	{
		int success=productService.Update(product);
		if(success==1)
		{
			return "updated";
		}
		else
		{
			return "Already exists";
		}
	}
	
	//If the product exists,it will be deleted and return true
	//else return false
	@DeleteMapping("/admin/product/remove/{productId}")
	public String deleteProductById(@PathVariable Long productId)
	{
		if(productService.getProductById(productId).equals(null))
		{
			return "product doesn't exists";
		}
		int status=productService.delete(productId);
		if(status==1)
			return "deleted";
		else
			return "not deleted";				
	
	}

}
