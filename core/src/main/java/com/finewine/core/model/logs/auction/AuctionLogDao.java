package com.finewine.core.model.logs.auction;

import com.finewine.core.model.logs.auth.AuthLog;

import java.util.List;

public interface AuctionLogDao {

    List<AuctionLog> getForUserId(Long userId);

    void save(AuctionLog auctionLog, Long userId);
}
