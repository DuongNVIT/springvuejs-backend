package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendMail(UserDTO sendUser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.autoletter.itshop");
        message.setTo(sendUser.getEmail());
        message.setSubject("Xác nhận đăng ký tài khoản ITShop");
        message.setText("Xin chào " + sendUser.getFullname() + ", kích hoạt tài khoản tại đây: " +
                "http://localhost:4000/active?username=" + sendUser.getUsername() +
                "&code=" + sendUser.getCode());
        mailSender.send(message);
    }
}
