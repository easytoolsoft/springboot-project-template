package com.easytoolsoft.springboot.template.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiwei.deng
 * @date 2017-04-19
 **/
@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {
    @GetMapping(value = {"", "/", "/index"})
    public String index(final Model model) {
        model.addAttribute("test","Hello,Wolrd!");
        return "home/index";
    }
}
