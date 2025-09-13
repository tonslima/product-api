package dev.tonslima.productapi.dto;

import dev.tonslima.productapi.model.Product;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        String description,
        BigDecimal price
) {
    public ProductDTO(Product product) {
        this(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }
}
