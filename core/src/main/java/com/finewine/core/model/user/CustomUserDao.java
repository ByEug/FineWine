package com.finewine.core.model.user;

import java.util.Optional;

public interface CustomUserDao {

    Optional<CustomUser> findById(Long id);

    Optional<CustomUser> findByUsername(String username);

    Integer countByUsername(String username);

    Long save(CustomUser customUser, Long inventoryId);

    void update(CustomUser customUser);
}
