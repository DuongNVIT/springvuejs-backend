package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.dto.ProductProjection;
import com.duongnv.springvuejsbackend.repository.UserProductStatusRepository;
import com.duongnv.springvuejsbackend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private UserProductStatusRepository userProductRepository;

    @Override
    public List<ProductProjection> getAllBills(String username) {
        return userProductRepository.findAllBills(username);
    }
}
