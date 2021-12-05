package com.finewine.web.controller.pages;

import com.finewine.core.model.auction.Auction;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.auction.AuctionService;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.inventory.InventoryService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/tradingPlatform")
public class TradingPlatformPageController {

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
    public String getTradingPlatform(Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        List<Auction> liveAuctions = auctionService.getLiveAuctions();
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("liveAuctions", liveAuctions);
        return "tradingPlatform";
    }

    @RequestMapping(value = "/back/{inventoryItemId}", method = RequestMethod.POST)
    public String getItemBack(@PathVariable("inventoryItemId") String inventoryItemId,
                              Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        auctionService.closeAuction(Long.parseLong(inventoryItemId), customUser);
        List<Auction> liveAuctions = auctionService.getLiveAuctions();
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("liveAuctions", liveAuctions);
        return "tradingPlatform";
    }

    @RequestMapping(value = "/buy/{inventoryItemId}", method = RequestMethod.POST)
    public String buyItem(@PathVariable("inventoryItemId") String inventoryItemId,
                          Principal principal, Model model) {
        if (principal == null) {
            return "/login";
        }
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        Auction auction = auctionService.getAuctionByItemId(Long.parseLong(inventoryItemId));
        if (auction.getSellPrice().compareTo(customUser.getCurrentFundsBalance()) > 0) {
            model.addAttribute("errorNotEnoughFunds", "Not enough funds");
        } else {
            auctionService.buyAuction(Long.parseLong(inventoryItemId), customUser);
            BigDecimal newFunds = customUser.getCurrentFundsBalance().add(auction.getSellPrice().multiply(BigDecimal.valueOf(-1L)));
            customUser.setCurrentFundsBalance(newFunds);
            customUserService.update(customUser);
        }
        List<Auction> liveAuctions = auctionService.getLiveAuctions();
        model.addAttribute("cart", cart);
        model.addAttribute("customUser", customUser);
        model.addAttribute("liveAuctions", liveAuctions);
        return "tradingPlatform";
    }
}
