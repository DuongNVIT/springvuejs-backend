package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/cart")
    public UserDTO getCart(HttpServletRequest request) {
        String token = jwtUtil.getTokenFromHeader(request);
        String username = jwtUtil.getUsernameFromToken(token);
        UserDTO userDTO = userService.findByUsername(username);
        return userDTO;
    }
}
