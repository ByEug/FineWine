package com.finewine.core.service.user;

import com.finewine.core.model.role.Role;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.model.user.CustomUserDTO;

import java.util.List;

public interface CustomUserService {

    Long save(CustomUserDTO customUserDTO);

    CustomUser findByUsername(String username);

    void update(CustomUser customUser);

    void updateRole(CustomUser customUser, Role role);

    void deleteUser(String id);

    List<CustomUser> getAllUsers();

    CustomUser findById(String id);
}
