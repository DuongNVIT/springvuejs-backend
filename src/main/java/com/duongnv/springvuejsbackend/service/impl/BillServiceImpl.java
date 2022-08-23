package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.repository.ProductProjection;
import com.duongnv.springvuejsbackend.repository.UserProductRepository;
import com.duongnv.springvuejsbackend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    public List<ProductProjection> getAllBills(String username) {
        return userProductRepository.findAllBills(username);
    }
}
