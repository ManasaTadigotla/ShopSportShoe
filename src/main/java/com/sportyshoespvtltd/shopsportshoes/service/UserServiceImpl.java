package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoespvtltd.shopsportshoes.entity.User;
import com.sportyshoespvtltd.shopsportshoes.repository.UserRepository;

@Service(value="userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAllUsers() {
		
		return userRepository.getUsers();
	}

	@Override
	public User saveUser(User user) {
		
		if(user!=null)
		{
		return userRepository.save(user);
		}
		else
			return null;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findById(email).get();
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {		
		//return userRepository.getUserByEmailAndPassword(email, password);
		return userRepository.getUserByEmailAndPassword(email,password);
	}

	@Override
	public int deleteByEmail(String email) {
		User user=new User();
		user=userRepository.findById(email).get();
		if(user!=null)
		{
			userRepository.delete(user);
			return 1;
		}
		else
		{
			return 0;
		}
	}		
		

	@Override
	public int updateUser(User user) {
		if(!userRepository.findById(user.getEmailId()).isEmpty())
		{
			return -1;
		}
		else
		{
			userRepository.save(user);
			return 1;
		}
				
	}

	@Override
	public List<User> searchUserByName(String searchName) {
		// TODO Auto-generated method stub
		return userRepository.searchByName(searchName);
	}


}
