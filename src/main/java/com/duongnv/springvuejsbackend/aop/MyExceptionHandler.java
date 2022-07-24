package com.duongnv.springvuejsbackend.aop;

import com.duongnv.springvuejsbackend.dto.ResponseDTO;
import com.duongnv.springvuejsbackend.exception.InvalidTokenException;
import com.duongnv.springvuejsbackend.exception.UnauthorizeException;
import com.duongnv.springvuejsbackend.exception.WrongUsernamPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    private ResponseDTO errorResponse = new ResponseDTO();

    @ExceptionHandler(WrongUsernamPasswordException.class)
    public ResponseEntity<?> wrongUsernamePasswordException(WrongUsernamPasswordException exception) {
        System.out.println("Vao controller advice");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> invalidToken(InvalidTokenException exception) {
        System.out.println("Invalid token exception");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<?> unauthorize(UnauthorizeException exception) {
        System.out.println("Unauthorize controller advice");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
