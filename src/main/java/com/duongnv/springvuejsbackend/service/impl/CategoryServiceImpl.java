package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.converter.CategoryConverter;
import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import com.duongnv.springvuejsbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements com.duongnv.springvuejsbackend.service.CategoryService {

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for(CategoryEntity category : categoryEntities) {
            categoryDTOs.add(categoryConverter.entityToDTO(category));
        }
        return categoryDTOs;
    }

    @Override
    public CategoryDTO findByCode(String code) {
        return categoryConverter.entityToDTO(categoryRepository.findByCode(code));
    }

}
