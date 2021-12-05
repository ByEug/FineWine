package com.finewine.core.model.inventory;

import java.util.Optional;

public interface InventoryDao {

    Optional<Inventory> findById(Long id);

    Long save(Inventory inventory);

    void update(Long overallQuantity, Long id);
}
