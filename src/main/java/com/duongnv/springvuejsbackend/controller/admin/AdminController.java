package com.duongnv.springvuejsbackend.controller.admin;

import com.duongnv.springvuejsbackend.entity.ProductStatusEntity;
import com.duongnv.springvuejsbackend.dto.ProductProjection;
import com.duongnv.springvuejsbackend.exception.UnknowException;
import com.duongnv.springvuejsbackend.repository.ProductStatusRepository;
import com.duongnv.springvuejsbackend.repository.UserProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserProductStatusRepository userProductRepository;

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @GetMapping("/products")
    public List<ProductProjection> getAll() {
        try {
            return userProductRepository.findAllProduct();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Exception AdminController!");
        }
    }

    @GetMapping("/products/status")
    public List<ProductStatusEntity> getAllStatus() {
        try {
            return productStatusRepository.findAll();
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Exception in AdminController!");
        }
    }

    @PutMapping("/products/status")
    public void changeStatus(@RequestParam Long userProductId, @RequestParam Long statusId) {
        try {
            userProductRepository.updateStatus(userProductId, statusId);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Exception in Admin Controller!");
        }
    }

}
