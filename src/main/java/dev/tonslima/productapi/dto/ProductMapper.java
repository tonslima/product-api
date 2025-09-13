package dev.tonslima.productapi.dto;

import dev.tonslima.productapi.model.Product;

public class ProductMapper {

    public static Product toEntity(ProductDTO dto) {
        return new Product(
                dto.name(),
                dto.description(),
                dto.price());
    }

    public static ProductRespDTO toDTO(Product product) {
        return new ProductRespDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }
}
