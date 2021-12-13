package com.finewine.core.model.logs.inventory;

import com.finewine.core.model.inventory.InventoryItem;
import com.finewine.core.model.logs.AbstractLog;

public class InventoryLog extends AbstractLog {

    private InventoryLogAction action;
    private InventoryItem inventoryItem;

    public InventoryLogAction getAction() {
        return action;
    }

    public void setAction(InventoryLogAction action) {
        this.action = action;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
}
