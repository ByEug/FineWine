package com.finewine.core.service.cart;

import com.finewine.core.exception.EmptyDatabaseArgumentException;
import com.finewine.core.exception.IllegalQuantityException;
import com.finewine.core.exception.OutOfStockException;
import com.finewine.core.model.cart.Cart;

import javax.servlet.http.HttpSession;

public interface CartService {

    Cart getCart(HttpSession httpSession);

    void deleteCart(HttpSession httpSession);

    void addProduct(Long productId, Long quantity, Cart cart) throws OutOfStockException, EmptyDatabaseArgumentException, IllegalQuantityException;

    void updateProduct(Long productId, Long quantity, Cart cart) throws EmptyDatabaseArgumentException, OutOfStockException;

    void removeProduct(Long productId, Cart cart);

    void calculateCart(Cart cart);

    void checkCartItemsForOutOfStock(Cart cart) throws OutOfStockException;
}
