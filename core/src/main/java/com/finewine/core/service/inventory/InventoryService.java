package com.finewine.core.service.inventory;

import com.finewine.core.inventory.Inventory;

public interface InventoryService {

    Long save(Inventory inventory);

    Inventory getById(Long id);
}
