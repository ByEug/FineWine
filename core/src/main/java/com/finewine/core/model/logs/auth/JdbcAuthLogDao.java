package com.finewine.core.model.logs.auth;

import com.finewine.core.model.inventory.InventoryItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcAuthLogDao implements AuthLogDao {

    private final String SQL_SELECT_LOG_FOR_USER = "select * from auth_log where id_user =";
    private final String SQL_SAVE_AUTH_LOG = "insert into auth_log (creating_date, action, id_user) values (?, ?, ?)";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AuthLog> getForUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_LOG_FOR_USER + userId,
                new AuthLogBeanPropertyRowMapper());
    }

    @Override
    public void save(AuthLog authLog, Long userId) {
        jdbcTemplate.update(SQL_SAVE_AUTH_LOG, authLog.getCreatingDate(), authLog.getAction().toString(), userId);
    }

    private final class AuthLogBeanPropertyRowMapper extends BeanPropertyRowMapper<AuthLog> {

        public AuthLogBeanPropertyRowMapper() {
            this.initialize(AuthLog.class);
        }

        @Override
        public AuthLog mapRow(ResultSet rs, int rowNumber) throws SQLException {
            AuthLog authLog = super.mapRow(rs, rowNumber);
            String action = rs.getString("action");
            authLog.setAction(AuthLogAction.valueOf(action));
            return authLog;
        }
    }
}
