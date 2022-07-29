package com.duongnv.springvuejsbackend.converter;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends Converter<ProductEntity, ProductDTO> {

    @Override
    public ProductDTO entityToDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @Override
    public ProductEntity dtoToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductEntity.class);
    }

    @Override
    public ProductEntity entityToEntity(ProductEntity productEntity, ProductDTO productDTO) {
        return null;
    }
}
