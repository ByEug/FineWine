package com.finewine.core.model.logs.auction;

import com.finewine.core.model.auction.Auction;
import com.finewine.core.model.logs.AbstractLog;

public class AuctionLog extends AbstractLog {

    private AuctionLogAction action;
    private Auction auction;

    public AuctionLogAction getAction() {
        return action;
    }

    public void setAction(AuctionLogAction action) {
        this.action = action;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
