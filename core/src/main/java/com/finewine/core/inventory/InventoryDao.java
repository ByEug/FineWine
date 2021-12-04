package com.finewine.core.inventory;

import java.util.Optional;

public interface InventoryDao {

    Optional<Inventory> findById(Long id);

    Long save(Inventory inventory);
}
