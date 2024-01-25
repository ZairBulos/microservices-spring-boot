package com.zair.orders_service.controllers;

import com.zair.orders_service.dtos.OrderRequestDTO;
import com.zair.orders_service.dtos.OrderResponseDTO;
import com.zair.orders_service.services.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @CircuitBreaker(name = "orders-service", fallbackMethod = "saveFallback")
    public ResponseEntity<OrderResponseDTO> save(@RequestBody OrderRequestDTO orderRequest) {
        return ResponseEntity.ok(service.save(orderRequest));
    }

    private ResponseEntity<OrderResponseDTO> saveFallback(OrderRequestDTO orderRequest, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
