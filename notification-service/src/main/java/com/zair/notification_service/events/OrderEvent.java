package com.zair.notification_service.events;

import com.zair.notification_service.enums.OrderStatus;

public record OrderEvent(
        String orderNumber,
        int itemsCount,
        OrderStatus orderStatus
) {
}
