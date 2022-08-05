package com.duongnv.springvuejsbackend.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AbstractDTO {
    protected Long id;
    protected Timestamp createdDate;
    protected String createdBy;
    protected Timestamp modifiedDate;
    protected String modifiedBy;
}
