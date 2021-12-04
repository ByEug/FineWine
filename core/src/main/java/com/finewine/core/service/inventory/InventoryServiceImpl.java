package com.finewine.core.service.inventory;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.inventory.Inventory;
import com.finewine.core.inventory.InventoryDao;
import com.finewine.core.model.product.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryDao inventoryDao;

    @Override
    public Long save(Inventory inventory) {
        return inventoryDao.save(inventory);
    }

    @Override
    public Inventory getById(Long id) {
        Optional<Inventory> inventory;
        try {
            inventory = inventoryDao.findById(id);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(id.toString());
        }
        if (inventory.isPresent()) {
            return inventory.get();
        } else {
            throw new NoElementWithSuchIdException(id.toString());
        }
    }


}
