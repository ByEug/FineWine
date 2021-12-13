package com.finewine.core.model.logs.inventory;

import java.util.List;

public interface InventoryLogDao {

    List<InventoryLog> getForUserId(Long userId);

    void save(InventoryLog inventoryLog, Long userId);
}
