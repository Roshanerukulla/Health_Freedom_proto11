package com.herf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herf.entity.FriendDetailsDTO;
import com.herf.entity.Groups;
import com.herf.entity.UserResponse;
import com.herf.exception.GroupAlreadyExistException;
import com.herf.exception.GroupNotFoundException;
import com.herf.exception.UserNotFoundException;
import com.herf.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/{userId}/add-to-group")
    public ResponseEntity<Groups> createGroupAutomatically(@PathVariable Long userId) {
        try {
            Groups createdGroup = groupService.createGroupAutomatically(userId);
            return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
        } catch (UserNotFoundException | GroupAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/friendsinfo")
    public ResponseEntity<List<FriendDetailsDTO>> getFriendsInfo(@PathVariable Long userId) {
        try {
            List<FriendDetailsDTO> friendsInfo = groupService.getFriendsInfo(userId);
            return new ResponseEntity<>(friendsInfo, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/friends/{userId}")
//    public ResponseEntity<List<Long>> getFriendsUserIds(@PathVariable Long userId) {
//        List<Long> friendsUserIds = groupService.getFriendsUserIds(userId);
//        return ResponseEntity.ok(friendsUserIds);
//    }


    // Other endpoints for group-related operations

    @GetMapping("/{groupId}")
    public ResponseEntity<Groups> getGroupById(@PathVariable Long groupId) {
        Optional<Groups> group = groupService.getGroupById(groupId);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
