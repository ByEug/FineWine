package com.finewine.core.service.shopstock;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.shopstock.ShopStock;
import com.finewine.core.model.shopstock.ShopStockDao;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.annotation.Resource;
import java.util.Optional;

public class ShopStockServiceImpl implements ShopStockService {

    @Resource
    private ShopStockDao shopStockDao;

    @Override
    public ShopStock getShopStockById(String id) {
        Optional<ShopStock> shopStock;
        try {
            Long longId = Long.valueOf(id);
            shopStock = shopStockDao.findById(longId);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(id);
        }
        if (shopStock.isPresent()) {
            return shopStock.get();
        } else {
            throw new NoElementWithSuchIdException(id);
        }
    }
}
