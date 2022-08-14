package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.RoleDTO;
import com.duongnv.springvuejsbackend.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<RoleEntity, RoleDTO> {

    protected ModelMapper modelMapper = new ModelMapper();

    @Override
    public RoleDTO entityToDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());
        roleDTO.setCode(roleEntity.getCode());
        return roleDTO;
    }

    @Override
    public RoleEntity dtoToEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());
        roleEntity.setCode(roleDTO.getCode());
        return roleEntity;
    }

    @Override
    public RoleEntity entityToEntity(RoleEntity roleEntity, RoleDTO roleDTO) {
        return null;
    }
}
