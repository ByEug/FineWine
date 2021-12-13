package com.finewine.core.model.address;

import com.finewine.core.model.country.Country;
import com.finewine.core.model.country.CountryDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Component
public class JdbcAddressDao implements AddressDao {

    private final String SQL_SELECT_FOR_FIND_BY_ID = "select * from address where id = ";
    private final String SQL_SAVE_ADDRESS = "insert into address (locality, street, home_number, flat_number," +
            " id_country) values (?, ?, ?, ?, ?)";
    private final String SQL_SAVE_ADDRESS_TO_USER = "insert into " +
            "user2address (id_user, id_address) values (?, ?)";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private CountryDao countryDao;

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ID + id,
                new AddressBeanPropertyRowMapper()));
    }

    @Override
    public Long saveAddress(Address address, Long countryId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] addressParams = new Object[]{address.getLocality(), address.getStreet(), address.getHomeNumber(),
            address.getFlatNumber(), countryId};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= addressParams.length; i++) {
                preparedStatement.setObject(i, addressParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Long saveAddressForUser(Address address, Long countryId, Long userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] addressParams = new Object[]{address.getLocality(), address.getStreet(), address.getHomeNumber(),
                address.getFlatNumber(), countryId};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= addressParams.length; i++) {
                preparedStatement.setObject(i, addressParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        jdbcTemplate.update(SQL_SAVE_ADDRESS_TO_USER, userId, keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }

    private final class AddressBeanPropertyRowMapper extends BeanPropertyRowMapper<Address> {

        public AddressBeanPropertyRowMapper() {
            this.initialize(Address.class);
        }

        @Override
        public Address mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Address address = super.mapRow(rs, rowNumber);
            Optional<Country> optCountry = countryDao.findById(rs.getLong("id_country"));
            optCountry.ifPresent(address::setCountry);
            return address;
        }
    }
}
