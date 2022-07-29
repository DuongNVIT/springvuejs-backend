package com.duongnv.springvuejsbackend.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "createddate")
    protected Timestamp createddate;

    @CreatedBy
    @Column(name = "createdby")
    protected String createdby;

    @LastModifiedDate
    @Column(name = "modifieddate")
    protected Timestamp modifieddate;

    @LastModifiedBy
    @Column(name = "modifiedby")
    protected String modifiedby;
}
