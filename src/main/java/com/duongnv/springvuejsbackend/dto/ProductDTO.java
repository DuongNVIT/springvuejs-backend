package com.duongnv.springvuejsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends AbstractDTO{
    private String name;
    private int oldPrice;
    private int newPrice;
    private String thumbnail;
    private String rate;
    private String categoryCode;
    private String status;
    private Long statusId;
    private Long userId;
    private Long totalItem;
}
