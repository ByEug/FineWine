package com.finewine.web.controller.pages.admin;

import com.finewine.core.model.logs.auth.AuthLog;
import com.finewine.core.model.logs.auth.AuthLogAction;
import com.finewine.core.model.user.CustomUser;
import com.finewine.core.service.logs.auth.AuthLogService;
import com.finewine.core.service.user.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping(value = "/success-login")
public class SuccessLoginController {

    @Resource
    private CustomUserService customUserService;

    @Resource
    private AuthLogService authLogService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationPage(Principal principal, Model model) {
        AuthLog authLog = new AuthLog();
        CustomUser customUser = customUserService.findByUsername(principal.getName());
        authLog.setCreatingDate(Date.valueOf(LocalDate.now()));
        authLog.setAction(AuthLogAction.Login);
        authLogService.save(authLog, customUser.getId());
        return "redirect:/productList";
    }
}
