package com.finewine.core.model.inventory;

import java.util.Optional;

public interface InventoryItemDao {

    Optional<InventoryItem> findById(Long id);

    void save(Long stockId, Long productId, Long inventoryId);
}
