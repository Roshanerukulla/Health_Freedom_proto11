package com.herf.service;

import org.springframework.stereotype.Service;

import com.herf.entity.User;

@Service
public interface LoginService {
 User loginUser(String username, String password);
}
