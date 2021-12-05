package com.finewine.core.model.inventory;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Component
public class JdbcInventoryStockDao implements InventoryStockDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from inventory_stock where id = ";
    private final String SQL_SAVE_INVENTORY_STOCK = "insert into inventory_stock (stock) values (?)";

    @Override
    public Optional<InventoryStock> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new BeanPropertyRowMapper<>(InventoryStock.class)));
    }

    @Override
    public Long save(InventoryStock inventoryStock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] customUserParams = new Object[]{inventoryStock.getStock()};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_INVENTORY_STOCK, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= customUserParams.length; i++) {
                preparedStatement.setObject(i, customUserParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
