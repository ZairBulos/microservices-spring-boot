package com.zair.orders_service.dtos;

public record OrderItemResponseDTO(
        Long id,
        String sku,
        Double price,
        Long quantity
) {
}
