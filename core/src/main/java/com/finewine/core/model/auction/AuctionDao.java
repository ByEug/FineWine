package com.finewine.core.model.auction;

import java.util.List;
import java.util.Optional;

public interface AuctionDao {

    Long save(Auction auction, Long inventoryId);

    void updateClose(Long inventoryId);

    Integer checkCountForLiveAuctions();

    List<Auction> getAllLiveAuctions();

    Optional<Auction> getById(Long Id);

    Optional<Auction> getByItemId(Long inventoryItemId);

    void buyAction(Long inventoryId, Long buyerId);
}
