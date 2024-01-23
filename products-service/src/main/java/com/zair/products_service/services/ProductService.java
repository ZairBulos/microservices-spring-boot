package com.zair.products_service.services;

import com.zair.products_service.dtos.ProductRequestDTO;
import com.zair.products_service.dtos.ProductResponseDTO;
import com.zair.products_service.entities.Product;
import com.zair.products_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public void save(ProductRequestDTO productRequest) {
        Product product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();

        productRepository.save(product);

        log.info("Product added: {}", product);
    }

    private ProductResponseDTO mapToProductResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
