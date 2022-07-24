package com.duongnv.springvuejsbackend.exception;

public class WrongUsernamPasswordException extends RuntimeException{

    public WrongUsernamPasswordException(String message) {
        super(message);
    }
}
