package com.duongnv.springvuejsbackend.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "createddate")
    protected Timestamp createdDate;

    @CreatedBy
    @Column(name = "createdby")
    protected String createdby;

    @LastModifiedDate
    @Column(name = "modifieddate")
    protected Timestamp modifiedDate;

    @LastModifiedBy
    @Column(name = "modifiedby")
    protected String modifiedby;
}
