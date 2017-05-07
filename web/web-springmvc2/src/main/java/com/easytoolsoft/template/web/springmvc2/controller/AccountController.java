package com.easytoolsoft.template.web.springmvc2.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.easytoolsoft.springboot.template.service.EventService;
import com.easytoolsoft.template.web.springmvc2.model.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhiwei.deng
 * @date 2017-04-19
 **/
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Resource
    private EventService eventService;

    @GetMapping(value = {"/login"})
    public String login(final Model model) {
        model.addAttribute("username","tomdeng");
        return "account/login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "/account/login";
    }

    @ResponseBody
    @PostMapping(value = "/authenticate")
    public ResponseResult authenticate(final String account, final String password, final boolean rememberMe,
                                       final HttpServletRequest req) {
        final ResponseResult result = new ResponseResult<>(false, "用户名/密码错误!");
        this.eventService.add("authenticate", account, result.getMsg(), "INFO", req.getRequestURL().toString());
        return result;
    }
}
