package com.duongnv.springvuejsbackend.service;

public interface CartService {
    void addProductToCart(Long productId, String token);
}
