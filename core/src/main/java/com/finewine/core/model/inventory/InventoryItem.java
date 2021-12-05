package com.finewine.core.model.inventory;

import com.finewine.core.model.product.Product;

public class InventoryItem {

    private Long id;
    private InventoryStock inventoryStock;
    private Product product;
    private Inventory inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InventoryStock getInventoryStock() {
        return inventoryStock;
    }

    public void setInventoryStock(InventoryStock inventoryStock) {
        this.inventoryStock = inventoryStock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
