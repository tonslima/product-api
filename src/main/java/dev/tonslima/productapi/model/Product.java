package dev.tonslima.productapi.model;

import dev.tonslima.productapi.dto.ProductRespDTO;
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
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product(ProductRespDTO productRespDTO) {
        this.name = productRespDTO.name();
        this.description = productRespDTO.description();
        this.price = productRespDTO.price();
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void update(String name, String description, BigDecimal price) {
        if  (!this.name.equals(name)) {
            this.name = name;
        }
        if (!this.description.equals(description)) {
            this.description = description;
        }
        if (!this.price.equals(price)) {
            this.price = price;
        }
    }
}
