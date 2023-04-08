package com.sportyshoespvtltd.shopsportshoes.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoespvtltd.shopsportshoes.ShopsportshoesApplication;
import com.sportyshoespvtltd.shopsportshoes.entity.CustomerOrder;
import com.sportyshoespvtltd.shopsportshoes.entity.Product;
import com.sportyshoespvtltd.shopsportshoes.entity.ProductCategory;
import com.sportyshoespvtltd.shopsportshoes.entity.User;
import com.sportyshoespvtltd.shopsportshoes.service.CustomerOrderService;
import com.sportyshoespvtltd.shopsportshoes.service.ProductService;
import com.sportyshoespvtltd.shopsportshoes.service.UserService;

@RestController
public class UserController {

	 private static final Logger logger = Logger.getLogger(UserController.class.toString());
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	CustomerOrderService customerOrderService;
	
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		
		// List<User> u1= userService.findAllUsers();
		return  userService.findAllUsers();
		 //System.out.println(u1);
		//return u1;
	}
	@GetMapping("/user/{emailId}")
	public User getUserByEmail(@PathVariable String emailId)
	{
		//logger.info(userService.getUserByEmail("anu@gmail.com").toString());
		return userService.getUserByEmail(emailId);
	}
	@GetMapping("/user/{emailId}/{password}")
	public User getUserByEmailAndPassword(@PathVariable String emailId,@PathVariable String password)
	{
		return userService.getUserByEmailAndPassword(emailId,password);
	}
	@GetMapping("/user/search/{searchName}")
	public List<User> searchUserWithName(@PathVariable String searchName)
	{
		return userService.searchUserByName(searchName);
	}
	
	@GetMapping("user/orders")
	public List<CustomerOrder> getOrderByUser(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if(session.getAttribute("userEmailId")!=null)
		{
		   String email=(String)session.getAttribute("userEmailId");
		   return customerOrderService.getAllOrderByUser(email);	
		}
		else
		{
			return null;
		}
	}
	
	@PostMapping("/user/register")
	public User createUser(@RequestBody User user)
	{		
		return userService.saveUser(user);
	}
	@PutMapping("/user/update")
	public String updateUser(@RequestBody User user)
	{		
		if(userService.updateUser(user)==1)
			return "updated";
		else
			return "User not exists";
	}
	@DeleteMapping("/admin/user/delete/{emailId}")
	public String deleteUser(String emailId)
	{		
		if(userService.deleteByEmail(emailId)==1)
			return "deleted";
		else
			return "User not exists";
	}
	
	
}
