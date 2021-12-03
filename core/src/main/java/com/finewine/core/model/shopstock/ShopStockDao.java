package com.finewine.core.model.shopstock;

import java.util.Optional;

public interface ShopStockDao {

    Optional<ShopStock> findById(Long id);

    void update(final Long key, final Long stock, final Long reserved);
}
