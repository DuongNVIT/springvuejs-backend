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
    @JsonIgnore
    private String password;
    private String fullname;
    private String email;
    private int status = 1;
    private RoleDTO role = new RoleDTO();
    private List<ProductDTO> products = new ArrayList<>();
}
