package com.finewine.core.model.shopstock;

import java.util.Optional;

public interface ShopStockDao {

    Optional<ShopStock> findById(Long id);
}
