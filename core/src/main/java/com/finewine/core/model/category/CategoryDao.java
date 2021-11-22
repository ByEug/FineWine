package com.finewine.core.model.category;

import java.util.Optional;

public interface CategoryDao {

    Optional<Category> findById(Long id);
}
