package com.finewine.core.service.logs.auction;

import com.finewine.core.model.logs.auction.AuctionLog;

import java.util.List;

public interface AuctionLogService {

    List<AuctionLog> getForUserId(Long userId);

    void save(AuctionLog auctionLog, Long userId);
}
