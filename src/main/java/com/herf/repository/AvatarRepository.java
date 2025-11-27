package com.herf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herf.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findByUid(Long userId);
}
