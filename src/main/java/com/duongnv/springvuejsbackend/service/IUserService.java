package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.UserEntity;

public interface IUserService {
    UserEntity save(UserDTO userDTO);
}
