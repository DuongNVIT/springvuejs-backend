package com.duongnv.springvuejsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    @JsonIgnore
    private RoleEntity role;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}
