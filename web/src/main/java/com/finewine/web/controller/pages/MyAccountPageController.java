package com.finewine.web.controller.pages;

import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.form.AddFundsDTO;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping(value = "/myAccount")
public class MyAccountPageController {

    @Resource
    private HttpSession httpSession;

    @Resource
    private CustomUserService customUserService;

    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getMyAccount(Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("addFundsDTO", new AddFundsDTO());
        return "myAccount";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addFunds(@Validated @ModelAttribute(name = "addFundsDTO") AddFundsDTO addFundsDTO,
                           BindingResult bindingResult, Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        model.addAttribute("cart", cart);
        if (!bindingResult.hasErrors()) {
            customUser.setCurrentFundsBalance(customUser.getCurrentFundsBalance().add(BigDecimal.valueOf(Double.parseDouble(addFundsDTO.getFunds()))));
            customUserService.update(customUser);
            customUser = customUserService.findByUsername(principal.getName());
        }
        model.addAttribute("customUser", customUser);
        return "myAccount";
    }

}
