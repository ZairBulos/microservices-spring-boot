package com.zair.orders_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderItemRequestDTO {
    private Long id;
    private String sku;
    private Double price;
    private Long quantity;
}
