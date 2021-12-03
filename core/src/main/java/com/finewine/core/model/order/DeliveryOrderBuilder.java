package com.finewine.core.model.order;

import com.finewine.core.model.address.Address;
import com.finewine.core.model.cart.Cart;

import java.math.BigDecimal;

public class DeliveryOrderBuilder {

    private Cart cart;
    private OrderFullDataDTO orderFullDataDTO;
    private Order order;
    private Long deliveryPrice;
    private Address address;


    public DeliveryOrderBuilder(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice) {
        this.cart = cart;
        this.orderFullDataDTO = orderFullDataDTO;
        order = new Order();
        address = new Address();
        this.deliveryPrice = deliveryPrice;
    }

    public Order getOrder() {
        order.setFirstName(orderFullDataDTO.getFirstName());
        order.setLastName(orderFullDataDTO.getLastName());
        order.setPhoneNumber(orderFullDataDTO.getPhone());
        order.setAdditionalInformation(orderFullDataDTO.getAdditionalInformation());
        address.setLocality(orderFullDataDTO.getAddressDTO().getLocality());
        address.setStreet(orderFullDataDTO.getAddressDTO().getStreet());
        address.setHomeNumber(orderFullDataDTO.getAddressDTO().getHomeNumber());
        address.setFlatNumber(orderFullDataDTO.getAddressDTO().getFlatNumber());
        order.setAddress(address);
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem(cartItem, order);
            order.getOrderItems().add(orderItem);
        });
        order.setSubtotalPrice(cart.getTotalCost());
        order.setDeliveryPrice(BigDecimal.valueOf(deliveryPrice));
        order.setTotalPrice(order.getSubtotalPrice().add(order.getDeliveryPrice()));
        order.setOrderType(OrderType.Delivery);
        order.setOrderStatus(OrderStatus.Created);
        return order;
    }
}
