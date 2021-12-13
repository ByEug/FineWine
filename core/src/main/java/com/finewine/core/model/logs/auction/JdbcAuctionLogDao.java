package com.finewine.core.model.logs.auction;

import com.finewine.core.model.auction.Auction;
import com.finewine.core.model.auction.AuctionDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcAuctionLogDao implements AuctionLogDao {

    private final String SQL_SELECT_LOG_FOR_USER = "select * from auction_log where id_user =";
    private final String SQL_SAVE_AUCTION_LOG = "insert into auction_log (creating_date, action, id_auction, id_user) values (?, ?, ?, ?)";


    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private AuctionDao auctionDao;

    @Override
    public List<AuctionLog> getForUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_LOG_FOR_USER + userId,
                new AuctionLogBeanPropertyRowMapper());
    }

    @Override
    public void save(AuctionLog auctionLog, Long userId) {
        jdbcTemplate.update(SQL_SAVE_AUCTION_LOG, auctionLog.getCreatingDate(), auctionLog.getAction().toString(),
                auctionLog.getAuction().getId(), userId);
    }

    private final class AuctionLogBeanPropertyRowMapper extends BeanPropertyRowMapper<AuctionLog> {

        public AuctionLogBeanPropertyRowMapper() {
            this.initialize(AuctionLog.class);
        }

        @Override
        public AuctionLog mapRow(ResultSet rs, int rowNumber) throws SQLException {
            AuctionLog auctionLog = super.mapRow(rs, rowNumber);
            Optional<Auction> optionalAuction = auctionDao.getById(rs.getLong("id_auction"));
            optionalAuction.ifPresent(auctionLog::setAuction);
            return auctionLog;
        }
    }
}
