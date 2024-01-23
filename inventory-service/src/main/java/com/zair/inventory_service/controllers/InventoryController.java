package com.zair.inventory_service.controllers;

import com.zair.inventory_service.dtos.BaseResponseDTO;
import com.zair.inventory_service.dtos.OrderItemRequestDTO;
import com.zair.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@PathVariable String sku) {
        return service.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO areInStock(@RequestBody List<OrderItemRequestDTO> orderItemsRequest) {
        return service.areInStock(orderItemsRequest);
    }
}
