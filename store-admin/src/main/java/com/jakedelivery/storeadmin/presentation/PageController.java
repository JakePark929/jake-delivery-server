package com.jakedelivery.storeadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("")
@Controller
public class PageController {
    @RequestMapping(path = {"", "/main"})
    public ModelAndView main() {
        return new ModelAndView("main");
    }

    @RequestMapping(path = {"/order"})
    public ModelAndView order() {
        return new ModelAndView("order/order");
    }
}
