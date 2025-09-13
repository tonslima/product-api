package dev.tonslima.productapi.service;

import dev.tonslima.productapi.exception.DuplicateProductException;
import dev.tonslima.productapi.model.Product;
import dev.tonslima.productapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product for id '" + id + "' not found"));
    }

    @Transactional
    public Product create(Product product) throws DuplicateProductException {
        if (productRepository.existsByName(product.getName())) {
            throw new DuplicateProductException("Product with name '" + product.getName() + "' already exists");
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product, Long id) throws EntityNotFoundException {
        var updatedProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product for id '" + id + "' not found"));

        updatedProduct.update(product.getName(), product.getDescription(), product.getPrice());
        return productRepository.save(updatedProduct);
    }
}
