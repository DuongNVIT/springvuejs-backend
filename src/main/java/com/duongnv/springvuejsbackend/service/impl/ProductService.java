package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}
