package com.finewine.core.service.order;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.country.Country;
import com.finewine.core.model.order.*;
import com.finewine.core.model.shopstock.ShopStock;
import com.finewine.core.model.shopstock.ShopStockDao;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.address.AddressService;
import com.finewine.core.service.country.CountryService;
import com.finewine.core.service.product.ProductService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private ProductService productService;

    @Resource
    private AddressService addressService;

    @Resource
    private CountryService countryService;

    @Resource
    private CustomUserService customUserService;

    @Resource
    private ShopStockDao shopStockDao;

    @Resource
    private OrderDao orderDao;

    @Override
    public Order getOrder(String id) {
        Optional<Order> order;
        try {
            Long longId = Long.valueOf(id);
            order = orderDao.getOrder(longId);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(id);
        }
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new NoElementWithSuchIdException(id);
        }
    }

    @Override
    public List<Order> getOrderListForUser(Long userId) {
        if (orderDao.checkCountForUser(userId) > 0) {
            return orderDao.getOrdersForUserId(userId);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Long placeInventoryOrder(Cart cart, PreOrderDataDTO preOrderDataDTO, String username) {
        InventoryOrderBuilder orderBuilder = new InventoryOrderBuilder(cart, preOrderDataDTO);
        Order order = orderBuilder.getOrder();
        updateStocks(order);
        order.setCreatingDate(Date.valueOf(LocalDate.now()));
        CustomUser customUser = customUserService.findByUsername(username);
        order.setUser(customUser);
        return orderDao.saveInventoryOrder(order);
    }

    @Override
    public Long createDeliveryPreOrderForAuth(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice, String username) {
        DeliveryOrderBuilder deliveryOrderBuilder = new DeliveryOrderBuilder(cart, orderFullDataDTO, deliveryPrice);
        Order order = deliveryOrderBuilder.getOrder();
        Country country = countryService.getByCountryName(orderFullDataDTO.getAddressDTO().getCountry());
        order.getAddress().setCountry(country);
        updateStocks(order);
        order.setCreatingDate(Date.valueOf(LocalDate.now()));
        CustomUser customUser = customUserService.findByUsername(username);
        Long id = addressService.saveAddressForUser(order.getAddress(), country.getId(), customUser.getId());
        order.setUser(customUser);
        return orderDao.saveDeliveryOrderAuth(order, id);
    }

    private void updateStocks(Order order) {
        order.getOrderItems().forEach(orderItem -> {
            ShopStock currentStock = productService.getProductById(orderItem.getProduct().getId().toString()).getStock();
            shopStockDao.update(orderItem.getProduct().getId(), currentStock.getStock() - orderItem.getQuantity(),
                    currentStock.getReserved().longValue());
        });
    }

    @Override
    public Long createDeliveryPreOrderForGuest(Cart cart, OrderFullDataDTO orderFullDataDTO, Long deliveryPrice) {
        DeliveryOrderBuilder deliveryOrderBuilder = new DeliveryOrderBuilder(cart, orderFullDataDTO, deliveryPrice);
        Order order = deliveryOrderBuilder.getOrder();
        Country country = countryService.getByCountryName(orderFullDataDTO.getAddressDTO().getCountry());
        order.getAddress().setCountry(country);
        updateStocks(order);
        order.setCreatingDate(Date.valueOf(LocalDate.now()));
        Long id = addressService.saveAddress(order.getAddress(), country.getId());
        return orderDao.saveDeliveryOrderGuest(order, id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        orderDao.updateOrderStatus(orderId, status);
    }
}
