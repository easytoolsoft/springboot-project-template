package com.easytoolsoft.template.web.springmvc1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home页控制器
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Controller
@RequestMapping(value = {"", "/"})
public class IndexController {
    @GetMapping(value = {"", "/", "/index"})
    public String index(final Model model) {
        return "index";
    }

}