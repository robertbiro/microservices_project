package com.ps.productservice.service;
import com.ps.productservice.dto.ProductRequest;
import com.ps.productservice.dto.ProductResponse;
import com.ps.productservice.exceptions.ProductException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProduct() throws ProductException;
}
