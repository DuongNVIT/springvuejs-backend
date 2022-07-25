package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<CategoryEntity, CategoryDTO> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoryDTO entityToDTO(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    @Override
    public CategoryEntity dtoToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }

    @Override
    public CategoryEntity entityToEntity(CategoryEntity categoryEntity, CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }
}
