package com.duongnv.springvuejsbackend.dto;

import com.duongnv.springvuejsbackend.entity.RoleEntity;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO extends AbstractDTO{
    private String username;
    private String password;
    private String fullname;
    private String email;
    private int status;
    private RoleDTO role = new RoleDTO();
    private String code;
    private List<ProductDTO> products = new ArrayList<>();
}
