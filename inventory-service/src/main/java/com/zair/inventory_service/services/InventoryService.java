package com.zair.inventory_service.services;

import com.zair.inventory_service.dtos.BaseResponseDTO;
import com.zair.inventory_service.dtos.OrderItemRequestDTO;
import com.zair.inventory_service.entities.Inventory;
import com.zair.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Boolean isInStock(String sku) {
        Optional<Inventory> inventory = inventoryRepository.findBySku(sku);
        return inventory.filter(i -> i.getQuantity() > 0).isPresent();
    }

    public BaseResponseDTO areInStock(List<OrderItemRequestDTO> orderItemsRequest) {
        List<String> errorList = new ArrayList<>();

        List<String> skus = orderItemsRequest.stream().map(OrderItemRequestDTO::getSku).toList();

        List<Inventory> inventories = inventoryRepository.findAllBySkuIn(skus);

        orderItemsRequest.forEach(orderItem -> {
            Optional<Inventory> inventory = inventories.stream()
                    .filter(i -> i.getSku().equals(orderItem.getSku()))
                    .findFirst();

            if (inventory.isEmpty()) {
                errorList.add("Product with sku " + orderItem.getSku() + " does not exist");
            } else {
                errorList.add("Product with sku " + orderItem.getSku() + " has insufficient quantity");
            }

        });

        return errorList.size() > 0
                ? new BaseResponseDTO(errorList.toArray(new String[0]))
                : new BaseResponseDTO(null);
    }
}
