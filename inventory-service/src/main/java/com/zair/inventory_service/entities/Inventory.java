package com.zair.inventory_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private Long quantity;
}
