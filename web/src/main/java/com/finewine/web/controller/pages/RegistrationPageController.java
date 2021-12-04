package com.finewine.web.controller.pages;

import com.finewine.core.exception.EmailAlreadyRegisteredException;
import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.user.CustomUserDTO;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationPageController {

    @Resource
    private CustomUserService customUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationPage(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/j_spring_security_logout";
        }
        model.addAttribute("customUserDTO", new CustomUserDTO());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerNewUser(@Validated @ModelAttribute(name = "customUserDTO") CustomUserDTO customUserDTO,
                                  BindingResult bindingResult, Principal principal, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                customUserService.save(customUserDTO);
                return "login";
            } catch (EmailAlreadyRegisteredException e) {
                bindingResult.rejectValue("username", "ExistEmail.customUserDTO.username");
                return "registration";
            }
        } else {
            return "registration";
        }
    }

    @ExceptionHandler(NoElementWithSuchIdException.class)
    public String handle(NoElementWithSuchIdException ex) {
        return "redirect:/404?message=" + "No element with such id: " + ex.getId();
    }
}
