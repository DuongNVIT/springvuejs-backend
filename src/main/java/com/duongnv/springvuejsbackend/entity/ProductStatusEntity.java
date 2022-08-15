package com.duongnv.springvuejsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatusEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "productStatus")
    @JsonIgnore
    private List<UserProductEntity> userProducts;

}
