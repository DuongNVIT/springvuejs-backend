package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.repository.ProductProjection;

import java.util.List;

public interface BillService {
    List<ProductProjection> getAllBills(String username);
}
