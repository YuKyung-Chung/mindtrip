package com.example.userserver.module.domain.product.repository;

import com.example.userserver.module.domain.product.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Optional<Product> findByName(String productName);

}
