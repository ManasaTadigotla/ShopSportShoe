package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import com.sportyshoespvtltd.shopsportshoes.entity.User;

public interface UserService {

	public List<User> findAllUsers();
	public User saveUser(User user);
	public User getUserByEmail(String email);
	public User getUserByEmailAndPassword(String email,String password);
	public int deleteByEmail(String email);
	public int updateUser(User user);
	public List<User> searchUserByName(String name);

}
