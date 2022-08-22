package com.duongnv.springvuejsbackend.security;

import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        System.out.println("==================");
        System.out.println();
        System.out.println(userEntity.getClass().getName());
        System.out.println(Hibernate.isInitialized(userEntity.getRole()));
        System.out.println(userEntity.getRole().getClass().getName());
//        System.out.println(userEntity.getRole().getName());
        System.out.println();
        System.out.println("==========");
        if(userEntity != null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            System.out.println("Bắt đầu lỗi jwt");
            // Nó lỗi là do
            authorities.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));
            return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}