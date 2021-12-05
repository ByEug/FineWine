package com.finewine.core.service.user;

import com.finewine.core.model.user.CustomUser;
import com.finewine.core.model.user.CustomUserDTO;

public interface CustomUserService {

    Long save(CustomUserDTO customUserDTO);

    CustomUser findByUsername(String username);

    void update(CustomUser customUser);
}
