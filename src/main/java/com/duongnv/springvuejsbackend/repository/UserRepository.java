package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    @Query("select u from UserEntity u where u.fullname like 'Nguyễn %'")
    UserEntity findByStartLetter(String start);


}
