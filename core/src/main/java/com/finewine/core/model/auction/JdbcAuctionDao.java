package com.finewine.core.model.auction;

import com.finewine.core.model.inventory.InventoryItem;
import com.finewine.core.model.inventory.InventoryItemDao;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.model.user.CustomUserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcAuctionDao implements AuctionDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private CustomUserDao customUserDao;

    @Resource
    private InventoryItemDao inventoryItemDao;

    private final String SQL_SAVE_AUCTION = "insert into auction (creating_date, " +
            "auc_status, sell_price, id_seller, id_inventory_item) values (?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_CLOSE = "update auction set cancelling_date = '%s', auc_status = '%s' where id_inventory_item = %d";
    private final String SQL_CHECK_LIVE = "select count(*) from auction where auc_status = 'Live'";
    private final String SQL_SELECT_LIVE_AUCTIONS = "select * from auction where auc_status = 'Live'";
    private final String SQL_SELECT_FOR_FIND_BY_ITEM_ID = "select * from auction where auc_status = 'Live' and id_inventory_item = ";
    private final String SQL_UPDATE_BUY = "update auction set completing_date = '%s', auc_status = '%s', id_buyer = %d " +
            "where auc_status = 'Live' and id_inventory_item = %d";

    @Override
    public Long save(Auction auction, Long inventoryId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] auctionParams = new Object[]{auction.getCreatingDate(), auction.getAucStatus().toString(),
                auction.getSellPrice().multiply(BigDecimal.valueOf(100L)), auction.getSeller().getId(),
                inventoryId};
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_AUCTION, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= auctionParams.length; i++) {
                preparedStatement.setObject(i, auctionParams[i - 1]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateClose(Long inventoryId) {
        jdbcTemplate.update(String.format(SQL_UPDATE_CLOSE, Date.valueOf(LocalDate.now()), AucStatus.Closed, inventoryId));
    }

    @Override
    public Integer checkCountForLiveAuctions() {
        return jdbcTemplate.queryForObject(SQL_CHECK_LIVE, Integer.class);
    }

    @Override
    public List<Auction> getAllLiveAuctions() {
        return jdbcTemplate.query(SQL_SELECT_LIVE_AUCTIONS, new AuctionBeanPropertyRowMapper());
    }

    @Override
    public Optional<Auction> getByItemId(Long inventoryItemId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_FOR_FIND_BY_ITEM_ID + inventoryItemId,
                new AuctionBeanPropertyRowMapper()));
    }

    @Override
    public void buyAction(Long inventoryId, Long buyerId) {
        jdbcTemplate.update(String.format(SQL_UPDATE_BUY, Date.valueOf(LocalDate.now()), AucStatus.Completed, buyerId,
                inventoryId));
    }

    private final class AuctionBeanPropertyRowMapper extends BeanPropertyRowMapper<Auction> {

        public AuctionBeanPropertyRowMapper() {
            this.initialize(Auction.class);
        }

        @Override
        public Auction mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Auction auction = super.mapRow(rs, rowNumber);
            int sellPrice = rs.getInt("sell_price");
            auction.setSellPrice(BigDecimal.valueOf((double) sellPrice / 100));
            Optional<CustomUser> optionalCustomUser = customUserDao.findById(rs.getLong("id_seller"));
            optionalCustomUser.ifPresent(auction::setSeller);
            Optional<InventoryItem> optionalInventoryItem = inventoryItemDao.findById(rs.getLong("id_inventory_item"));
            optionalInventoryItem.ifPresent(auction::setInventoryItem);
            return auction;
        }
    }
}
