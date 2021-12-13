package com.finewine.core.model.inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryItemDao {

    Optional<InventoryItem> findById(Long id);

    Long save(Long stockId, Long productId, Long inventoryId);

    List<InventoryItem> getItemsForInventoryId(Long inventoryId);

    Integer checkCountForInventory(Long inventoryId);

    void updateOnSell(Long id, Boolean value);

    void updateInventory(Long inventoryId, Long inventoryItemId);
}
