package com.zair.inventory_service.repositories;

import com.zair.inventory_service.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySku(String sku);

    List<Inventory> findAllBySkuIn(List<String> skus);
}
