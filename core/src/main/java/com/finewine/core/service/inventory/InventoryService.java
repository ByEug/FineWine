package com.finewine.core.service.inventory;

import com.finewine.core.model.inventory.Inventory;
import com.finewine.core.model.order.Order;

public interface InventoryService {

    Long save(Inventory inventory);

    Inventory getById(Long id);

    void createInventoryItemsFromOrderItems(Order order, String username);
}
