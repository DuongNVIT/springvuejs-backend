package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.converter.UserConverter;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.exception.DuplicateAccountException;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import com.duongnv.springvuejsbackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity save(UserDTO userDTO) {
        try {
            System.out.println("Lưu người dùng");
            return userRepository.save(userConverter.dtoToEntity(userDTO));
        } catch (DataIntegrityViolationException e) {
            System.out.println("Trùng tên");
            throw new DuplicateAccountException("Trùng email hoặc tên đăng nhập");
        }
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userConverter.entityToDTO(userRepository.findByUsername(username));
    }
}
