package com.zair.orders_service.services;

import com.zair.orders_service.dtos.*;
import com.zair.orders_service.entities.Order;
import com.zair.orders_service.entities.OrderItem;
import com.zair.orders_service.enums.OrderStatus;
import com.zair.orders_service.events.OrderEvent;
import com.zair.orders_service.repositories.OrderRepository;
import com.zair.orders_service.utils.JsonUtil;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObservationRegistry observationRegistry;

    public List<OrderResponseDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    public OrderResponseDTO save(OrderRequestDTO orderRequest) {
        Observation inventoryObservation = Observation.createNotStarted("inventory-service", observationRegistry);

        return inventoryObservation.observe(() -> {
            // Check Inventory
            BaseResponseDTO result = webClientBuilder.build()
                    .post()
                    .uri("lb://inventory-service/api/inventories/in-stock")
                    .bodyValue(orderRequest.getOrderItems())
                    .retrieve()
                    .bodyToMono(BaseResponseDTO.class)
                    .block();

            if (result != null && !result.hasErrors()) {
                // Create Order
                Order order = new Order();
                order.setOrderNumber(UUID.randomUUID().toString());
                order.setOrderItems(orderRequest.getOrderItems()
                        .stream()
                        .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                        .toList()
                );

                // Save Order
                Order savedOrder = orderRepository.save(order);

                // Order Topic
                OrderEvent orderEvent = new OrderEvent(
                        savedOrder.getOrderNumber(),
                        savedOrder.getOrderItems().size(),
                        OrderStatus.PLACED
                );

                kafkaTemplate.send("orders-topic", JsonUtil.toJSON(orderEvent));

                return mapToOrderResponse(savedOrder);
            } else {
                throw new IllegalArgumentException("Some of the products are not in stock.");
            }
        });
    }

    private OrderResponseDTO mapToOrderResponse(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemResponse).toList()
        );
    }

    private OrderItemResponseDTO mapToOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getSku(),
                orderItem.getPrice(),
                orderItem.getQuantity()
        );
    }

    private OrderItem mapOrderItemRequestToOrderItem(OrderItemRequestDTO orderItemRequest, Order order) {
        return OrderItem.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }
}
