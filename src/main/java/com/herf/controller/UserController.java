package com.herf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.herf.entity.User;
import com.herf.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserDetails(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/id")
    public ResponseEntity<Long> getUserId(@PathVariable Long userId) {
        Long fetchedUserId = userService.getUserId(userId);
        if (fetchedUserId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fetchedUserId);
    }

    @GetMapping("/{userId}/username")
    public ResponseEntity<String> getUserName(@PathVariable Long userId) {
        String username = userService.getUsername(userId);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(username);
    }

}
