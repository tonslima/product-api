package dev.tonslima.productapi.dto;

import java.math.BigDecimal;

public record ProductRespDTO(
        Long id,
        String name,
        String description,
        BigDecimal price
) {
}
