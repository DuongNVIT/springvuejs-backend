package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.converter.CategoryConverter;
import com.duongnv.springvuejsbackend.converter.ProductConverter;
import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.service.CategoryService;
import com.duongnv.springvuejsbackend.service.ProductService;
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
    private ProductConverter productConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<ProductEntity> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAll(pageable);
    }

    @GetMapping("/products/{category}")
    public List<ProductEntity> getProducts(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        CategoryDTO categoryDTO = categoryService.findByCode(category);
        Pageable pageable = PageRequest.of(page, size);
        return productService.findByCategoryId(categoryDTO.getId(), pageable);
    }

    @PostMapping("/products")
    public void createProducts(@RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
    }

    @GetMapping("/products/search/{name}")
    public List<ProductEntity> searchProduct(@PathVariable(required = false) String name) {
//        if(nam) return productRepository.findAll();
        return productRepository.findByName(name);
    }

    @GetMapping("/products/cart/{userid}")
    public List<ProductEntity> getCart(@PathVariable Long userid) {
        return productRepository.findUserProduct(userid);
    }
}
