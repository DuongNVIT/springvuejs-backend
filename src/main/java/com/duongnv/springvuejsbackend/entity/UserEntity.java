package com.duongnv.springvuejsbackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserEntity extends BaseEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "status")
    private int status;

    @Column(name = "roleid")
    private Long roleid;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    private RoleEntity role;
}
