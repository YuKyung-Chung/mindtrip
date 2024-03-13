package com.example.userserver.module.domain.orders.service;
import com.example.userserver.global.api.response.BaseResponse;
import com.example.userserver.global.client.ProductClient;
import com.example.userserver.module.domain.orders.response.OrderRes;
import com.example.userserver.module.domain.product.dto.response.ProductRes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductClient productClient;

    @Override
    public OrderRes insertOrder(String productName) {
        log.info("[OrderService]: insertOrder method accepted = {}", productName);
        BaseResponse<ProductRes> productRes = productClient.searchProduct(productName);

        System.out.println(productRes.getMessage());
        System.out.println(productRes.getResult());


        return null;
    }
}
