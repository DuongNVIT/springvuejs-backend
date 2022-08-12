package com.duongnv.springvuejsbackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @PostMapping("/secureLogin")
    public String login() {
        System.out.println("Vào demosecured login");
        return "demosecurelogin";
    }
}
