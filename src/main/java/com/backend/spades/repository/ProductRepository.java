package com.backend.spades.repository;

import com.backend.spades.model.Product;
import io.micrometer.core.lang.Nullable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Nullable
    public List<Product> getProductByCategory(String category);
}
