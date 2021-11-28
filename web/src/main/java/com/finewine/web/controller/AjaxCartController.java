package com.finewine.web.controller;

import com.finewine.core.ajaxhandling.HandlingInfo;
import com.finewine.core.ajaxhandling.ResponseHandlingInfo;
import com.finewine.core.ajaxhandling.SuccessfulHandlingInfo;
import com.finewine.core.ajaxhandling.ValidationHandlingInfo;
import com.finewine.core.exception.EmptyDatabaseArgumentException;
import com.finewine.core.exception.OutOfStockException;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.product.ProductDTO;
import com.finewine.core.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {

    @Resource
    private CartService cartService;

    @Resource
    private HttpSession httpSession;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HandlingInfo> addPhone(@Valid @ModelAttribute(name = "productDTO") ProductDTO productDTO,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ValidationHandlingInfo errors = new ValidationHandlingInfo(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Cart currentCart = cartService.getCart(httpSession);
            cartService.addProduct(productDTO.getId(), productDTO.getQuantity(), currentCart);
            SuccessfulHandlingInfo handlingInfo = new SuccessfulHandlingInfo(currentCart.getTotalQuantity().toString(),
                    currentCart.getTotalCost().toString());
            return ResponseEntity.ok().body(handlingInfo);
        } catch (OutOfStockException | EmptyDatabaseArgumentException e) {
            ResponseHandlingInfo errors = new ResponseHandlingInfo(e.getErrorMessage());
            return ResponseEntity.badRequest().body(errors);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
