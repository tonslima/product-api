package dev.tonslima.productapi.controller;

import dev.tonslima.productapi.dto.ApiResponse;
import dev.tonslima.productapi.dto.ProductDTO;
import dev.tonslima.productapi.model.Product;
import dev.tonslima.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAll() {
        List<Product> products = productService.getAll();
        List<ProductDTO> productsDTO = products.stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok(ApiResponse.of(productsDTO));
    }
}
