package com.backend.spades.mapper;

import com.backend.spades.dto.ProductDto;
import com.backend.spades.model.Product;
import java.util.List;

public class ProductMapper {
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setImage(product.getImage());
        return productDto;
    }

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setImage(productDto.getImage());
        return product;
    }

    public List<ProductDto> listToDto(List<Product> products) {
        return products.stream().map(this::toDto).toList();
    }

    public List<Product> listToEntity(List<ProductDto> productDtos) {
        return productDtos.stream().map(this::toEntity).toList();
    }
}
