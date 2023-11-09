package com.ps.productservice.exceptions;

import org.springframework.http.HttpStatus;

public class ProductException extends Exception{
    private final HttpStatus httpStatus;

    public ProductException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
