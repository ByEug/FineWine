package com.finewine.core.model.comment;

import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductDao;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.model.user.CustomUserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCommentDao implements CommentDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private ProductDao productDao;

    @Resource
    private CustomUserDao customUserDao;

    private final String SQL_COUNT_BY_PRODUCT_ID = "select count(*) from comment where id_product = %d";
    private final String SQL_SELECT_COMMENTS_FOR_PRODUCT_ID = "select * from comment where id_product = %d";
    private final String SQL_INSERT_NEW_COMMENT = "insert into comment (comment_text, creating_date, id_user, id_product) " +
            "values (?, ?, ?, ?)";

    @Override
    public Integer checkCommentsForProduct(Long productId) {
        return jdbcTemplate.queryForObject(String.format(SQL_COUNT_BY_PRODUCT_ID, productId), Integer.class);
    }

    @Override
    public List<Comment> getCommentsForProduct(Long productId) {
        return jdbcTemplate.query(String.format(SQL_SELECT_COMMENTS_FOR_PRODUCT_ID, productId),
                new CommentBeanPropertyRowMapper());
    }

    @Override
    public void save(String commentText, Long userId, Long productId) {
        jdbcTemplate.update(SQL_INSERT_NEW_COMMENT, commentText, Date.valueOf(LocalDate.now()), userId, productId);
    }

    private final class CommentBeanPropertyRowMapper extends BeanPropertyRowMapper<Comment> {

        public CommentBeanPropertyRowMapper() {
            this.initialize(Comment.class);
        }

        @Override
        public Comment mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Comment comment = super.mapRow(rs, rowNumber);
            Optional<Product> optionalProduct = productDao.findById(rs.getLong("id_product"));
            optionalProduct.ifPresent(comment::setProduct);
            Optional<CustomUser> optionalCustomUser = customUserDao.findById(rs.getLong("id_user"));
            optionalCustomUser.ifPresent(comment::setUser);
            return comment;
        }
    }
}
