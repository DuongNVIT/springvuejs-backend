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
    public List<ProductDTO> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAll(pageable);
    }

    @GetMapping("/products/{category}")
    public List<ProductDTO> getProducts(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        CategoryDTO categoryDTO = categoryService.findByCode(category);
        Pageable pageable = PageRequest.of(page, size);
        return productService.findByCategoryId(categoryDTO.getId(), pageable);
    }

    @PostMapping("/products")
    public ProductDTO createProducts(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/products/search")
    public List<ProductDTO> searchProduct(
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAllByName(productName, pageable);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProducts(@PathVariable Long productId) {
        System.out.println("========= " + productId);
        productService.deleteById(productId);
    }

}
