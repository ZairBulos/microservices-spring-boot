package com.zair.notification_service.listeners;

import com.zair.notification_service.events.OrderEvent;
import com.zair.notification_service.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {

    @KafkaListener(topics = "orders-topic")
    public void handleOrdersNotification(String message) {
        OrderEvent orderEvent = JsonUtil.fromJSON(message, OrderEvent.class);

        log.info(
                "Order {} event received for order : {} with {} items",
                orderEvent.orderStatus(),
                orderEvent.orderNumber(),
                orderEvent.itemsCount()
        );
    }
}
