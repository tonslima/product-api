package dev.tonslima.productapi.controller;

import dev.tonslima.productapi.dto.ApiResponse;
import dev.tonslima.productapi.dto.ProductDTO;
import dev.tonslima.productapi.exception.DuplicateProductException;
import dev.tonslima.productapi.model.Product;
import dev.tonslima.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        ProductDTO productDTO = new ProductDTO(product);

        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) throws DuplicateProductException {
        Product product = new Product(productDTO);
        Product createdProduct = productService.create(product);

        var uri = UriComponentsBuilder.fromPath("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductDTO(createdProduct));
    }
}
