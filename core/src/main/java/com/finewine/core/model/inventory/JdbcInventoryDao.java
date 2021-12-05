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
public class JdbcInventoryDao implements InventoryDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from inventory where id = ";
    private final String SQL_SAVE_INVENTORY = "insert into inventory (overall_quantity) values (?)";
    private final String SQL_UPDATE_INVENTORY = "update inventory set overall_quantity = %d where id = %d";

    @Override
    public Optional<Inventory> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new BeanPropertyRowMapper<>(Inventory.class)));
    }

    @Override
    public Long save(Inventory inventory) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] customUserParams = new Object[]{inventory.getOverallQuantity()};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_INVENTORY, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= customUserParams.length; i++) {
                preparedStatement.setObject(i, customUserParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void update(Long overallQuantity, Long id) {
        jdbcTemplate.update(String.format(SQL_UPDATE_INVENTORY, overallQuantity, id));
    }
}
