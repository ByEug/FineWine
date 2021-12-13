package com.finewine.core.model.user;

import com.finewine.core.model.role.Role;

import java.util.List;
import java.util.Optional;

public interface CustomUserDao {

    Optional<CustomUser> findById(Long id);

    Optional<CustomUser> findByUsername(String username);

    Integer countByUsername(String username);

    Long save(CustomUser customUser, Long inventoryId);

    void update(CustomUser customUser);

    void updateRole(CustomUser customUser, Role role);

    void deleteUser(Long id);

    List<CustomUser> getAllUsers();
}
