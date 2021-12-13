package com.finewine.core.model.order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Optional<Order> getOrder(Long id);

    Integer checkCountForUser(Long userId);

    List<Order> getOrdersForUserId(Long userId);

    Long saveInventoryOrder(Order order);

    Long saveDeliveryOrderGuest(Order order, Long addressId);

    Long saveDeliveryOrderAuth(Order order, Long addressId);

    List<Order> getAllOrders();

    void updateOrderStatus(Long orderId, OrderStatus status);
}
