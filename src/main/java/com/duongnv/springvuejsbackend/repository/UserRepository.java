package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
