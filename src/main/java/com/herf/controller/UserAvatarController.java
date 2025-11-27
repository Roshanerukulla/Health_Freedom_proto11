package com.herf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herf.entity.AvatarDTO;
import com.herf.service.UserAvatarService;

@RestController
@RequestMapping("/users")
public class UserAvatarController {
	@Autowired
    private final UserAvatarService userAvatarService;

    
    public UserAvatarController(UserAvatarService userAvatarService) {
        this.userAvatarService = userAvatarService;
    }

    @PutMapping("/{userId}/{avatarId}")
    public ResponseEntity<String> associateAvatarWithUser(@PathVariable Long userId, @PathVariable Long avatarId) {
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(avatarId);
        userAvatarService.associateAvatarWithUser(userId, avatarDTO);
        return ResponseEntity.ok("Avatar associated with the user successfully");
    }
}