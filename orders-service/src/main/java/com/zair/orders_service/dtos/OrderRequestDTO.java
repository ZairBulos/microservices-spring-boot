package com.zair.orders_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderRequestDTO {
    private List<OrderItemRequestDTO> orderItems;
}
