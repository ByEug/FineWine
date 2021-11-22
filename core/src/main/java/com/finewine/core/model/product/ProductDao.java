package com.finewine.core.model.product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll(int offset, int limit);

    List<Product> findAllNoLimit();

    Optional<Product> findById(Long id);
}
