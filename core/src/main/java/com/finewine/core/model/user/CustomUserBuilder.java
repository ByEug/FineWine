package com.finewine.core.model.user;

import com.finewine.core.model.inventory.Inventory;

import java.math.BigDecimal;

public class CustomUserBuilder {

    private CustomUserDTO customUserDTO;
    private CustomUser customUser;
    private Inventory inventory;

    public CustomUserBuilder(CustomUserDTO customUserDTO, Inventory inventory) {
        this.customUserDTO = customUserDTO;
        this.inventory = inventory;
        customUser = new CustomUser();
    }

    public CustomUser getCustomUser() {
        customUser.setClientName(customUserDTO.getClientName());
        customUser.setClientSurname(customUserDTO.getClientSurname());
        customUser.setUsername(customUserDTO.getUsername());
        customUser.setPassword(customUserDTO.getPassword());
        customUser.setCurrentFundsBalance(BigDecimal.valueOf(0L));
        customUser.setEnabled(Boolean.TRUE);
        customUser.setInventory(inventory);
        return customUser;
    }
}
