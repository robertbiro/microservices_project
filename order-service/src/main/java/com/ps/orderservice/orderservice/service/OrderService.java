package com.ps.orderservice.orderservice.service;

import com.ps.orderservice.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    void placeOrder(OrderRequest orderRequest);
}
