package com.duongnv.springvuejsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryid")
    private CategoryEntity category;
}
