package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.MailDemo;
import com.duongnv.springvuejsbackend.dto.UserDTO;

public interface ISendEmailService {
    void sendMail(UserDTO mailDemo, String password);
}
