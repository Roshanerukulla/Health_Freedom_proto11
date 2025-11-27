package com.herf.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herf.entity.FriendDetailsDTO;
import com.herf.entity.Groups;
import com.herf.entity.User;
import com.herf.entity.UserResponse;
import com.herf.exception.GroupNotFoundException;
import com.herf.exception.UserNotFoundException;
import com.herf.repository.ActivityRepository;
import com.herf.repository.GroupsRepository;
import com.herf.repository.UserRepository;

@Service

public class GroupService {

    @Autowired
    private GroupsRepository groupRepository;

    @Autowired
    private UserRepository userRepository;  // Assuming this repository exists

    @Autowired
    private ActivityRepository activityRepository;  // Assuming this repository exists

    public Groups createGroupAutomatically(Long userId) {
        User user = getUser(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        Long coachId = user.getCoachId();

        Groups existingGroup = groupRepository.findByCoachIdAndGroupName(coachId, "Group " + coachId).orElse(null);
        if (existingGroup != null) {
            return addUserToGroup(existingGroup.getGroupId(), userId);
        }

        Groups group = new Groups();
        group.setCoachId(coachId);
        group.setGroupName("Group " + coachId);
        group.setUsers(Collections.singletonList(userId));

        return groupRepository.save(group);
    }

    public List<FriendDetailsDTO> getFriendsInfo(Long userId) {
        Groups group = groupRepository.findByUsersContaining(userId)
                .orElseThrow(() -> new GroupNotFoundException("User not found in any group with id: " + userId));

        List<Long> userIds = group.getUsers();
        List<FriendDetailsDTO> friendsInfo = new ArrayList<>();

        for (Long friendId : userIds) {
            User user = getUser(friendId);
            if (user == null) {
                throw new UserNotFoundException("User not found with id: " + friendId);
            }

            Long totalSteps = getTotalSteps(friendId);

            FriendDetailsDTO friendDetailsDto = new FriendDetailsDTO();
            friendDetailsDto.setUserId(friendId);
            friendDetailsDto.setUsername(user.getUsername());
            friendDetailsDto.setTotalSteps(totalSteps);
            friendsInfo.add(friendDetailsDto);
        }

        return friendsInfo;
    }

    public List<Long> getTotalSteps(List<Long> userIds) {
        List<Long> totalSteps = new ArrayList<>();
        for (Long userId : userIds) {
            totalSteps.add(getTotalSteps(userId));
        }
        return totalSteps;
    }

    public Optional<Groups> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Groups addUserToGroup(Long groupId, Long userId) {
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + groupId));

        if (!group.getUsers().contains(userId)) {
            group.getUsers().add(userId);
            return groupRepository.save(group);
        }

        return group;
    }

    private User getUser(Long userId) {
        // Fetch user data from the UserRepository
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    private Long getTotalSteps(Long userId) {
        // Fetch total steps data from the ActivityRepository
        return activityRepository.getTotalStepsByUserId(userId);
    }
}
