package com.zair.orders_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders_items")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private Double price;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
