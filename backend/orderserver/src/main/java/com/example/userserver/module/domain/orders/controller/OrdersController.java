package com.example.userserver.module.domain.orders.controller;

import com.example.userserver.global.api.response.BaseResponse;
import com.example.userserver.global.exception.code.SuccessCode;
import com.example.userserver.module.domain.orders.service.OrderService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "review", description = "리뷰 API")
@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrdersController {

    private final OrderService orderService;

//    @Operation(summary = "health check")
    @GetMapping("/welcome")
    public ResponseEntity<BaseResponse<String>> registerReview() throws IOException {

        return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its orderserver");
    }

//    @Operation(summary = "상품구매")
    @GetMapping("/buy")
    public ResponseEntity<BaseResponse<Integer>> registerReview(
            @RequestParam("productName") String productName) throws IOException {

        orderService.insertOrder(productName);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, 1);
    }
}
