package com.finewine.core.service.logs.inventory;

import com.finewine.core.model.logs.inventory.InventoryLog;
import com.finewine.core.model.logs.inventory.InventoryLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryLogServiceImpl implements InventoryLogService {

    @Resource
    private InventoryLogDao inventoryLogDao;

    @Override
    public List<InventoryLog> getForUserId(Long userId) {
        return inventoryLogDao.getForUserId(userId);
    }

    @Override
    public void save(InventoryLog inventoryLog, Long userId) {
        inventoryLogDao.save(inventoryLog, userId);
    }
}
