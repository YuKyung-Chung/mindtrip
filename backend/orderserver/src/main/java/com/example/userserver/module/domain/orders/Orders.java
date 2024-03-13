package com.example.userserver.module.domain.orders;

import com.example.userserver.module.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orders_id")
	private int ordersId;

	private int clientId;
	private int productId;

	public Orders createOrders(int clientId, int productId) {
		Orders order = new Orders();
		order.setClientId(clientId);
		order.setProductId(productId);
		return order;
	}
}

