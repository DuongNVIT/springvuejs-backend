package com.duongnv.springvuejsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDemo {
    private String username;
    private String email;
    private String fullName;
}