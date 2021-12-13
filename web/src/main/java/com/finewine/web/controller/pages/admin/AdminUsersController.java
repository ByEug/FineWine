package com.finewine.web.controller.pages.admin;

import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/admin/users")
public class AdminUsersController {

    @Resource
    private CustomUserService customUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("users", customUserService.getAllUsers());
        return "adminUsers";
    }
}
