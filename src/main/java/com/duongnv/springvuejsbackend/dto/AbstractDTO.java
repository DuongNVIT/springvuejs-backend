package com.duongnv.springvuejsbackend.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AbstractDTO {
    protected Long id;
    protected Timestamp createddate;
    protected String createdby;
    protected Timestamp modifieddate;
    protected String modifiedby;
}
