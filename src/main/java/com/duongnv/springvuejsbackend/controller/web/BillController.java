package com.duongnv.springvuejsbackend.controller.web;

import com.duongnv.springvuejsbackend.dto.ProductProjection;
import com.duongnv.springvuejsbackend.exception.UnknowException;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/bills")
    public List<ProductProjection> getBills(HttpServletRequest request) {
        try {
            String token = jwtUtil.getTokenFromHeader(request);
            String username = jwtUtil.getUsernameFromToken(token);
            return billService.getAllBills(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Exception in bill controller");
        }
    }
}
