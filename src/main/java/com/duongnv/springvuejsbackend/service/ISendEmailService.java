package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.MailDemo;

public interface ISendEmailService {
    void sendMail(MailDemo mailDemo, String password);
}
