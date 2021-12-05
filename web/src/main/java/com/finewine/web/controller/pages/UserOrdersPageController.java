package com.finewine.web.controller.pages;

import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.form.AddFundsDTO;
import com.finewine.core.model.order.Order;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.order.OrderService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/userOrders")
public class UserOrdersPageController {

    @Resource
    private HttpSession httpSession;

    @Resource
    private CustomUserService customUserService;

    @Resource
    private CartService cartService;

    @Resource
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserOrders(Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        List<Order> userOrders = orderService.getOrderListForUser(customUser.getId());
        Cart cart = cartService.getCart(httpSession);
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("userOrders", userOrders);
        return "userOrders";
    }
}
