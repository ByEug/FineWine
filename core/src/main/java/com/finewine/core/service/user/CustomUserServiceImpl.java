package com.finewine.core.service.user;

import com.finewine.core.exception.EmailAlreadyRegisteredException;
import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.inventory.Inventory;
import com.finewine.core.model.role.Role;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.model.user.CustomUserBuilder;
import com.finewine.core.model.user.CustomUserDTO;
import com.finewine.core.model.user.CustomUserDao;
import com.finewine.core.service.inventory.InventoryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    @Override
    public CustomUser findByUsername(String username) {
        Optional<CustomUser> customUser;
        try {
            customUser = customUserDao.findByUsername(username);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(username);
        }
        if (customUser.isPresent()) {
            return customUser.get();
        } else {
            throw new NoElementWithSuchIdException(username);
        }
    }

    @Override
    public void update(CustomUser customUser) {
        customUserDao.update(customUser);
    }

    @Override
    public void updateRole(CustomUser customUser, Role role) {
        customUserDao.updateRole(customUser, role);
    }

    @Override
    public void deleteUser(String id) {
        try {
            long longId;
            longId = Long.parseLong(id);
            customUserDao.deleteUser(longId);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(id);
        }
    }

    @Override
    public List<CustomUser> getAllUsers() {
        return customUserDao.getAllUsers();
    }

    @Override
    public CustomUser findById(String id) {
        Optional<CustomUser> customUser;
        try {
            long longId;
            longId = Long.parseLong(id);
            customUser = customUserDao.findById(longId);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(id);
        }
        if (customUser.isPresent()) {
            return customUser.get();
        } else {
            throw new NoElementWithSuchIdException(id);
        }
    }

    private boolean checkEmailAvailability(String email) {
        return customUserDao.countByUsername(email) <= 0;
    }
}
