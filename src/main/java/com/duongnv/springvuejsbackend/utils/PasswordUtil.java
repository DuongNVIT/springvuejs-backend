package com.duongnv.springvuejsbackend.utils;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    public static String generateRandomPassword() {
        StringBuffer password = new StringBuffer("");
        for(int i = 1; i<=10; ++i) {
            char temp = (char) (40 + Math.random() * 40);
            System.out.println(temp);
            password.append(temp);
        }
        return password.toString();
    }
}
