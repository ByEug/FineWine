package com.finewine.core.model.country;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCountryDao implements CountryDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_FOR_FIND_ALL_SIMPLE = "select * from country";
    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from country where id = ";
    private final String SQL_SELECT_FOR_FIND_BY_NAME = "select * from country where english_name = '%s'";

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(SQL_SELECT_FOR_FIND_ALL_SIMPLE, new BeanPropertyRowMapper<>(Country.class));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new BeanPropertyRowMapper<>(Country.class)));
    }

    @Override
    public Optional<Country> findByEnglishName(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(String.format(SQL_SELECT_FOR_FIND_BY_NAME, name),
                new BeanPropertyRowMapper<>(Country.class)));
    }
}
