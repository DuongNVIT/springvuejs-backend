package com.duongnv.springvuejsbackend.repository;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;

public interface ProductProjection {

    Long getId();
    Timestamp getCreatedDate();
    String getCreatedBy();
    Timestamp getModifiedDate();
    String getModifiedBy();
    String getName();
    String getThumbnail();
    int getOldPrice();
    int getNewPrice();

    @Value("#{target.status.toString()}")
    String getStatus();

    @Value("#{target.userProductId}")
    Long getUserProductId();

    @Value("#{target.username}")
    String getUsername();

    @Value("#{target.statusId}")
    Long getStatusId();
}
