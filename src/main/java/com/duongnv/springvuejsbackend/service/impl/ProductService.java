package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;



    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> findByCategoryId(Long categoryid) {
        return productRepository.findByCategoryId(categoryid);
    }

}
