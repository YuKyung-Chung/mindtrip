package com.example.userserver.module.domain.product.controller;


import com.example.userserver.global.api.response.BaseResponse;
import com.example.userserver.global.exception.code.SuccessCode;
import com.example.userserver.module.domain.product.dto.response.ProductRes;
import com.example.userserver.module.domain.product.service.ProductService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "review", description = "")
@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

//    @Operation(summary = "health check")
    @GetMapping("/welcome")
    public ResponseEntity<BaseResponse<String>> registerReview() throws IOException {

        return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its productservice");
    }

//    @Operation(summary = "상품 검색")
    @GetMapping("/search/{productName}")
    public ResponseEntity<BaseResponse<ProductRes>> searchProduct(
            @PathVariable("productName") String productName) throws IOException {
        System.out.println(productName);
        ProductRes productRes = productService.getProductByProductName(productName);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, productRes);
    }
}
