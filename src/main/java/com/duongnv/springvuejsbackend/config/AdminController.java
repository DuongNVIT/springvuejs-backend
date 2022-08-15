package com.duongnv.springvuejsbackend.config;

import com.duongnv.springvuejsbackend.entity.ProductStatusEntity;
import com.duongnv.springvuejsbackend.repository.ProductProjection;
import com.duongnv.springvuejsbackend.repository.ProductStatusRepository;
import com.duongnv.springvuejsbackend.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @GetMapping("/products")
    public List<ProductProjection> getAll() {
        return userProductRepository.findAllProduct();
    }

    @GetMapping("/products/status")
    public List<ProductStatusEntity> getAllStatus() {
        return productStatusRepository.findAll();
    }

    @PostMapping("/products/status")
    public void changeStatus(@RequestParam Long userProductId, @RequestParam Long statusId) {
        userProductRepository.updateStatus(userProductId, statusId);
    }

}
