package com.herf.service;

import com.herf.entity.AvatarDTO;
import com.herf.entity.User;
import com.herf.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAvatarService {
    @Autowired
    private final UserRepository userRepository;


    public UserAvatarService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void associateAvatarWithUser(Long userId, AvatarDTO avatarDTO) {
        // Fetch the user entity by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Update the user's avatar information
        user.setAvatarId(avatarDTO.getId());

        // Save the updated user entity back to the database
        userRepository.save(user);
    }
}