package com.duongnv.springvuejsbackend.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class CustomAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        if(authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }

        return Optional.of(((UserDetails) authentication.getPrincipal()).getUsername());
    }
}