package com.duongnv.springvuejsbackend.service;

import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findByCategoryCode(String code);
}
