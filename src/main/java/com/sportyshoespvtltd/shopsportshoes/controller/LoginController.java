package com.sportyshoespvtltd.shopsportshoes.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoespvtltd.shopsportshoes.entity.User;
import com.sportyshoespvtltd.shopsportshoes.service.UserService;

@RestController
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/login/{email}/{password}")
	public User getUserByEmailAndPassword(@PathVariable String email,@PathVariable String password)
	{
		return userService.getUserByEmailAndPassword(email, password);
		
	}
	@GetMapping("/logout")
	public String logout(javax.servlet.http.HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session !=null) {
			session.invalidate();
			return "logged out";
		}
		else
		return "session expired";
		
	}

	@PostMapping("/login/changePassword")
	public User changePassword(@RequestBody User user) {
		
		 User user1 = userService.getUserByEmail(user.getEmailId()); 
		 
		 user1.setPassword(user.getPassword());
		userService.updateUser(user1);
		return user1;
	}
}
