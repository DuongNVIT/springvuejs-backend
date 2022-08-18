package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.exception.*;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import com.duongnv.springvuejsbackend.security.JwtRequest;
import com.duongnv.springvuejsbackend.security.JwtResponse;
import com.duongnv.springvuejsbackend.security.JwtUserDetailsService;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.AuthService;
import com.duongnv.springvuejsbackend.service.IUserService;
import com.duongnv.springvuejsbackend.service.impl.SendMailServiceImpl;
import com.duongnv.springvuejsbackend.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/responsestatus")
    public String status() {
        double b = Math.random();
        System.out.println(b);
        if(b < 0.5) {
            throw new ResponseE();
        }
        return "response status";
    }

    @PostMapping("/duong")
    public String duong() {
        return "Duong test";
    }

    @GetMapping("/duong")
    public String test() {
        return "Nguyễn Văn Đương";
    }

    @GetMapping("/demouser")
    @PreAuthorize("hasAuthority('user')")
    public String demo() {
        return "Demo user";
    }

    @GetMapping("/demoadmin")
    @PreAuthorize("hasAuthority('admin')")
    public String demoadmin() {
        return "Demo admin";
    }


    @PostMapping("/signin")
    public JwtResponse signin(@RequestBody JwtRequest requestPayload) {
        try {
            return authService.signIn(requestPayload);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new WrongUsernamPasswordException("Sai tên đăng nhập hoặc mật khẩu");
        } catch (InsufficientAuthenticationException e) {
            throw new UnauthorizeException("Không có quyền truy cập!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error login!");
            throw new UnknowException("Unknow exception!");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        try {
            return authService.signUp(userDTO);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Đăng ký lỗi");
            throw new DuplicateAccountException("Trùng email hoặc tên đăng nhập!");
        } catch (Exception exception) {
            log.error("Error signup");
            throw new UnknowException("Unknow exception!");
        }
    }
}
