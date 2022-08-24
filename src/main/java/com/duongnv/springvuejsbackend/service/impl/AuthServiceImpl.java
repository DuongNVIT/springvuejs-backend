package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.dto.RoleDTO;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.exception.UnknowException;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import com.duongnv.springvuejsbackend.security.JwtRequest;
import com.duongnv.springvuejsbackend.security.JwtResponse;
import com.duongnv.springvuejsbackend.security.JwtUserDetailsService;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.AuthService;
import com.duongnv.springvuejsbackend.service.SendMailService;
import com.duongnv.springvuejsbackend.service.UserService;
import com.duongnv.springvuejsbackend.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Autowired
    private UserRepository userRepository;

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
        System.out.println(userDTO);
        UUID id = UUID.randomUUID();
        System.out.println(id);
        userDTO.setCode(id.toString());
        userService.save(userDTO);
        sendMailService.sendMail(userDTO);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @Override
    public void active(String username, String code) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(code.equals(userEntity.getCode()) && userEntity.getStatus() == 0) {
            userEntity.setStatus(1);
            userRepository.save(userEntity);
        } else {
            throw new UnknowException("Active account exception");
        }
    }
}
