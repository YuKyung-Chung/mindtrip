package com.example.userserver.module.domain.product.service;


import com.example.userserver.global.exception.BaseExceptionHandler;
import com.example.userserver.global.exception.code.ErrorCode;
import com.example.userserver.module.domain.product.Product;
import com.example.userserver.module.domain.product.dto.response.ProductRes;
import com.example.userserver.module.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    @Override
    public ProductRes getProductByProductName(String productName) {
        log.info("[ProductService]: getProductByProductName method accepted = {}", productName);
        Product product = productRepository.findByName(productName)
                .orElseThrow(
                        ()->new BaseExceptionHandler("No product",
                                ErrorCode.NOT_FOUND_ERROR)
                );

        return ProductRes.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .build();
    }
}
