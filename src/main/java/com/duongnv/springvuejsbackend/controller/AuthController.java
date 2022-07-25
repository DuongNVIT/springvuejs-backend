package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.RoleDTO;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.RoleEntity;
import com.duongnv.springvuejsbackend.service.IUserService;
import com.duongnv.springvuejsbackend.service.impl.UserService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

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
    public ResponseEntity<?> signin(@RequestBody JwtRequest request)
            throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(44 + " authcontroller");
        return ResponseEntity.ok().body(new JwtResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        String pass = passwordUtil.generateRandomPassword();
        userDTO.setPassword(passwordEncoder.encode(pass));
        userService.save(userDTO);
        sendMailService.sendMail(userDTO, pass);
        return ResponseEntity.ok("Đăng ký thành công");
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
}
