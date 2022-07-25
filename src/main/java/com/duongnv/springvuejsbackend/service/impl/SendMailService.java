package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.dto.MailDemo;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.service.ISendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendMailService implements ISendEmailService {
    @Autowired
    private MailSender mailSender;

    @Override
    public void sendMail(UserDTO userDTO, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nguyenduong985911@gmail.com");
        message.setTo(userDTO.getEmail());
        message.setSubject("Xác nhận đăng ký tài khoản ITShop");
        message.setText("Xin chào" + userDTO.getFullname() + "mật khẩu của bạn là: " + password);
        mailSender.send(message);
    }
}
