package com.finewine.web.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String NotFoundPage() {
        return "404";
    }
}
