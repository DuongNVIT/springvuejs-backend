package com.duongnv.springvuejsbackend.security;

import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        System.out.println(userEntity.getFullname());
        System.out.println(userEntity.getAddress());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getRole().getName());
        if(userEntity != null) {
            return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
//        if ("duongnv".equals(username)) {
//            System.out.println("loadbyusernam");
//            return new User("duongnv", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

    }

}