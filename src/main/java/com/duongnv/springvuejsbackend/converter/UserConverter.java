package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserEntity, UserDTO> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO entityToDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserEntity dtoToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    @Override
    public UserEntity entityToEntity(UserEntity userEntity, UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
