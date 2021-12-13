package com.finewine.web.controller.pages.admin;

import com.finewine.core.exception.IncorrectUrlParameterException;
import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.order.Order;
import com.finewine.core.model.order.OrderStatus;
import com.finewine.core.service.order.OrderService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/admin/orders/{id}")
public class AdminOrdersDetailsController {

    @Resource
    private OrderService orderService;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrder(@PathVariable("id") String id, Model model) {
        Order order = orderService.getOrder(id);
        model.addAttribute("order", order);
        return "adminOrdersDetails";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String changeStatus(@RequestParam(required = false) String status, @PathVariable("id") Long id) {
        updateStatus(status, orderService.getOrder(id.toString()));
        return "redirect:/admin/orders/" + id;
    }

    private void updateStatus(String status, Order order) {
        if (EnumUtils.isValidEnum(OrderStatus.class, status)) {
            order.setOrderStatus(OrderStatus.valueOf(status));
            orderService.updateOrderStatus(order.getId(), order.getOrderStatus());
        } else {
            throw new IncorrectUrlParameterException(status);
        }
    }

    @ExceptionHandler(NoElementWithSuchIdException.class)
    public String handleNoElement(NoElementWithSuchIdException ex) {
        return "redirect:/404?message=" + "No such id: " + ex.getId();
    }

    @ExceptionHandler(IncorrectUrlParameterException.class)
    public String handleIncorrectParameter(IncorrectUrlParameterException ex) {
        return "redirect:/404?message=" + "Incorrect parameter in the request url: " + ex.getParameter();
    }
}
