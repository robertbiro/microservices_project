package com.ps.productservice.controller;

import com.ps.productservice.dto.ProductRequest;
import com.ps.productservice.exceptions.ProductException;
import com.ps.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        try {
            return ResponseEntity.ok().body(productService.getAllProduct());
        } catch (ProductException e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }
}
