package dev.tonslima.productapi.model;

import dev.tonslima.productapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.description = productDTO.description();
        this.price = productDTO.price();
    }
}
