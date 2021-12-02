package com.finewine.core.model.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotNull
    private Long id;

    @Min(value = 1L)
    @NotNull
    private Long quantity;

    public ProductDTO() {

    }

    public ProductDTO(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
