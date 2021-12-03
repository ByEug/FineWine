package com.finewine.core.model.order;

import java.util.Optional;

public interface OrderDao {

    Optional<Order> getOrder(Long id);

    Long saveInventoryOrder(Order order);

    Long saveDeliveryOrder(Order order, Long addressId);
}
