package com.duongnv.springvuejsbackend.dto;

import org.springframework.http.HttpStatus;

public class ServerResponseDto {

    private static final ServerResponseDto SUCCESS = new ServerResponseDto(HttpStatus.OK, null);
    private static final ServerResponseDto ERROR = new ServerResponseDto(HttpStatus.UNAUTHORIZED, null);

    private HttpStatus status;
    private Object data;

    private ServerResponseDto(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static ServerResponseDto success() {
        return SUCCESS;
    }

    public static ServerResponseDto success(Object data) {
        return new ServerResponseDto(HttpStatus.OK, data);
    }

    public static ServerResponseDto error() {
        return ERROR;
    }

    public static ServerResponseDto error(Object data) {
        return new ServerResponseDto(HttpStatus.UNAUTHORIZED, data);
    }

}
