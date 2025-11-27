package com.herf.service;

import org.springframework.stereotype.Service;

import com.herf.entity.User;

@Service
public interface UserService {
 public User getUserDetails(Long userId);
 Long getUserId(Long userId);
public String getUsername(Long userId);
}
