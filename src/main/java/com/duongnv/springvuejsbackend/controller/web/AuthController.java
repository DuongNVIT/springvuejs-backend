package com.duongnv.springvuejsbackend.controller.web;

import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.exception.*;
import com.duongnv.springvuejsbackend.security.JwtRequest;
import com.duongnv.springvuejsbackend.security.JwtResponse;
import com.duongnv.springvuejsbackend.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public JwtResponse signin(@RequestBody JwtRequest requestPayload) {
        try {
            return authService.signIn(requestPayload);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new WrongUsernamPasswordException("Sai tên đăng nhập hoặc mật khẩu");
        } catch (InsufficientAuthenticationException e) {
            throw new UnauthorizeException("Không có quyền truy cập!");
        } catch (DisabledException exception) {
            throw new DisableAccountException("Tài khoản chưa kích hoạt");
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
            exception.printStackTrace();
            log.error("Error signup");
            throw new UnknowException("Unknow exception!");
        }
    }

    @PostMapping("/active")
    public String activeAccount(@RequestParam String username, @RequestParam String code) {
        try {
            authService.active(username, code);
            return "Kích hoạt tài khoản thành công";
        } catch (Exception exception) {
          exception.printStackTrace();
          throw new UnknowException("Active account exception");
        }
    }
}
