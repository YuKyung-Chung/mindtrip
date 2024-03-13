package com.example.userserver.module.domain.orders.response;

public record OrderRes(
        int ordersId,
        int clientId,
        int productId,
        String productName
) {

}
