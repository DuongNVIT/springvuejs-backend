package com.duongnv.springvuejsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProductDTO extends AbstractDTO{
    private Long userId;
    private Long productId;
    private Long statusId;
}
