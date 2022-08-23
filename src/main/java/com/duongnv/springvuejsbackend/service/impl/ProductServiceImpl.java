package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.converter.ProductConverter;
import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.CategoryRepository;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    public List<ProductDTO> findAll(Pageable pageable) {
        Page<ProductEntity> page = productRepository.findAll(pageable);
        return getProductDTOS(page);
    }

    private List<ProductDTO> getProductDTOS(Page<ProductEntity> page) {
        List<ProductEntity> productEntities = page.getContent();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(ProductEntity productEntity : productEntities) {
            ProductDTO productDTO = productConverter.entityToDTO(productEntity);
            productDTO.setTotalItem(page.getTotalElements());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long categoryId, Pageable pageable) {
        Page<ProductEntity> page = productRepository.findByCategoryId(categoryId, pageable);
        return getProductDTOS(page);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.dtoToEntity(productDTO);
        CategoryEntity categoryEntity = categoryRepository.findByCode(productDTO.getCategoryCode());
        productEntity.setCategory(categoryEntity);
        return productConverter.entityToDTO(productRepository.save(productEntity));
    }

    @Override
    public void deleteById(Long productId) {
        Integer x = Integer.valueOf((int) productId.longValue());
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDTO> findAllByName(String productName, Pageable pageable) {
        Page<ProductEntity> page = productRepository.findByName(productName, pageable);
        return getProductDTOS(page);
    }

}
