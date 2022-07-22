package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
