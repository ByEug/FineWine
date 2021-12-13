package com.finewine.core.service.logs.auction;

import com.finewine.core.model.logs.auction.AuctionLog;
import com.finewine.core.model.logs.auction.AuctionLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuctionLogServiceImpl implements AuctionLogService {

    @Resource
    private AuctionLogDao auctionLogDao;

    @Override
    public List<AuctionLog> getForUserId(Long userId) {
        return auctionLogDao.getForUserId(userId);
    }

    @Override
    public void save(AuctionLog auctionLog, Long userId) {
        auctionLogDao.save(auctionLog, userId);
    }
}
