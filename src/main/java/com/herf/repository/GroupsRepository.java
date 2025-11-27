package com.herf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herf.entity.Groups;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    Optional<Groups> findByCoachIdAndGroupName(Long coachId, String groupName);  // Update the method parameter name
    Optional<Groups> findByUsersContaining(Long userId);
}
