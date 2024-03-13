package com.example.userserver.module.domain.orders.repository;

import com.example.userserver.module.domain.orders.Orders;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	Optional<Orders> findByOrdersId(int ordersId);
	void deleteOrdersByOrdersId(int ordersId);

}
