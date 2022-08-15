package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.RoleDTO;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.RoleEntity;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserEntity, UserDTO> {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO entityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setFullname(userEntity.getFullName());
        userDTO.setEmail(userEntity.getEmail());
        RoleDTO roleDTO = roleConverter.entityToDTO(userEntity.getRole());
        userDTO.setRole(roleDTO);
        return userDTO;
    }

    @Override
    public UserEntity dtoToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setFullName(userDTO.getFullname());
        RoleEntity roleEntity = roleConverter.dtoToEntity(userDTO.getRole());
        userEntity.setRole(roleEntity);
        return userEntity;
    }

    @Override
    public UserEntity entityToEntity(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setId(userDTO.getId());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setFullName(userDTO.getFullname());
        return userEntity;
    }
}
