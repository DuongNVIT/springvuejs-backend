package com.duongnv.springvuejsbackend.dto;

import lombok.Data;

@Data
public class UserDTO extends AbstractDTO{
    private String username;
    private String fullname;
}
