package dev.tonslima.productapi.dto;

import java.math.BigDecimal;

public record ProductDTO(
        String name,
        String description,
        BigDecimal price
) {
}
