package com.herf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.herf.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsername(String username);

  
    boolean existsByUsername(String username);

    boolean existsByEmailId(String emailId);


	String findUsernamesById(Long userId);
}
