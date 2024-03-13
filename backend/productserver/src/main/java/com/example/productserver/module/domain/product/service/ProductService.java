package com.example.productserver.module.domain.product.service;

import com.example.productserver.module.domain.product.dto.response.ProductRes;

public interface ProductService {
    ProductRes getProductByProductName(String productName);
}
