package com.sportyshoespvtltd.shopsportshoes.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoespvtltd.shopsportshoes.entity.CustomerOrder;
import com.sportyshoespvtltd.shopsportshoes.entity.Product;
import com.sportyshoespvtltd.shopsportshoes.entity.ProductCategory;
import com.sportyshoespvtltd.shopsportshoes.entity.PurchaseItem;
import com.sportyshoespvtltd.shopsportshoes.entity.User;
import com.sportyshoespvtltd.shopsportshoes.repository.PurchaseItemRepository;
import com.sportyshoespvtltd.shopsportshoes.service.CustomerOrderService;
import com.sportyshoespvtltd.shopsportshoes.service.ProductCategoryService;
import com.sportyshoespvtltd.shopsportshoes.service.ProductService;
import com.sportyshoespvtltd.shopsportshoes.service.PurchaseItemService;
import com.sportyshoespvtltd.shopsportshoes.service.UserService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@Autowired
	CustomerOrderService customerOrderService;
	
	
	@GetMapping("/admin/customerlist")
	public List<User> listOfRegisteredUsers() {
		List<User> customerList = userService.findAllUsers();
		//List<User> customerList = userService.findAllUsers().stream()
			//	.filter(user -> user.getDiscriminatorValue().equals("Customer")).collect(Collectors.toList());

				return customerList;
	}
	
	@GetMapping("/admin/productlist")
	public List<Product> getAllProducts()
	{
		return productService.getAllProducts();
	}
	
	
	@GetMapping({"/admin/productcategorylist","/user/productcategorylist"})
	public List<ProductCategory> getAllProductCategories()
	{
		return categoryService.getAllProductCategories();
	} 
	
	@GetMapping("/admin/allorders")
	public List<CustomerOrder> getAllOrders()
	{
	  return customerOrderService.getAllOrders();
		
	}
	
	@PostMapping("/admin/category/add")
	public ProductCategory createCategory(@RequestBody ProductCategory category)
	{
		return categoryService.save(category);
	}
	
	//returns 1,is inserted new product
		//returns -1,is product is already exist
		@PutMapping("admin/productcategory/update")
		public String updateProductCategory(@RequestBody ProductCategory productCategory)
		{
			int success=categoryService.update(productCategory);
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
		@DeleteMapping("admin/productcategory/remove")
		public String deleteProductCategory(@RequestBody ProductCategory productCategory )
		{
			int status=categoryService.delete(productCategory);
			if(status==1)
				return "deleted";
			else
				return "Not exists";				
		
		}
		
		

}
