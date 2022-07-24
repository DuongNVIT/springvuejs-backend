package com.duongnv.springvuejsbackend.exception;

public class UnauthorizeException extends RuntimeException{

    public UnauthorizeException(String message) {
        super(message);
    }
}
