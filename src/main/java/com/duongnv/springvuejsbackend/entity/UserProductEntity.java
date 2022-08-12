package com.duongnv.springvuejsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProductEntity extends BaseEntity{

    @Column(name = "userid")
    private Long userId;

    @Column(name = "productid")
    private Long productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusid")
    private ProductStatusEntity productStatus;

}
