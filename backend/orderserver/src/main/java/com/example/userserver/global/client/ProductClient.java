package com.example.userserver.global.client;

import com.example.userserver.global.api.response.BaseResponse;
import com.example.userserver.module.domain.product.dto.response.ProductRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productserver")
public interface ProductClient {
    @GetMapping("product/search/{productName}")
    BaseResponse<ProductRes> searchProduct(@PathVariable String productName);
}
