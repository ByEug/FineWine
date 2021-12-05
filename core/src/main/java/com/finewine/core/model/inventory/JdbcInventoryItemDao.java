package com.finewine.core.model.inventory;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class JdbcInventoryItemDao implements InventoryItemDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from inventory_item where id = ";
    private final String SQL_INSERT_NEW_ITEM = "insert into inventory_item (id_inventory_stock, id_product, id_inventory) " +
            "values (?, ?, ?)";

    @Override
    public Optional<InventoryItem> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new BeanPropertyRowMapper<>(InventoryItem.class)));
    }

    @Override
    public void save(Long stockId, Long productId, Long inventoryId) {
        jdbcTemplate.update(SQL_INSERT_NEW_ITEM, stockId, productId, inventoryId);
    }
}
