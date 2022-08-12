package com.duongnv.springvuejsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO {
    protected Long id;
    protected Timestamp createdDate;
    protected String createdBy;
    protected Timestamp modifiedDate;
    protected String modifiedBy;
}
