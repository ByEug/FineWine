package com.finewine.core.model.order;

import com.finewine.core.model.cart.Cart;

import java.math.BigDecimal;

public class InventoryOrderBuilder {

    private Cart cart;
    private PreOrderDataDTO preOrderDataDTO;
    private Order order;

    public InventoryOrderBuilder(Cart cart, PreOrderDataDTO preOrderDataDTO) {
        this.cart = cart;
        this.preOrderDataDTO = preOrderDataDTO;
        order = new Order();
    }

    public Order getOrder() {
        order.setFirstName(preOrderDataDTO.getFirstName());
        order.setLastName(preOrderDataDTO.getLastName());
        order.setPhoneNumber(preOrderDataDTO.getPhone());
        order.setAdditionalInformation(preOrderDataDTO.getAdditionalInformation());
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem(cartItem, order);
            order.getOrderItems().add(orderItem);
        });
        order.setSubtotalPrice(cart.getTotalCost());
        order.setDeliveryPrice(BigDecimal.valueOf(0L));
        order.setTotalPrice(order.getSubtotalPrice().add(order.getDeliveryPrice()));
        order.setOrderType(OrderType.Inventory);
        order.setOrderStatus(OrderStatus.Created);
        return order;
    }
}
