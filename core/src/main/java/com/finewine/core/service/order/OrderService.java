package com.finewine.core.service.order;

import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.order.Order;
import com.finewine.core.model.order.OrderFullDataDTO;
import com.finewine.core.model.order.OrderStatus;
import com.finewine.core.model.order.PreOrderDataDTO;

import java.util.List;

public interface OrderService {

    Order getOrder(String id);

    List<Order> getOrderListForUser(Long userId);

    Long placeInventoryOrder(Cart cart, PreOrderDataDTO preOrderDataDTO, String username);

    Long createDeliveryPreOrderForAuth(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice, String username);

    Long createDeliveryPreOrderForGuest(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice);

    List<Order> getAllOrders();

    void updateOrderStatus(Long orderId, OrderStatus status);
}
