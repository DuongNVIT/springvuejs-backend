package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.converter.UserConverter;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.RoleEntity;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.repository.RoleRepository;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import com.duongnv.springvuejsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity save(UserDTO userDTO) {
        System.out.println("Lưu người dùng");
        UserEntity userToSave = userConverter.dtoToEntity(userDTO);
        RoleEntity roleEntity = roleRepository.getReferenceById(2l);
        userToSave.setRole(roleEntity);
        return userRepository.save(userToSave);
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userConverter.entityToDTO(userRepository.findByUsername(username));
    }
}
