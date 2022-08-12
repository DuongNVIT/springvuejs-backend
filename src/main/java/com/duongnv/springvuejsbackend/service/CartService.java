package com.duongnv.springvuejsbackend.service;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    void addProductToCart(Long productId, String token);
}
