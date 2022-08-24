package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.security.JwtRequest;
import com.duongnv.springvuejsbackend.security.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtResponse signIn(JwtRequest requestPayload);
    ResponseEntity<?> signUp(UserDTO userDTO);
    void active(String username, String code);
}
