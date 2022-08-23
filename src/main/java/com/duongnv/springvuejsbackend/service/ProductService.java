package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();
    List<ProductDTO> findAll(Pageable pageable);
    List<ProductDTO> findByCategoryId(Long categoryid, Pageable pageable);
    ProductDTO save(ProductDTO productDTO);
    void deleteById(Long productId);
    List<ProductDTO> findAllByName(String productName, Pageable pageable);
}
