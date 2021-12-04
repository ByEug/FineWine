package com.finewine.core.service.order;

import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.order.Order;
import com.finewine.core.model.order.OrderFullDataDTO;
import com.finewine.core.model.order.PreOrderDataDTO;

public interface OrderService {

    Order getOrder(String id);

    Long placeInventoryOrder(Cart cart, PreOrderDataDTO preOrderDataDTO);

    Long createDeliveryPreOrderForAuth(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice, String username);

    Long createDeliveryPreOrderForGuest(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice);
}
