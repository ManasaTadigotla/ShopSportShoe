package com.sportyshoespvtltd.shopsportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportyshoespvtltd.shopsportshoes.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("select ud from userdetails ud where ud.emailId=?1 and ud.password=?2")
	public User getUserByEmailAndPassword(String email,String pwd);
	
	@Query("select ud from userdetails ud where ud.firstName=?1 or ud.lastName=?1")
	public List<User> searchByName(String searchName);
	

	@Query("select u from userdetails u")
	public List<User> getUsers();
	
}
