package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Override
    public void addProductToCart(Long productId, String token) {

    }
}
