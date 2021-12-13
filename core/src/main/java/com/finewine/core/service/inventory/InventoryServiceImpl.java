package com.finewine.core.service.inventory;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.inventory.*;
import com.finewine.core.model.logs.inventory.InventoryLog;
import com.finewine.core.model.logs.inventory.InventoryLogAction;
import com.finewine.core.model.logs.inventory.InventoryLogDao;
import com.finewine.core.model.order.Order;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryDao inventoryDao;

    @Resource
    private InventoryStockDao inventoryStockDao;

    @Resource
    private InventoryItemDao inventoryItemDao;

    @Resource
    private InventoryLogDao inventoryLogDao;

    @Resource
    private CustomUserService customUserService;

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

    @Override
    public void createInventoryItemsFromOrderItems(Order order, String username) {
        CustomUser customUser = customUserService.findByUsername(username);
        AtomicReference<Long> overallQuantity = new AtomicReference<>(0L);
        order.getOrderItems().forEach(orderItem -> {
            Long quantity = orderItem.getQuantity();
            overallQuantity.updateAndGet(v -> v + quantity);
            InventoryStock inventoryStock = new InventoryStock(quantity);
            Long stockId = inventoryStockDao.save(inventoryStock);
            Long inventoryItemId = inventoryItemDao.save(stockId, orderItem.getProduct().getId(), customUser.getInventory().getId());
            InventoryLog inventoryLog = new InventoryLog();
            inventoryLog.setCreatingDate(Date.valueOf(LocalDate.now()));
            inventoryLog.setAction(InventoryLogAction.Adding);
            inventoryLog.setInventoryItem(inventoryItemDao.findById(inventoryItemId).get());
            inventoryLogDao.save(inventoryLog, customUser.getId());
        });
        inventoryDao.update(customUser.getInventory().getOverallQuantity() + overallQuantity.get(),
                customUser.getInventory().getId());
    }

    @Override
    public List<InventoryItem> getInventoryItemsForUserInventory(Long inventoryId) {
        if (inventoryItemDao.checkCountForInventory(inventoryId) > 0) {
            return inventoryItemDao.getItemsForInventoryId(inventoryId);
        } else {
            return new ArrayList<>();
        }
    }


}
