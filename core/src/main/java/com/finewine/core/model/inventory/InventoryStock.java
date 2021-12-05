package com.finewine.core.model.inventory;

public class InventoryStock {

    private Long id;
    private Long stock;

    public InventoryStock() {

    }

    public InventoryStock(Long stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
