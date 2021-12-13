package com.finewine.core.model.role;

import com.finewine.core.exception.IllegalRoleException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class JdbcRoleDao implements RoleDao {

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from role where id = ";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Role> getById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new RoleBeanPropertyRowMapper()));
    }

    private final class RoleBeanPropertyRowMapper extends BeanPropertyRowMapper<Role> {

        public RoleBeanPropertyRowMapper() {
            this.initialize(Role.class);
        }

        @Override
        public Role mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Role role = super.mapRow(rs, rowNumber);
            EnumRole enumRole;
            try {
                enumRole = EnumRole.valueOf(rs.getString("role_name"));
            } catch (IllegalArgumentException e) {
                throw new IllegalRoleException(rs.getString("role_name"));
            }
            role.setRoleName(enumRole);
            return role;
        }
    }
}
