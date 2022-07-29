package com.duongnv.springvuejsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductDTO extends AbstractDTO{
    private String name;
    private int oleprice;
    private int newprice;
    private String thumbnail;
    private String rate;
    private CategoryDTO category;
    private String categorycode;
}
