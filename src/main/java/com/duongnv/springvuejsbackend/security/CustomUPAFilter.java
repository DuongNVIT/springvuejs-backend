package com.duongnv.springvuejsbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CustomUPAFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    public CustomUPAFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/api/secureLogin", "POST"));
        System.out.println("Vào đây CustomUPAFilter");
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("Vào đây attemptAuthentication");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, password, authorities);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {

        System.out.println("Authen thành công");
        System.out.println(auth);
        SecurityContextHolder.getContext().setAuthentication(auth);
        super.successfulAuthentication(req, res,chain, auth);
        chain.doFilter(req, res);
    }
}
