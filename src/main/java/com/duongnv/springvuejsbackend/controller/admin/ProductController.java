package com.duongnv.springvuejsbackend.controller.admin;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminProductController")
@RequestMapping("/api/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ProductDTO createProducts(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProducts(@PathVariable Long productId) {
        System.out.println("========= " + productId);
        productService.deleteById(productId);
    }
}
