package com.finewine.core.service.user;

import com.finewine.core.exception.EmailAlreadyRegisteredException;
import com.finewine.core.inventory.Inventory;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.model.user.CustomUserBuilder;
import com.finewine.core.model.user.CustomUserDTO;
import com.finewine.core.model.user.CustomUserDao;
import com.finewine.core.service.inventory.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    @Resource
    private CustomUserDao customUserDao;

    @Resource
    private InventoryService inventoryService;

    @Override
    public Long save(CustomUserDTO customUserDTO) {
        if (checkEmailAvailability(customUserDTO.getUsername())) {
            Inventory inventory = new Inventory();
            inventory.setOverallQuantity(0);
            Long inventoryId = inventoryService.save(inventory);
            inventory = inventoryService.getById(inventoryId);
            CustomUserBuilder customUserBuilder = new CustomUserBuilder(customUserDTO, inventory);
            CustomUser customUser = customUserBuilder.getCustomUser();
            return customUserDao.save(customUser, inventoryId);
        } else {
            throw new EmailAlreadyRegisteredException(customUserDTO.getUsername());
        }
    }

    private boolean checkEmailAvailability(String email) {
        return customUserDao.countByUsername(email) <= 0;
    }
}
