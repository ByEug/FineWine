package com.finewine.core.model.inventory;

import java.util.Optional;

public interface InventoryStockDao {

    Optional<InventoryStock> findById(Long id);

    Long save(InventoryStock inventoryStock);
}
