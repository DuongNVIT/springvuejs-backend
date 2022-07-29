package com.duongnv.springvuejsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<ProductEntity> products = new ArrayList<>();
}
