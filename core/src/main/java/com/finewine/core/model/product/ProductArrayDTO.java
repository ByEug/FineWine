package com.finewine.core.model.product;

import com.finewine.core.model.cart.CartItem;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class ProductArrayDTO {

    @Valid
    private List<ProductDTO> productDTOItems;

    public ProductArrayDTO() {

    }

    public ProductArrayDTO(List<CartItem> cartItems) {
        productDTOItems = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            productDTOItems.add(new ProductDTO(cartItem.getProduct().getId(), cartItem.getQuantity()));
        });
    }

    public List<ProductDTO> getProductDTOItems() {
        return productDTOItems;
    }

    public void setProductDTOItems(List<ProductDTO> productDTOItems) {
        this.productDTOItems = productDTOItems;
    }
}
