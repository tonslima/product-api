package dev.tonslima.productapi.dto;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        String name,
        String description,
        BigDecimal price
) {
}
