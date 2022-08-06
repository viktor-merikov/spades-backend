package com.backend.spades.controller;

import com.backend.spades.dto.ProductDto;
import com.backend.spades.exception.NotFoundException;
import com.backend.spades.mapper.ProductMapper;
import com.backend.spades.model.Product;
import com.backend.spades.repository.ProductRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapper();

    @GetMapping
    public List<ProductDto> getAll() {
        return productMapper.listToDto(productRepository.findAll());
    }
        //TODO: create Category repository and get products throw category
    @GetMapping(params = "category")
    public List<ProductDto> getByCategory(@RequestParam String category) {
        List<Product> productByCategory = productRepository.getProductByCategory(category);
        if (productByCategory == null || productByCategory.isEmpty()) {
            throw new NotFoundException("This category is empty");
        }
        return productMapper.listToDto(productByCategory);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product with id: " + id + "not found");
        }
        return optionalProduct.get();
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public Product update(@Valid @RequestBody Product product) {
        if (product.getId() == null) {
            throw new NotFoundException("Product ID is null");
        }
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product with id: " + product.getId() + "not found");
        }
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
