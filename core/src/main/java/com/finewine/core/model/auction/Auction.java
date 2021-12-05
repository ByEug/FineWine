package com.finewine.core.model.auction;

import com.finewine.core.model.inventory.InventoryItem;
import com.finewine.core.model.user.CustomUser;

import java.math.BigDecimal;
import java.sql.Date;

public class Auction {

    private Long id;
    private Date creatingDate;
    private Date cancellingDate;
    private Date completingDate;
    private AucStatus aucStatus;
    private BigDecimal sellPrice;
    private CustomUser seller;
    private CustomUser buyer;
    private InventoryItem inventoryItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Date getCancellingDate() {
        return cancellingDate;
    }

    public void setCancellingDate(Date cancellingDate) {
        this.cancellingDate = cancellingDate;
    }

    public Date getCompletingDate() {
        return completingDate;
    }

    public void setCompletingDate(Date completingDate) {
        this.completingDate = completingDate;
    }

    public AucStatus getAucStatus() {
        return aucStatus;
    }

    public void setAucStatus(AucStatus aucStatus) {
        this.aucStatus = aucStatus;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public CustomUser getSeller() {
        return seller;
    }

    public void setSeller(CustomUser seller) {
        this.seller = seller;
    }

    public CustomUser getBuyer() {
        return buyer;
    }

    public void setBuyer(CustomUser buyer) {
        this.buyer = buyer;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
}
