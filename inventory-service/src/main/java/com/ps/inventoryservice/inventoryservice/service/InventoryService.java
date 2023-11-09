package com.ps.inventoryservice.inventoryservice.service;

import org.springframework.stereotype.Service;

@Service
public interface InventoryService {

    boolean isInStock(String skuCode);
}
