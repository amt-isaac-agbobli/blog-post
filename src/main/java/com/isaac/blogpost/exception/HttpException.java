package com.isaac.blogpost.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException  extends RuntimeException{
    private final int statusCode;
    public HttpException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    public HttpException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
