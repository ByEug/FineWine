package com.finewine.core.model.category;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class JdbcCategoryDao implements CategoryDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from category where id = ";

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new BeanPropertyRowMapper<>(Category.class)));
    }
}
