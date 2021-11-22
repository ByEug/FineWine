package com.finewine.core.model.product;

import com.finewine.core.model.category.Category;
import com.finewine.core.model.category.CategoryDao;
import com.finewine.core.model.country.Country;
import com.finewine.core.model.country.CountryDao;
import com.finewine.core.model.shopstock.ShopStock;
import com.finewine.core.model.shopstock.ShopStockDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcProductDao implements ProductDao {

    private final String SQL_SELECT_FOR_FIND_ALL_SIMPLE_NO_LIMIT = "select * from product";
    private final String SQL_SELECT_FOR_FIND_ALL_SIMPLE = "select * from product offset %d limit %d";
    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from product where id = ";
    private final String SQL_SELECT_FOR_MAP_ROW = "select id_category from product2category where id_product = ";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private CountryDao countryDao;

    @Resource
    private ShopStockDao shopStockDao;

    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<Product> findAll(int offset, int limit) {
        return jdbcTemplate.query(String.format(SQL_SELECT_FOR_FIND_ALL_SIMPLE, offset, limit),
                new ProductBeanPropertyRowMapper());
    }

    @Override
    public List<Product> findAllNoLimit() {
        return jdbcTemplate.query(SQL_SELECT_FOR_FIND_ALL_SIMPLE_NO_LIMIT, new ProductBeanPropertyRowMapper());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new ProductBeanPropertyRowMapper()));
    }

    private final class ProductBeanPropertyRowMapper extends BeanPropertyRowMapper<Product> {

        public ProductBeanPropertyRowMapper() {
            this.initialize(Product.class);
        }

        @Override
        public Product mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Product product = super.mapRow(rs, rowNumber);
            int price = rs.getInt("price");
            product.setPrice(BigDecimal.valueOf((double) price / 100));
            Optional<Country> optCountry = countryDao.findById(rs.getLong("id_country"));
            optCountry.ifPresent(product::setCountry);
            Optional<ShopStock> optShopStock = shopStockDao.findById(rs.getLong("id_stock"));
            optShopStock.ifPresent(product::setStock);
            List<Long> categoryIds = jdbcTemplate.query(SQL_SELECT_FOR_MAP_ROW + product.getId(), new IdRowMapper());
            if (CollectionUtils.isNotEmpty(categoryIds)) {
                Set<Category> categorySet = new HashSet<>();
                categoryIds.forEach(categoryId -> {
                    Optional<Category> optCategory = categoryDao.findById(categoryId);
                    optCategory.ifPresent(categorySet::add);
                });
                product.setCategories(categorySet);
            }
            return product;
        }
    }

    private final static class IdRowMapper implements RowMapper<Long> {

        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("id_category");
        }
    }
}
