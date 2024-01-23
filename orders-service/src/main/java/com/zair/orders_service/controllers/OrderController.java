package com.zair.orders_service.controllers;

import com.zair.orders_service.dtos.OrderRequestDTO;
import com.zair.orders_service.dtos.OrderResponseDTO;
import com.zair.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody OrderRequestDTO orderRequest) {
        service.save(orderRequest);
    }
}
