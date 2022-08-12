package com.duongnv.springvuejsbackend.security;

import com.duongnv.springvuejsbackend.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println(System.currentTimeMillis());

        try {
//            if(request.getServletPath().startsWith("/")) {
//                chain.doFilter(request, response);
//                return;
//            }
            if(request.getServletPath().startsWith("/api/signin")) {
                System.out.println("K cần filter");
                chain.doFilter(request, response);
                return;
            }
            final String requestTokenHeader = request.getHeader("Authorization");

            String username = null;
            String jwtToken = null;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                System.out.println("79 vào đây");
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

            System.out.println("94 đến đây");
            // Once we get the token validate it.
            HttpWrapper requestWrapper = new HttpWrapper(request);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println(71);
                UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
                requestWrapper.addParameter("username", userDetails.getUsername());
                requestWrapper.addParameter("password", "password");
                System.out.println(73);
                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    System.out.println("77 Token oke");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } else {
//            Map<String, String> error  = new HashMap<>();
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////        System.out.println(authException);
////        System.out.println(authException.getMessage());
//            error.put("message", "Unauthorize");
//            new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
            System.out.println("113 vào đây");
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorDTO responseDTO = new ErrorDTO(
                    "Không có quyền truy cập",
                    HttpStatus.UNAUTHORIZED.value()
            );
            new ObjectMapper().writeValue(response.getOutputStream(), responseDTO);
        }
    }
}