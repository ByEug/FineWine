package com.finewine.core.model.logs.inventory;

import com.finewine.core.model.inventory.InventoryItem;
import com.finewine.core.model.inventory.InventoryItemDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcInventoryLogDao implements InventoryLogDao {

    private final String SQL_SELECT_LOG_FOR_USER = "select * from inventory_log where id_user =";
    private final String SQL_SAVE_INVENTORY_LOG = "insert into inventory_log (creating_date, action, id_inventory_item, id_user) values (?, ?, ?, ?)";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private InventoryItemDao inventoryItemDao;

    @Override
    public List<InventoryLog> getForUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_LOG_FOR_USER + userId,
                new InventoryLogBeanPropertyRowMapper());
    }

    @Override
    public void save(InventoryLog inventoryLog, Long userId) {
        jdbcTemplate.update(SQL_SAVE_INVENTORY_LOG, inventoryLog.getCreatingDate(), inventoryLog.getAction().toString(),
                inventoryLog.getInventoryItem().getId(), userId);
    }

    private final class InventoryLogBeanPropertyRowMapper extends BeanPropertyRowMapper<InventoryLog> {

        public InventoryLogBeanPropertyRowMapper() {
            this.initialize(InventoryLog.class);
        }

        @Override
        public InventoryLog mapRow(ResultSet rs, int rowNumber) throws SQLException {
            InventoryLog inventoryLog = super.mapRow(rs, rowNumber);
            Optional<InventoryItem> optionalInventoryItem = inventoryItemDao.findById(rs.getLong("id_inventory_item"));
            optionalInventoryItem.ifPresent(inventoryLog::setInventoryItem);
            return inventoryLog;
        }
    }
}
