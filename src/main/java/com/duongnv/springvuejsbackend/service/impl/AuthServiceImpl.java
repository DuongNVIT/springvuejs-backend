package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.dto.RoleDTO;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.security.JwtRequest;
import com.duongnv.springvuejsbackend.security.JwtResponse;
import com.duongnv.springvuejsbackend.security.JwtUserDetailsService;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.AuthService;
import com.duongnv.springvuejsbackend.service.SendMailService;
import com.duongnv.springvuejsbackend.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public JwtResponse signIn(JwtRequest requestPayload) {
        UsernamePasswordAuthenticationToken
                authenticationObject = new UsernamePasswordAuthenticationToken(requestPayload.getUsername(), requestPayload.getPassword());
        authenticationManager.authenticate(authenticationObject);
        System.out.println("Load trong authServiceImpl!");
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(requestPayload.getUsername());
        UserDTO userDTO = userService.findByUsername(requestPayload.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(userDTO.getId(), userDTO.getRole().getCode(), userDTO.getUsername(), token);
        return jwtResponse;
    }


    @Override
    public ResponseEntity<?> signUp(UserDTO userDTO) {
        String pass = passwordUtil.generateRandomPassword();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(2l);
        roleDTO.setName("User");
        roleDTO.setCode("user");
        userDTO.setRole(roleDTO);
        userDTO.setPassword(passwordEncoder.encode(pass));
        sendMailService.sendMail(userDTO, pass);
        userService.save(userDTO);
        return ResponseEntity.ok("Đăng ký thành công");
    }
}
