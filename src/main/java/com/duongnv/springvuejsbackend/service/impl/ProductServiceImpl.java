package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.converter.ProductConverter;
import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.CategoryRepository;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> findAll(org.springframework.data.domain.Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public List<ProductEntity> findByCategoryId(Long categoryid, Pageable pageable) {
        return productRepository.findByCategoryId(categoryid, pageable).getContent();
    }

    @Override
    public void save(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.dtoToEntity(productDTO);
        CategoryEntity categoryEntity = categoryRepository.findByCode(productDTO.getCategoryCode());
        productEntity.setCategory(categoryEntity);
        productRepository.save(productEntity);
    }

}
