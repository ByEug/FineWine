package com.finewine.web.controller.pages;

import com.finewine.core.exception.NoSuchOrderTypeException;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.country.Country;
import com.finewine.core.model.order.OrderFullDataDTO;
import com.finewine.core.model.order.OrderType;
import com.finewine.core.model.order.PreOrderDataDTO;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.country.CountryService;
import com.finewine.core.service.order.OrderService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {

    @Resource
    private Environment env;

    @Resource
    private HttpSession httpSession;

    @Resource
    private CountryService countryService;

    @Resource
    private OrderService orderService;

    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrder(@RequestParam(required = false) String orderType, Principal principal, Model model) {
        if (orderType.equals(OrderType.Inventory.name()) && principal == null) {
            return "redirect:/login";
        }
        OrderType currentOrderType = checkForValidOrderType(orderType);
        Cart cart = cartService.getCart(httpSession);
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries", countries);
        model.addAttribute("preOrderDataDTO", new PreOrderDataDTO());
        model.addAttribute("orderFullDataDTO", new OrderFullDataDTO());
        model.addAttribute("currentOrderType", currentOrderType);
        prepareModelToShowCart(cart, currentOrderType, model);
        return "order";
    }

    private OrderType checkForValidOrderType(String orderType) {
        OrderType bufferOrderType;
        try {
            bufferOrderType = OrderType.valueOf(orderType);
        } catch (IllegalArgumentException e) {
            throw new NoSuchOrderTypeException(orderType);
        }
        return bufferOrderType;
    }

    private void prepareModelToShowCart(Cart cart, OrderType currentOrderType, Model model) {
        model.addAttribute("cart", cart);
        if (currentOrderType.equals(OrderType.Delivery)) {
            model.addAttribute("deliveryPrice", Long.parseLong(env.getProperty("delivery.price")));
            model.addAttribute("totalPrice",
                    cart.getTotalCost().add(BigDecimal.valueOf(Long.parseLong(env.getProperty("delivery.price")))));
        } else {
            model.addAttribute("deliveryPrice", 0L);
            model.addAttribute("totalPrice", cart.getTotalCost());
        }
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public String placeInventoryOrder(@Validated @ModelAttribute(name = "preOrderDataDTO") PreOrderDataDTO preOrderDataDTO,
                             @RequestParam(required = false) String orderType,
                             BindingResult bindingResult, Principal principal, Model model) {
        Cart cart = cartService.getCart(httpSession);
        OrderType currentOrderType = checkForValidOrderType(orderType);
        if (cart.getCartItems().isEmpty()) {
            model.addAttribute("emptyCartMessage", env.getProperty("emptyCartMessage"));
            model.addAttribute("preOrderDataDTO", preOrderDataDTO);
        } else if (!bindingResult.hasErrors()) {
            return manageInventoryOrder(cart, preOrderDataDTO, principal);
        }
        prepareModelToShowCart(cart, currentOrderType, model);
        return "order";
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    public String placeDeliveryOrder(@Validated @ModelAttribute(name = "orderFullDataDTO") OrderFullDataDTO orderFullDataDTO,
                                     @RequestParam(required = false) String orderType,
                                     BindingResult bindingResult, Principal principal, Model model) {
        Cart cart = cartService.getCart(httpSession);
        OrderType currentOrderType = checkForValidOrderType(orderType);
        if (cart.getCartItems().isEmpty()) {
            model.addAttribute("emptyCartMessage", env.getProperty("emptyCartMessage"));
            model.addAttribute("orderFullDataDTO", orderFullDataDTO);
        } else if (!bindingResult.hasErrors()) {
            return manageDeliveryOrder(cart, orderFullDataDTO, principal);
        }
        prepareModelToShowCart(cart, currentOrderType, model);
        return "order";
    }

    private String manageInventoryOrder(Cart cart, PreOrderDataDTO preOrderDataDTO, Principal principal) {
        Long id = orderService.placeInventoryOrder(cart, preOrderDataDTO);
        cartService.deleteCart(httpSession);
        return "redirect:/orderOverview/" + id;
    }

    private String manageDeliveryOrder(Cart cart, OrderFullDataDTO orderFullDataDTO, Principal principal) {
        Long id;
        Long deliveryPrice = Long.parseLong(env.getProperty("delivery.price"));
        if (principal != null) {
            id = orderService.createDeliveryPreOrderForAuth(cart, orderFullDataDTO, deliveryPrice, principal.getName());
        } else {
            id = orderService.createDeliveryPreOrderForGuest(cart, orderFullDataDTO, deliveryPrice);
        }
        cartService.deleteCart(httpSession);
        return "redirect:/orderOverview/" + id;
    }

    @ExceptionHandler(NoSuchOrderTypeException.class)
    public String handle(NoSuchOrderTypeException ex) {
        return "redirect:/404?message=" + "No such order type: " + ex.getType();
    }
}
