package dev.tonslima.productapi.dto;

import dev.tonslima.productapi.model.Product;
import org.mapstruct.Mapper;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    Product toEntity(ProductUpdateDTO dto);

    ProductRespDTO toDTO(Product product);
}
