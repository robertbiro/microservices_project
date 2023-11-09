package com.ps.productservice.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ProductException{

    public ProductNotFoundException() {
        super("The list is empty", HttpStatus.NO_CONTENT);
    }
}
