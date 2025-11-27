//package com.hf.user.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hf.user.exception.UserNotFoundException;
//import com.hf.user.feign.GroupFeignClient;
//import com.hf.user.repository.UserRepository;
//@Service
//public class UserGroupService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private GroupFeignClient groupFeignClient;
//
//    public List<String> getFriendNames(Long userId) {
//        // Fetch user IDs from group service based on user ID
//        List<Long> friendUserIds = groupFeignClient.getFriendUserIds(userId);
//
//        if (friendUserIds.isEmpty()) {
//            throw new UserNotFoundException("No friends found for user with id: " + userId);
//        }
//
//        // Fetch usernames from user service based on user IDs
//        return userRepository.findUsernamesByIds(friendUserIds);
//    }
//
//    // Other user-related methods...
//}



