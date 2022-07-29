package com.duongnv.springvuejsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private int oldprice;

    @Column(name = "newprice")
    private int newprice;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryid")
    private CategoryEntity category;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_product", joinColumns = @JoinColumn(name = "productid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    private List<UserEntity> users = new ArrayList<>();

}
