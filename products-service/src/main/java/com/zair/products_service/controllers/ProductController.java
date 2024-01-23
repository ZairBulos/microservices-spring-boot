package com.zair.products_service.controllers;

import com.zair.products_service.dtos.ProductRequestDTO;
import com.zair.products_service.dtos.ProductResponseDTO;
import com.zair.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProductRequestDTO productRequest) {
        service.save(productRequest);
    }
}
