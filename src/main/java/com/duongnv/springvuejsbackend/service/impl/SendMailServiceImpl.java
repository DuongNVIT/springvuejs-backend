package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private MailSender mailSender;

    @Override
    public void sendMail(UserDTO sendUser, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nguyenduong985911@gmail.com");
        message.setTo(sendUser.getEmail());
        message.setSubject("Xác nhận đăng ký tài khoản ITShop");
        message.setText("Xin chào " + sendUser.getFullname() + ", mật khẩu của bạn là: " + password);
        mailSender.send(message);
    }
}
