package com.finewine.web.controller.pages.admin;

import com.finewine.core.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/admin/orders")
public class AdminOrdersController {

    @Resource
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "adminOrders";
    }
}
