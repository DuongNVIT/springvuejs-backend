package com.duongnv.springvuejsbackend.security;

import com.duongnv.springvuejsbackend.dto.ErrorResponseDTO;
import com.duongnv.springvuejsbackend.exception.InvalidTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Set<String> IGNORED_FILTER_URL = new HashSet<>();

    static {
        IGNORED_FILTER_URL.add("/");
        IGNORED_FILTER_URL.add("/api/signin");
        IGNORED_FILTER_URL.add("/api/signup");
        IGNORED_FILTER_URL.add("/api/products");
        IGNORED_FILTER_URL.add("/api/categories");
    }

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        boolean isIgnoreJwtFilter =
                IGNORED_FILTER_URL.contains(request.getRequestURI())
                || request.getRequestURI().startsWith("/api/products");
        if(isIgnoreJwtFilter) {
            System.out.println("Không cần filter jwt");
            chain.doFilter(request, response);
            return;
        }

        System.out.println("Phải jwt filter");
        System.out.println(request.getRequestURI());

        try {
            String token = getTokenFromHeader(request);
            UserDetails userDetails = getUserFromToken(token);
            jwtTokenUtil.validateToken(token, userDetails);
            saveToSecurityContext(userDetails, request);
        } catch (Exception exception) {
            logger.error("Invalid token");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                    "Không có quyền truy cập, token không hợp lệ",
                    HttpStatus.UNAUTHORIZED.value());
            new ObjectMapper().writeValue(response.getOutputStream(), responseDTO);
            return;
        }
        System.out.println("113 vào đây");
        chain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            return token;
        } else {
            throw new InvalidTokenException("Token does not begin with bearer!");
        }
    }

    private UserDetails getUserFromToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return userDetails;
    }

    private void saveToSecurityContext(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}