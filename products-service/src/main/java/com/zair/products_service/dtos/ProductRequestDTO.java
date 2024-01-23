package com.zair.products_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;
}
