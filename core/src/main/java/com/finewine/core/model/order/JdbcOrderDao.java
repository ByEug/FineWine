package com.finewine.core.model.order;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.address.Address;
import com.finewine.core.model.address.AddressDao;
import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Component
public class JdbcOrderDao implements OrderDao {

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from orders where id = ";
    private static final String SQL_SELECT_FOR_MAP_ROW = "select * from order_item where id_order = ";
    private static final String SQL_SAVE_ORDER_INVENTORY = "insert into orders (subtotal_price, delivery_price, total_price, first_name," +
            " last_name, phone_number, additional_information, creating_date, order_type, order_status, id_user) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SAVE_ORDER_DELIVERY_GUEST = "insert into orders (subtotal_price, delivery_price, total_price, first_name," +
            " last_name, phone_number, additional_information, creating_date, order_type, order_status, id_address) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SAVE_ORDER_DELIVERY_AUTH = "insert into orders (subtotal_price, delivery_price, total_price, first_name," +
            " last_name, phone_number, additional_information, creating_date, order_type, order_status, id_address, id_user) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SAVE_ORDER_ITEM = "insert into order_item (id_product, id_order, quantity) " +
            "values (?, ?, ?)";
    private final String SQL_COUNT_BY_ID = "select count(*) from orders where id = %d";
    private final String SQL_SELECT_ORDERS_FOR_USER_ID = "select * from orders where id_user = %d";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private AddressDao addressDao;

    @Resource
    private ProductDao productDao;

    @Override
    public Optional<Order> getOrder(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new OrderBeanPropertyRowMapper()));
    }

    @Override
    public Integer checkCountForUser(Long userId) {
        return jdbcTemplate.queryForObject(String.format(SQL_COUNT_BY_ID, userId), Integer.class);
    }

    @Override
    public List<Order> getOrdersForUserId(Long userId) {
        return jdbcTemplate.query(String.format(SQL_SELECT_ORDERS_FOR_USER_ID, userId), new OrderBeanPropertyRowMapper());
    }

    @Override
    public Long saveInventoryOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] orderParams = new Object[]{order.getSubtotalPrice().multiply(BigDecimal.valueOf(100L)),
                order.getDeliveryPrice().multiply(BigDecimal.valueOf(100L)),
                order.getTotalPrice().multiply(BigDecimal.valueOf(100L)),
                order.getFirstName(), order.getLastName(), order.getPhoneNumber(),
                order.getAdditionalInformation(), order.getCreatingDate(),
                order.getOrderType().toString(), order.getOrderStatus().toString(), order.getUser().getId()};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ORDER_INVENTORY, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= orderParams.length; i++) {
                preparedStatement.setObject(i, orderParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            jdbcTemplate.update(SQL_SAVE_ORDER_ITEM, orderItems.get(i).getProduct().getId(),
                    id, orderItems.get(i).getQuantity());
        }
        return id;
    }

    @Override
    public Long saveDeliveryOrderGuest(Order order, Long addressId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] orderParams = new Object[]{order.getSubtotalPrice().multiply(BigDecimal.valueOf(100L)),
                order.getDeliveryPrice().multiply(BigDecimal.valueOf(100L)),
                order.getTotalPrice().multiply(BigDecimal.valueOf(100L)),
                order.getFirstName(), order.getLastName(), order.getPhoneNumber(),
                order.getAdditionalInformation(), order.getCreatingDate(),
                order.getOrderType().toString(), order.getOrderStatus().toString(), addressId};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ORDER_DELIVERY_GUEST, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= orderParams.length; i++) {
                preparedStatement.setObject(i, orderParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            jdbcTemplate.update(SQL_SAVE_ORDER_ITEM, orderItems.get(i).getProduct().getId(),
                    id, orderItems.get(i).getQuantity());
        }
        return id;
    }

    @Override
    public Long saveDeliveryOrderAuth(Order order, Long addressId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] orderParams = new Object[]{order.getSubtotalPrice().multiply(BigDecimal.valueOf(100L)),
                order.getDeliveryPrice().multiply(BigDecimal.valueOf(100L)),
                order.getTotalPrice().multiply(BigDecimal.valueOf(100L)),
                order.getFirstName(), order.getLastName(), order.getPhoneNumber(),
                order.getAdditionalInformation(), order.getCreatingDate(),
                order.getOrderType().toString(), order.getOrderStatus().toString(), addressId, order.getUser().getId()};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ORDER_DELIVERY_AUTH, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= orderParams.length; i++) {
                preparedStatement.setObject(i, orderParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            jdbcTemplate.update(SQL_SAVE_ORDER_ITEM, orderItems.get(i).getProduct().getId(),
                    id, orderItems.get(i).getQuantity());
        }
        return id;
    }

    private final class OrderBeanPropertyRowMapper extends BeanPropertyRowMapper<Order> {

        public OrderBeanPropertyRowMapper() {
            this.initialize(Order.class);
        }

        @Override
        public Order mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Order order = super.mapRow(rs, rowNumber);
            int subtotalPrice = rs.getInt("subtotal_price");
            order.setSubtotalPrice(BigDecimal.valueOf((double) subtotalPrice / 100));
            int deliveryPrice = rs.getInt("delivery_price");
            order.setDeliveryPrice(BigDecimal.valueOf((double) deliveryPrice / 100));
            int totalPrice = rs.getInt("total_price");
            order.setTotalPrice(BigDecimal.valueOf((double) totalPrice / 100));
            List<OrderItemBuffer> orderItemsBuffer = jdbcTemplate.query(SQL_SELECT_FOR_MAP_ROW + order.getId(),
                    new BeanPropertyRowMapper<>(OrderItemBuffer.class));
            List<OrderItem> orderItems = new ArrayList<>();
            orderItemsBuffer.forEach(orderItemBuffer -> {
                Optional<Product> optionalProduct = productDao.findById(orderItemBuffer.getIdProduct());
                if (optionalProduct.isPresent()) {
                    orderItems.add(new OrderItem(orderItemBuffer.getId(), optionalProduct.get(), order,
                            orderItemBuffer.getQuantity()));
                } else {
                    throw new NoElementWithSuchIdException(orderItemBuffer.getIdProduct().toString());
                }
            });
            order.setOrderItems(orderItems);
            try {
                Optional<Address> optAddress = addressDao.findById(rs.getLong("id_address"));
                optAddress.ifPresent(order::setAddress);
            } catch (EmptyResultDataAccessException e) {
                return order;
            }
            return order;
        }
    }

}
