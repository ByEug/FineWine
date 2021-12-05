package com.finewine.core.service.auction;

import com.finewine.core.model.auction.Auction;
import com.finewine.core.model.form.AddToMarketDTO;
import com.finewine.core.model.user.CustomUser;

import java.util.List;

public interface AuctionService {

    Long saveNewAuction(Long inventoryId, CustomUser seller, AddToMarketDTO addToMarketDTO);

    void closeAuction(Long inventoryId, CustomUser seller);

    Auction getAuctionByItemId(Long inventoryItemId);

    List<Auction> getLiveAuctions();

    void buyAuction(Long inventoryId, CustomUser buyer);
}
