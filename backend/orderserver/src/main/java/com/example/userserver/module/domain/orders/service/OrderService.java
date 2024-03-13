package com.example.userserver.module.domain.orders.service;


import com.example.userserver.module.domain.orders.response.OrderRes;

public interface OrderService {
    OrderRes insertOrder(String productName);
}
