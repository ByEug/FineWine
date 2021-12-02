package com.finewine.core.model.cart;

import com.finewine.core.model.product.Product;

import java.math.BigDecimal;

public class CartItem {

    private Product product;
    private Long quantity;
    private BigDecimal priceForQuantity;

    public CartItem(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
        priceForQuantity = BigDecimal.valueOf(quantity).multiply(product.getPrice());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceForQuantity() {
        return priceForQuantity;
    }

    public void setPriceForQuantity(BigDecimal priceForQuantity) {
        this.priceForQuantity = priceForQuantity;
    }
}
