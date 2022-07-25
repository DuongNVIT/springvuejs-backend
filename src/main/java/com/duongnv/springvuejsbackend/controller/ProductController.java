package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<ProductEntity> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> pageL = null;
        System.out.println(page);
        System.out.println(size);
        pageL = (Page<ProductEntity>) productRepository.findAll(pageable);
        System.out.println(pageL.getTotalElements());
        System.out.println(pageL.getContent());
        System.out.println();
        return (List<ProductEntity>) pageL.getContent();
    }
}
