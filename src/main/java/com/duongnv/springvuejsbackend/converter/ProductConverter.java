package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<ProductEntity, ProductDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    protected ModelMapper modelMapper = new ModelMapper();
    @Override
    public ProductDTO entityToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setOldPrice(productEntity.getOldPrice());
        productDTO.setNewPrice(productEntity.getNewPrice());
        productDTO.setThumbnail(productEntity.getThumbnail());
        productDTO.setCategoryCode(productEntity.getCategory().getCode());
        return productDTO;
    }

    @Override
    public ProductEntity dtoToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setOldPrice(productDTO.getOldPrice());
        productEntity.setNewPrice(productDTO.getNewPrice());
        productEntity.setThumbnail(productDTO.getThumbnail());
        CategoryEntity categoryEntity = categoryRepository.findByCode(productDTO.getCategoryCode());
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }

    @Override
    public ProductEntity entityToEntity(ProductEntity productEntity, ProductDTO productDTO) {
        productEntity.setName(productDTO.getName());
        productEntity.setThumbnail(productDTO.getThumbnail());
        productEntity.setOldPrice(productDTO.getOldPrice());
        productEntity.setNewPrice(productDTO.getNewPrice());
        CategoryEntity categoryEntity = categoryRepository.findByCode(productDTO.getCategoryCode());
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }
}
