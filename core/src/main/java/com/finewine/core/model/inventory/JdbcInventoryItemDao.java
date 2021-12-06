package com.finewine.core.model.inventory;

import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcInventoryItemDao implements InventoryItemDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private InventoryStockDao inventoryStockDao;

    @Resource
    private InventoryDao inventoryDao;

    @Resource
    private ProductDao productDao;

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from inventory_item where id = ";
    private final String SQL_INSERT_NEW_ITEM = "insert into inventory_item (id_inventory_stock, id_product, id_inventory, is_on_sell) " +
            "values (?, ?, ?, ?)";
    private final String SQL_SELECT_ITEMS_FOR_INVENTORY_ID = "select * from inventory_item where id_inventory = %d";
    private final String SQL_COUNT_BY_ID = "select count(*) from inventory_item where id_inventory = %d";
    private final String SQL_UPDATE_ON_SELL = "update inventory_item set is_on_sell = %b where id = %d";
    private final String SQL_UPDATE_INVENTORY = "update inventory_item set id_inventory = %d where id = %d";

    @Override
    public Optional<InventoryItem> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new InventoryItemBeanPropertyRowMapper()));
    }

    @Override
    public Integer checkCountForInventory(Long inventoryId) {
        return jdbcTemplate.queryForObject(String.format(SQL_COUNT_BY_ID, inventoryId), Integer.class);
    }

    @Override
    public void updateOnSell(Long id, Boolean value) {
        jdbcTemplate.update(String.format(SQL_UPDATE_ON_SELL, value, id));
    }

    @Override
    public void updateInventory(Long inventoryId, Long inventoryItemId) {
        jdbcTemplate.update(String.format(SQL_UPDATE_INVENTORY, inventoryId, inventoryItemId));
    }

    @Override
    public List<InventoryItem> getItemsForInventoryId(Long inventoryId) {
        return jdbcTemplate.query(String.format(SQL_SELECT_ITEMS_FOR_INVENTORY_ID, inventoryId),
                new InventoryItemBeanPropertyRowMapper());
    }

    @Override
    public void save(Long stockId, Long productId, Long inventoryId) {
        jdbcTemplate.update(SQL_INSERT_NEW_ITEM, stockId, productId, inventoryId, false);
    }

    private final class InventoryItemBeanPropertyRowMapper extends BeanPropertyRowMapper<InventoryItem> {

        public InventoryItemBeanPropertyRowMapper() {
            this.initialize(InventoryItem.class);
        }

        @Override
        public InventoryItem mapRow(ResultSet rs, int rowNumber) throws SQLException {
            InventoryItem inventoryItem = super.mapRow(rs, rowNumber);
            Optional<InventoryStock> optionalInventoryStock = inventoryStockDao.findById(rs.getLong("id_inventory_stock"));
            optionalInventoryStock.ifPresent(inventoryItem::setInventoryStock);
            Optional<Inventory> optionalInventory = inventoryDao.findById(rs.getLong("id_inventory"));
            optionalInventory.ifPresent(inventoryItem::setInventory);
            Optional<Product> optionalProduct = productDao.findById(rs.getLong("id_product"));
            optionalProduct.ifPresent(inventoryItem::setProduct);
            Boolean isOnSell = rs.getBoolean("is_on_sell");
            inventoryItem.setOnSell(isOnSell);
            return inventoryItem;
        }
    }
}
