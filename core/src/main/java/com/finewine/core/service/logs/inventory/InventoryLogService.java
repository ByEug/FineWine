package com.finewine.core.service.logs.inventory;

import com.finewine.core.model.logs.inventory.InventoryLog;

import java.util.List;

public interface InventoryLogService {

    List<InventoryLog> getForUserId(Long userId);

    void save(InventoryLog inventoryLog, Long userId);
}
