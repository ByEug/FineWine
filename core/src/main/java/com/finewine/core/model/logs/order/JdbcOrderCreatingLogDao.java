package com.finewine.core.model.logs.order;

import com.finewine.core.model.order.Order;
import com.finewine.core.model.order.OrderDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcOrderCreatingLogDao implements OrderCreatingLogDao {

    private final String SQL_SELECT_LOG_FOR_USER = "select * from order_creating_log where id_user =";
    private final String SQL_SAVE_ORDER_LOG = "insert into order_creating_log (creating_date, order_type, id_order, id_user) values (?, ?, ?, ?)";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private OrderDao orderDao;

    @Override
    public List<OrderCreatingLog> getForUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_LOG_FOR_USER + userId,
                new OrderCreatingLogBeanPropertyRowMapper());
    }

    @Override
    public void save(OrderCreatingLog orderCreatingLog, Long userId) {
        jdbcTemplate.update(SQL_SAVE_ORDER_LOG, orderCreatingLog.getCreatingDate(), orderCreatingLog.getOrderType().toString(),
                orderCreatingLog.getOrder().getId(), userId);
    }

    private final class OrderCreatingLogBeanPropertyRowMapper extends BeanPropertyRowMapper<OrderCreatingLog> {

        public OrderCreatingLogBeanPropertyRowMapper() {
            this.initialize(OrderCreatingLog.class);
        }

        @Override
        public OrderCreatingLog mapRow(ResultSet rs, int rowNumber) throws SQLException {
            OrderCreatingLog orderCreatingLog = super.mapRow(rs, rowNumber);
            Optional<Order> optionalOrder = orderDao.getOrder(rs.getLong("id_order"));
            optionalOrder.ifPresent(orderCreatingLog::setOrder);
            return orderCreatingLog;
        }
    }
}
