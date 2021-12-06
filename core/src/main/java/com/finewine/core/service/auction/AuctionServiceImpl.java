package com.finewine.core.service.auction;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.auction.AucStatus;
import com.finewine.core.model.auction.Auction;
import com.finewine.core.model.auction.AuctionDao;
import com.finewine.core.model.form.AddToMarketDTO;
import com.finewine.core.model.inventory.InventoryItem;
import com.finewine.core.model.inventory.InventoryItemDao;
import com.finewine.core.model.user.CustomUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Resource
    private AuctionDao auctionDao;

    @Resource
    private InventoryItemDao inventoryItemDao;

    @Override
    public Long saveNewAuction(Long inventoryId, CustomUser seller, AddToMarketDTO addToMarketDTO) {
        inventoryItemDao.updateOnSell(inventoryId, Boolean.TRUE);
        Optional<InventoryItem> optInventoryItem = inventoryItemDao.findById(inventoryId);
        Auction auction = new Auction();
        auction.setCreatingDate(Date.valueOf(LocalDate.now()));
        auction.setAucStatus(AucStatus.Live);
        auction.setSellPrice(BigDecimal.valueOf(Double.parseDouble(addToMarketDTO.getPrice())));
        auction.setSeller(seller);
        return auctionDao.save(auction, inventoryId);
    }

    @Override
    public void closeAuction(Long inventoryId, CustomUser seller) {
        inventoryItemDao.updateOnSell(inventoryId, Boolean.FALSE);
        auctionDao.updateClose(inventoryId);
    }

    @Override
    public Auction getAuctionByItemId(Long inventoryItemId) {
        Optional<Auction> auction;
        try {
            auction = auctionDao.getByItemId(inventoryItemId);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(inventoryItemId.toString());
        }
        if (auction.isPresent()) {
            return auction.get();
        } else {
            throw new NoElementWithSuchIdException(inventoryItemId.toString());
        }
    }

    @Override
    public List<Auction> getLiveAuctions() {
        if (auctionDao.checkCountForLiveAuctions() > 0) {
            return auctionDao.getAllLiveAuctions();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void buyAuction(Long inventoryId, CustomUser buyer) {
        auctionDao.buyAction(inventoryId, buyer.getId());
        inventoryItemDao.updateOnSell(inventoryId, Boolean.FALSE);
        inventoryItemDao.updateInventory(buyer.getInventory().getId(), inventoryId);
    }
}
