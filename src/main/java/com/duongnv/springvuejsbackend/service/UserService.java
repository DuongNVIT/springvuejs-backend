package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.UserEntity;

public interface UserService {
    UserEntity save(UserDTO userDTO);
    UserDTO findByUsername(String username);
}
