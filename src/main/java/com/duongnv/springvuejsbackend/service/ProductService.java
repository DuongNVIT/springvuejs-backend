package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();
    List<ProductEntity> findAll(Pageable pageable);
    List<ProductEntity> findByCategoryId(Long categoryid, Pageable pageable);
    void save(ProductDTO productDTO);
}
