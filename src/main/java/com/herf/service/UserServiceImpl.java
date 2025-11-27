package com.herf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herf.entity.User;
import com.herf.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	
	
	@Autowired
 	UserRepository ur;
	@Override
	public User getUserDetails(Long userId) {
		 return ur.findById(userId).orElse(null);
	}
	@Override
	public Long getUserId(Long userId) {
	    return ur.findById(userId).map(User::getId).orElse(null);
	}
	@Override
	public String getUsername(Long userId) {
		// TODO Auto-generated method stub
		return ur.findUsernamesById(userId);
	}


}


