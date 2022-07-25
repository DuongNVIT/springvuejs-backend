package com.duongnv.springvuejsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long id = 2l;
    private String name = "User";
    private String code = "user";
}
