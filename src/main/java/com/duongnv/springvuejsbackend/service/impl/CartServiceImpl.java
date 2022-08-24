package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.repository.UserProductStatusRepository;
import com.duongnv.springvuejsbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private UserProductStatusRepository userProductRepository;

    @Override
    public void addProductToCart(Long productId, String token) {

    }

    @Override
    public void order(List<Long> ids) {
        userProductRepository.order(ids);
    }
}
