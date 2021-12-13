package com.finewine.web.controller.pages.admin;

import com.finewine.core.exception.IncorrectUrlParameterException;
import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.form.AddRoleDTO;
import com.finewine.core.model.logs.auction.AuctionLog;
import com.finewine.core.model.logs.auth.AuthLog;
import com.finewine.core.model.logs.inventory.InventoryLog;
import com.finewine.core.model.logs.order.OrderCreatingLog;
import com.finewine.core.model.order.OrderFullDataDTO;
import com.finewine.core.model.role.EnumRole;
import com.finewine.core.model.role.Role;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.logs.auction.AuctionLogService;
import com.finewine.core.service.logs.auth.AuthLogService;
import com.finewine.core.service.logs.inventory.InventoryLogService;
import com.finewine.core.service.logs.order.OrderCreatingLogService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(value = "/admin/users/{id}")
public class AdminUsersDetailsController {

    @Resource
    private CustomUserService customUserService;

    @Resource
    private AuthLogService authLogService;

    @Resource
    private AuctionLogService auctionLogService;

    @Resource
    private InventoryLogService inventoryLogService;

    @Resource
    private OrderCreatingLogService orderCreatingLogService;

    @RequestMapping(method = RequestMethod.GET)
    public String showUser(@PathVariable("id") String id, Model model) {
        CustomUser customUser = customUserService.findById(id);
        addLogsToModel(model, customUser.getId());
        model.addAttribute("roles", EnumRole.values());
        model.addAttribute("user", customUser);
        model.addAttribute("addRoleDTO", new AddRoleDTO());
        return "adminUsersDetails";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addRole(@PathVariable("id") String id,
                          @Validated @ModelAttribute(name = "addRoleDTO") AddRoleDTO addRoleDTO,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            throw new NoSuchElementException("Incorrect role");
        } else {
            CustomUser customUser = customUserService.findById(id);
            Role role = new Role();
            role.setRoleName(EnumRole.valueOf(addRoleDTO.getRole()));
            customUserService.updateRole(customUser, role);
            addLogsToModel(model, customUser.getId());
            model.addAttribute("roles", EnumRole.values());
            model.addAttribute("user", customUserService.findById(id));
            model.addAttribute("addRoleDTO", addRoleDTO);
            return "adminUsersDetails";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        customUserService.deleteUser(id);
        return "redirect:/admin/users";
    }

    private void addLogsToModel(Model model, Long id) {
        List<AuthLog> authLogList = authLogService.getForUserId(id);
        List<AuctionLog> auctionLogList = auctionLogService.getForUserId(id);
        List<InventoryLog> inventoryLogList = inventoryLogService.getForUserId(id);
        List<OrderCreatingLog> orderCreatingLogList = orderCreatingLogService.getForUserId(id);
        model.addAttribute("authLogList", authLogList);
        model.addAttribute("auctionLogList", auctionLogList);
        model.addAttribute("inventoryLogList", inventoryLogList);
        model.addAttribute("orderCreatingLogList", orderCreatingLogList);
    }

    @ExceptionHandler(NoElementWithSuchIdException.class)
    public String handleNoElement(NoElementWithSuchIdException ex) {
        return "redirect:/404?message=" + "No such id: " + ex.getId();
    }

    @ExceptionHandler(IncorrectUrlParameterException.class)
    public String handleIncorrectParameter(IncorrectUrlParameterException ex) {
        return "redirect:/404?message=" + "Incorrect parameter in the request url: " + ex.getParameter();
    }
}
