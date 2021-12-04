package com.finewine.core.model.shopstock;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class JdbcShopStockDao implements ShopStockDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from shop_stock where id = ";

    private static final String SQL_UPDATE = "update shop_stock set stock = %d, reserved = %d where id = " +
            "(select id_stock from product where id = %d)";

    @Override
    public Optional<ShopStock> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new BeanPropertyRowMapper<>(ShopStock.class)));
    }

    @Override
    public void update(Long productId, Long stock, Long reserved) {
        jdbcTemplate.update(String.format(SQL_UPDATE, stock, reserved, productId));
    }
}
