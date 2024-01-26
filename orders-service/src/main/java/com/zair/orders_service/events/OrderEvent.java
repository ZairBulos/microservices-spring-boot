package com.zair.orders_service.events;

import com.zair.orders_service.enums.OrderStatus;

public record OrderEvent(
        String orderNumber,
        int itemsCount,
        OrderStatus orderStatus
) {
}
