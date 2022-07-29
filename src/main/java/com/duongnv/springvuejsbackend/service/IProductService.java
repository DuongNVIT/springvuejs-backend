package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.entity.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductEntity> findAll();
    List<ProductEntity> findByCategoryId(Long categoryid);
}
