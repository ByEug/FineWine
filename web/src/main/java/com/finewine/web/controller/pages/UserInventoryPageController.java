package com.finewine.web.controller.pages;

import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.form.AddToMarketDTO;
import com.finewine.core.model.inventory.InventoryItem;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.auction.AuctionService;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.inventory.InventoryService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/userInventory")
public class UserInventoryPageController {

    @Resource
    private HttpSession httpSession;

    @Resource
    private CustomUserService customUserService;

    @Resource
    private CartService cartService;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private AuctionService auctionService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserInventory(Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        List<InventoryItem> inventoryItems = inventoryService.getInventoryItemsForUserInventory(customUser.getInventory().getId());
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("addToMarketDTO", new AddToMarketDTO());
        return "userInventory";
    }

    @RequestMapping(value = "/back/{inventoryId}", method = RequestMethod.POST)
    public String bringItemBackFromMarket(@PathVariable("inventoryId") String inventoryId,
                                          Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        auctionService.closeAuction(Long.parseLong(inventoryId), customUser);
        List<InventoryItem> inventoryItems = inventoryService.getInventoryItemsForUserInventory(customUser.getInventory().getId());
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("addToMarketDTO", new AddToMarketDTO());
        return "userInventory";
    }

    @RequestMapping(value = "/sell/{inventoryId}", method = RequestMethod.POST)
    public String sellItemOnMarket(@PathVariable("inventoryId") String inventoryId,
                                   @Validated @ModelAttribute(name = "addToMarketDTO") AddToMarketDTO addToMarketDTO,
                                   BindingResult bindingResult, Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        if (!bindingResult.hasErrors()) {
            auctionService.saveNewAuction(Long.parseLong(inventoryId), customUser, addToMarketDTO);
        }
        List<InventoryItem> inventoryItems = inventoryService.getInventoryItemsForUserInventory(customUser.getInventory().getId());
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("inventoryItems", inventoryItems);
        return "userInventory";
    }
}
