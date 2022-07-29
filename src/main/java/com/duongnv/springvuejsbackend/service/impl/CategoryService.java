package com.duongnv.springvuejsbackend.service.impl;

import com.duongnv.springvuejsbackend.converter.CategoryConverter;
import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import com.duongnv.springvuejsbackend.repository.CategoryRepository;
import com.duongnv.springvuejsbackend.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(CategoryEntity category : categoryEntities) {
            categoryDTOS.add(categoryConverter.entityToDTO(category));
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findByCategoryCode(String code) {
        return categoryConverter.entityToDTO(categoryRepository.findByCode(code));
    }

}
