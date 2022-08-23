package com.duongnv.springvuejsbackend.service;

import java.util.List;

public interface CartService {
    void addProductToCart(Long productId, String token);
    void order(List<Long> ids);
}
