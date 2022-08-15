package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.repository.ProductProjection;
import com.duongnv.springvuejsbackend.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    private UserProductRepository userProductRepository;

    @GetMapping("/bills")
    public List<ProductProjection> getAllBills() {
        return userProductRepository.findAllProduct();
    }

}
