package com.ps.inventoryservice.inventoryservice.service;

import com.ps.inventoryservice.inventoryservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);
}
