package com.finewine.core.model.order;

import com.finewine.core.model.cart.CartItem;
import com.finewine.core.model.product.Product;

public class OrderItem {

    private Long id;
    private Product product;
    private Order order;
    private Long quantity;

    public OrderItem(Long id, Product product, Order order, Long quantity) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderItem(CartItem cartItem, Order order) {
        this.product = cartItem.getProduct();
        this.order = order;
        this.quantity = cartItem.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }
}
