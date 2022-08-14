package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<CategoryEntity, CategoryDTO> {


    protected ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoryDTO entityToDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setCode(categoryEntity.getCode());
        return categoryDTO;
    }

    @Override
    public CategoryEntity dtoToEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCode(categoryDTO.getCode());
        return categoryEntity;
    }

    @Override
    public CategoryEntity entityToEntity(CategoryEntity oldCategoryEntity, CategoryDTO categoryDTO) {
        oldCategoryEntity.setId(categoryDTO.getId());
        oldCategoryEntity.setName(categoryDTO.getName());
        oldCategoryEntity.setCode(categoryDTO.getCode());
        return oldCategoryEntity;
    }
}
