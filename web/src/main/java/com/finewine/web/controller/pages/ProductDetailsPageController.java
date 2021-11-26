package com.finewine.web.controller.pages;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.product.Product;
import com.finewine.core.service.product.ProductService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails/{id}")
public class ProductDetailsPageController {

    @Resource
    private Environment env;

    @Resource
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductDetails(@PathVariable("id") String id, Model model) throws NoElementWithSuchIdException {
        Product currentProduct = productService.getProductById(id);
        model.addAttribute("product", currentProduct);
        return "productDetails";
    }

    @ExceptionHandler(NoElementWithSuchIdException.class)
    public String handle(NoElementWithSuchIdException ex) {
        return "redirect:/404?message=" + "No element with such id: " + ex.getId();
    }
}
