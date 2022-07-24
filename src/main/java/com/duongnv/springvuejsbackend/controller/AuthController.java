package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.utils.PasswordUtil;
import com.duongnv.springvuejsbackend.dto.MailDemo;
import com.duongnv.springvuejsbackend.exception.UnauthorizeException;
import com.duongnv.springvuejsbackend.exception.WrongUsernamPasswordException;
import com.duongnv.springvuejsbackend.security.JwtRequest;
import com.duongnv.springvuejsbackend.security.JwtResponse;
import com.duongnv.springvuejsbackend.security.JwtUserDetailsService;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.impl.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/api")
    public String test() {
        return "Nguyễn Văn Đương";
    }

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('user')")
    public String demo() {
        return "Demo autho";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAuthority('admin')")
    public String demoadmin() {
        return "Demo autho";
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request)
            throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(44 + " authcontroller");
        return ResponseEntity.ok().body(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new WrongUsernamPasswordException("Sai ten dang nhap hoac mat khau");
        } catch (InsufficientAuthenticationException e) {
            throw new UnauthorizeException("Unauthorize!");
        } catch (UsernameNotFoundException e) {
            throw new WrongUsernamPasswordException("Sai tên đăng nhập hoặc mật khẩu");
        }
    }

    @PostMapping("/dangky")
    public String dangky(@RequestBody MailDemo mailDemo) {
        String pass = passwordUtil.generateRandomPassword();
        sendMailService.sendMail(mailDemo, pass);
        System.out.println(pass);
        return "Gửi thành công password " + pass;
    }
}
