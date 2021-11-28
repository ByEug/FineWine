package com.finewine.core.service.cart;

import com.finewine.core.exception.EmptyDatabaseArgumentException;
import com.finewine.core.exception.IllegalQuantityException;
import com.finewine.core.exception.OutOfStockException;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.cart.CartItem;
import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductDao;
import com.finewine.core.model.shopstock.ShopStock;
import com.finewine.core.model.shopstock.ShopStockDao;
import com.finewine.core.service.product.ProductService;
import com.finewine.core.service.shopstock.ShopStockService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@PropertySource("classpath:/properties/errorsMessages.properties")
public class HttpSessionCartServiceImpl implements CartService {

    @Resource
    private Environment env;

    @Resource
    private ProductService productService;

    private static final String CART_SESSION_ATTRIBUTE = "sessionCart";

    @Override
    public Cart getCart(HttpSession httpSession) {
        Cart attribute = (Cart) httpSession.getAttribute(HttpSessionCartServiceImpl.CART_SESSION_ATTRIBUTE);
        if (attribute == null) {
            attribute = new Cart();
            httpSession.setAttribute(CART_SESSION_ATTRIBUTE, attribute);
        }
        return attribute;
    }

    @Override
    public void deleteCart(HttpSession httpSession) {
        httpSession.removeAttribute(CART_SESSION_ATTRIBUTE);
    }

    @Override
    public void addProduct(Long productId, Long quantity, Cart cart) throws OutOfStockException, EmptyDatabaseArgumentException, IllegalQuantityException {
        Product product;
        try {
            product = productService.getProductById(productId.toString());
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyDatabaseArgumentException(env.getProperty("error.emptyDatabaseArgument"));
        }
        ShopStock shopStock = product.getStock();
        if (shopStock.getStock() >= quantity) {
            addQuantityToCartItem(quantity, product, cart);
        } else {
            throw new OutOfStockException(env.getProperty("error.outOfStock"));
        }
    }

    private void addQuantityToCartItem(Long quantity, Product product, Cart cart) {
        Optional<CartItem> optionalCartItem = findSameCartItem(product.getId(), cart);
        if (optionalCartItem.isPresent()) {
            CartItem existsItem = optionalCartItem.get();
            existsItem.setQuantity(existsItem.getQuantity() + quantity);
        } else {
            cart.getCartItems().add(new CartItem(product, quantity));
        }
        calculateCart(cart);
    }

    private Optional<CartItem> findSameCartItem(Long id, Cart cart) {
        return cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateProduct(Long productId, Long quantity, Cart cart) throws EmptyDatabaseArgumentException, OutOfStockException {

    }

    @Override
    public void removeProduct(Long productId, Cart cart) {

    }

    @Override
    public void calculateCart(Cart cart) {
        long totalQuantity = cart.getCartItems().stream()
                .mapToLong(CartItem::getQuantity)
                .sum();
        cart.setTotalQuantity(totalQuantity);
        BigDecimal totalCost = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalCost(totalCost);
    }

    @Override
    public void checkCartItemsForOutOfStock(Cart cart) throws OutOfStockException {

    }
}
