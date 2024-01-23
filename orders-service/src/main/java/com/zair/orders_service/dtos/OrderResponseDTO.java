package com.zair.orders_service.dtos;

import java.util.List;

public record OrderResponseDTO(
        Long id,
        String orderNumber,
        List<OrderItemResponseDTO> orderItems) {
}
