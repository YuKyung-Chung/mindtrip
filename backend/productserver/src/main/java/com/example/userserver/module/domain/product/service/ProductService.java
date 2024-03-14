package com.example.userserver.module.domain.product.service;


import com.example.userserver.module.domain.product.dto.response.ProductRes;

public interface ProductService {
    ProductRes getProductByProductName(String productName);
}
