package com.finewine.web.controller.pages;

import com.finewine.core.exception.EmptyDatabaseArgumentException;
import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.exception.OutOfStockException;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.order.OrderType;
import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductArrayDTO;
import com.finewine.core.model.product.ProductDTO;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.product.ProductService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@PropertySource("classpath:/lang-en.properties")
@RequestMapping(value = "/cart")
public class CartPageController {

    @Resource
    private Environment env;

    @Resource
    private CartService cartService;

    @Resource
    private ProductService productService;

    @Resource
    private HttpSession httpSession;

    @RequestMapping(method = RequestMethod.GET)
    public String getCart(Model model) {
        Cart cart = cartService.getCart(httpSession);
        if (cart.getTotalQuantity().intValue() == 0) {
            model.addAttribute("isEmpty", env.getProperty("emptyCartMessage"));
        }
        model.addAttribute("cart", cart);
        model.addAttribute("orderTypes", OrderType.values());
        model.addAttribute("productArrayDTO", new ProductArrayDTO(cart.getCartItems()));
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateCart(@Valid @ModelAttribute(name = "productArrayDTO") ProductArrayDTO productArrayDTO,
                             BindingResult bindingResult, Model model) {
        Cart cart = cartService.getCart(httpSession);
        List<Long> updatedProductIds = new ArrayList<>();
        IntStream.range(0, productArrayDTO.getProductDTOItems().size()).forEach(i -> {
            ProductDTO currentProductDTO = productArrayDTO.getProductDTOItems().get(i);
            if (bindingResult.getFieldErrors("productDTOItems[" + i + "].id").isEmpty()
                    && bindingResult.getFieldErrors("productDTOItems[" + i + "].quantity").isEmpty()) {
                updateItemInCart(currentProductDTO, cart, bindingResult, i, updatedProductIds);
            }
        });
        model.addAttribute("cart", cart);
        model.addAttribute("orderTypes", OrderType.values());
        model.addAttribute("updatedProductIds", updatedProductIds);
        model.addAttribute("successfulUpdateMessage", env.getProperty("successfulUpdateMessage"));
        model.addAttribute("productArrayDTO", productArrayDTO);
        return "cart";
    }

    private void updateItemInCart(ProductDTO productDTO, Cart cart, BindingResult bindingResult,
                                  int index, List<Long> updatedProductIds) {
        Long id = productDTO.getId();
        Long quantity = productDTO.getQuantity();
        if (id != null && quantity != null) {

            try {
                cartService.updateProduct(id, quantity, cart);
                updatedProductIds.add(id);
            } catch (OutOfStockException e) {
                bindingResult.rejectValue("productDTOItems[" + index + "].quantity",
                        "OutOfStock.productArrayDTO.productDTOItems.quantity");
            } catch (EmptyDatabaseArgumentException e) {
                bindingResult.rejectValue("productDTOItems[" + index + "].id",
                        "NoElement.productArrayDTO.productDTOItems.id");
            }
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String deleteFromCart(@PathVariable("id") Long id, Model model) throws NoElementWithSuchIdException {
        Product currentProduct = productService.getProductById(id.toString());
        Cart cart = cartService.getCart(httpSession);
        if (cart.getCartItems().isEmpty()) {
            return prepareModelForEmptyCart(cart, model);
        }
        cartService.removeProduct(currentProduct.getId(), cart);
        if (cart.getCartItems().isEmpty()) {
            return prepareModelForEmptyCart(cart, model);
        }
        model.addAttribute("message", env.getProperty("deleteFromCartMessage"));
        model.addAttribute("productArrayDTO", new ProductArrayDTO(cart.getCartItems()));
        model.addAttribute("cart", cart);
        model.addAttribute("orderTypes", OrderType.values());
        return "cart";
    }

    private String prepareModelForEmptyCart(Cart cart, Model model) {
        model.addAttribute("isEmpty", env.getProperty("emptyCartMessage"));
        model.addAttribute("cart", cart);
        return "cart";
    }

    @ExceptionHandler(NoElementWithSuchIdException.class)
    public String handle(NoElementWithSuchIdException ex) {
        return "redirect:/404?message=" + env.getProperty("noSuchIdException") + ex.getId();
    }
}
