package com.finewine.core.model.role;

import java.util.Optional;

public interface RoleDao {

    Optional<Role> getById(Long id);
}
