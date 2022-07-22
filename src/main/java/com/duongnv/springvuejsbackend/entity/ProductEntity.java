package com.duongnv.springvuejsbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "name")
    private String name;

    @Column(name = "oldprice")
    private int oldPrice;

    @Column(name = "newprice")
    private int newPrice;

    @Column(name = "rate")
    private int rate;
}
