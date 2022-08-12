package com.duongnv.springvuejsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "user_product")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CartEntity extends BaseEntity{

//    @Column(name = "userid")
    private Long userId;

//    @Column(name = "productid")
    private Long productId;


}
