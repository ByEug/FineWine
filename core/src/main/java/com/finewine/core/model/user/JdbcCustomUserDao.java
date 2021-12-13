package com.finewine.core.model.user;

import com.finewine.core.model.address.Address;
import com.finewine.core.model.address.AddressDao;
import com.finewine.core.model.inventory.Inventory;
import com.finewine.core.model.inventory.InventoryDao;
import com.finewine.core.model.order.JdbcOrderDao;
import com.finewine.core.model.role.Role;
import com.finewine.core.model.role.RoleDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcCustomUserDao implements CustomUserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private InventoryDao inventoryDao;

    @Resource
    private AddressDao addressDao;

    @Resource
    private RoleDao roleDao;

    private final String SQL_COUNT_BY_USERNAME = "select count(*) from user where username = '%s'";
    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from user where id = ";
    private final String SQL_SELECT_FOR_FIND_BY_USERNAME = "select * from user where username = '%s'";
    private final String SQL_SAVE_CUSTOM_USER = "insert into user (client_name, client_surname, username, " +
            "password, current_funds_balance, enabled, id_inventory) values (?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_SAVE_CUSTOM_USER_ROLE = "insert into " +
            "user2role (id_user, id_role) values (?, ?)";
    private final String SQL_UPDATE_CUSTOM_USER = "update user set client_name = '%s', client_surname = '%s', " +
            "password = '%s', current_funds_balance = %s where id = %d";
    private final String SQL_SELECT_FOR_MAP_ROW_ROLE = "select id_role from user2role where id_user = ";
    private final String SQL_SELECT_FOR_MAP_ROW_ADDRESS = "select id_address from user2address where id_user = ";
    private final String SQL_SELECT_USERS = "select * from user";
    private final String SQL_DELETE_USER = "delete from user where id = ";

    @Override
    public Optional<CustomUser> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new CustomUserBeanPropertyRowMapper()));
    }

    @Override
    public Optional<CustomUser> findByUsername(String username) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(String.format(SQL_SELECT_FOR_FIND_BY_USERNAME, username),
                new CustomUserBeanPropertyRowMapper()));
    }

    @Override
    public Integer countByUsername(String username) {
        return jdbcTemplate.queryForObject(String.format(SQL_COUNT_BY_USERNAME, username), Integer.class);
    }

    @Override
    public Long save(CustomUser customUser, Long inventoryId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] customUserParams = new Object[]{customUser.getClientName(), customUser.getClientSurname(),
                customUser.getUsername(), customUser.getPassword(), customUser.getCurrentFundsBalance(),
                customUser.getEnabled(), inventoryId};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_CUSTOM_USER, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= customUserParams.length; i++) {
                preparedStatement.setObject(i, customUserParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        jdbcTemplate.update(SQL_SAVE_CUSTOM_USER_ROLE, id, 2);
        return id;
    }

    @Override
    public void update(CustomUser customUser) {
        jdbcTemplate.update(String.format(SQL_UPDATE_CUSTOM_USER, customUser.getClientName(), customUser.getClientSurname(),
                customUser.getPassword(), customUser.getCurrentFundsBalance().multiply(BigDecimal.valueOf(100L)), customUser.getId()));
    }

    @Override
    public void updateRole(CustomUser customUser, Role role) {
        jdbcTemplate.update(SQL_SAVE_CUSTOM_USER_ROLE, customUser.getId(), role.getId());
    }

    @Override
    public void deleteUser(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER + id);
    }

    @Override
    public List<CustomUser> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_USERS, new CustomUserBeanPropertyRowMapper());
    }

    private final class CustomUserBeanPropertyRowMapper extends BeanPropertyRowMapper<CustomUser> {

        public CustomUserBeanPropertyRowMapper() {
            this.initialize(CustomUser.class);
        }

        @Override
        public CustomUser mapRow(ResultSet rs, int rowNumber) throws SQLException {
            CustomUser customUser = super.mapRow(rs, rowNumber);
            int currentFundsBalance = rs.getInt("current_funds_balance");
            customUser.setCurrentFundsBalance(BigDecimal.valueOf((double) currentFundsBalance / 100));
            Optional<Inventory> optInventory = inventoryDao.findById(rs.getLong("id_inventory"));
            optInventory.ifPresent(customUser::setInventory);
            List<Long> roleIds = jdbcTemplate.query(SQL_SELECT_FOR_MAP_ROW_ROLE + customUser.getId(), new IdRoleRowMapper());
            if (CollectionUtils.isNotEmpty(roleIds)) {
                Set<Role> roleSet = new HashSet<>();
                roleIds.forEach(roleId -> {
                    Optional<Role> optRole = roleDao.getById(roleId);
                    optRole.ifPresent(roleSet::add);
                });
                customUser.setRoles(roleSet);
            }
            List<Long> addressIds = jdbcTemplate.query(SQL_SELECT_FOR_MAP_ROW_ADDRESS + customUser.getId(), new IdAddressRowMapper());
            if (CollectionUtils.isNotEmpty(addressIds)) {
                Set<Address> addressSet = new HashSet<>();
                addressIds.forEach(addressId -> {
                    Optional<Address> optAddress = addressDao.findById(addressId);
                    optAddress.ifPresent(addressSet::add);
                });
                customUser.setAddresses(addressSet);
            }
            return customUser;
        }
    }

    private final static class IdRoleRowMapper implements RowMapper<Long> {

        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("id_role");
        }
    }

    private final static class IdAddressRowMapper implements RowMapper<Long> {

        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("id_address");
        }
    }
}
