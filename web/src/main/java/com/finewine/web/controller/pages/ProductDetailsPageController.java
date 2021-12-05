package com.finewine.web.controller.pages;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.cart.Cart;
import com.finewine.core.model.comment.Comment;
import com.finewine.core.model.form.CommentDTO;
import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductDTO;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.cart.CartService;
import com.finewine.core.service.comment.CommentService;
import com.finewine.core.service.product.ProductService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/productDetails/{id}")
public class ProductDetailsPageController {

    @Resource
    private HttpSession httpSession;

    @Resource
    private CartService cartService;

    @Resource
    private ProductService productService;

    @Resource
    private CommentService commentService;

    @Resource
    private CustomUserService customUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductDetails(@PathVariable("id") String id, Model model) throws NoElementWithSuchIdException {
        Product currentProduct = productService.getProductById(id);
        List<Comment> comments = commentService.getCommentsForProductId(id);
        model.addAttribute("product", currentProduct);
        model.addAttribute("comments", comments);
        model.addAttribute("commentDTO", new CommentDTO());
        model.addAttribute("cart", cartService.getCart(httpSession));
        model.addAttribute("productDTO", new ProductDTO());
        return "productDetails";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addComment(@PathVariable("id") String id, @Validated @ModelAttribute(name = "commentDTO") CommentDTO commentDTO,
                             BindingResult bindingResult, Principal principal, Model model) throws NoElementWithSuchIdException {
        if (principal == null) {
            return "/login";
        }
        Product currentProduct = productService.getProductById(id);
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        Cart cart = cartService.getCart(httpSession);
        if (!bindingResult.hasErrors()) {
            commentService.save(commentDTO.getCommentText(), customUser.getId(), id);
        }
        List<Comment> comments = commentService.getCommentsForProductId(id);
        model.addAttribute("product", currentProduct);
        model.addAttribute("comments", comments);
        model.addAttribute("commentDTO", commentDTO);
        model.addAttribute("cart", cart);
        model.addAttribute("productDTO", new ProductDTO());
        return "productDetails";
    }

    @ExceptionHandler(NoElementWithSuchIdException.class)
    public String handle(NoElementWithSuchIdException ex) {
        return "redirect:/404?message=" + "No element with such id: " + ex.getId();
    }
}
