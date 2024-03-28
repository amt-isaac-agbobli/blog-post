package com.isaac.blogpost.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException  extends RuntimeException{
    private final String statusCode;
    public HttpException(String message, String statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    public HttpException(String message, String statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
