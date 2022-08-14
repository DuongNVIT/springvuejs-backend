package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.UserDTO;

public interface SendMailService {
    void sendMail(UserDTO sendUser, String password);
}
