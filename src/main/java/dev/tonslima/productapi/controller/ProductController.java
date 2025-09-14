package dev.tonslima.productapi.controller;

import dev.tonslima.productapi.dto.*;
import dev.tonslima.productapi.exception.DuplicateProductException;
import dev.tonslima.productapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductRespDTO>>> getAll() {
        var products = productService.getAll();
        var dtoRespList = products.stream()
                .map(productMapper::toDTO)
                .toList();

        return ResponseEntity.ok(ApiResponse.of(dtoRespList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRespDTO> getById(@PathVariable Long id) {
        var product = productService.getById(id);
        var dtoResp = productMapper.toDTO(product);

        return ResponseEntity.ok(dtoResp);
    }

    @PostMapping
    public ResponseEntity<ProductRespDTO> create(@Valid @RequestBody ProductCreateDTO dto) throws DuplicateProductException {
        var product = productMapper.toEntity(dto);
        var createdProduct = productService.create(product);

        var uri = UriComponentsBuilder.fromPath("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(productMapper.toDTO(createdProduct));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductRespDTO> update(@RequestBody ProductUpdateDTO dto, @PathVariable Long id) {
        var product = productMapper.toEntity(dto);
        var updatedProduct = productService.update(product, id);

        return ResponseEntity.ok(productMapper.toDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
