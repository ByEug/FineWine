package com.finewine.core.model.country;

import java.util.List;
import java.util.Optional;

public interface CountryDao {

    List<Country> findAll();

    Optional<Country> findById(Long id);
}
