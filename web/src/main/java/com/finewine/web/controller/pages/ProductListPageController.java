package com.finewine.web.controller.pages;

import com.finewine.core.model.product.Product;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@PropertySource("classpath:/values.properties")
@RequestMapping(value = "/productList")
public class ProductListPageController {

    @Resource
    private HttpSession httpSession;

    @Resource
    private CartService cartService;

    @Resource
    private ProductService productService;

    @Value("${value.quantityOnPage}")
    private Integer quantityOnPage;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                  Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("cart", cartService.getCart(httpSession));
        return "productList";
    }
}
