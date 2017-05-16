package com.easytoolsoft.template.web.springmvc1.controller.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easytoolsoft.commons.support.i18n.LocaleUtils;
import com.easytoolsoft.commons.support.model.ResponseResult;
import com.easytoolsoft.template.data.mybatis.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登录页控制器
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Slf4j
@Controller
@RequestMapping(value = "/member")
public class LoginController {
    @Resource
    private EventService eventService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login/{lang}")
    public ModelAndView language(@PathVariable final String lang, final HttpServletRequest request,
                                 final HttpServletResponse response) {
        LocaleUtils.setLocale(lang, request, response);
        return new ModelAndView("redirect:/member/login");
    }

    @GetMapping(value = {"/login"})
    public String login(final Model model, final HttpServletRequest request, final HttpServletResponse response) {
        LocaleUtils.setInitLocale(request, response);
        log.info(LocaleUtils.getMessage("view.member.login.account"));
        return "member/login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "/member/login";
    }

    @ResponseBody
    @GetMapping(value = "/authenticate")
    public ResponseResult authenticate(final String account, final String password, final boolean rememberMe,
                                       final HttpServletRequest req) {
        final ResponseResult result = new ResponseResult<>(false, "用户名/密码错误!");
        try {
            final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account, password)
            );
            authentication.isAuthenticated();
            result.setSuccess(true);
            result.setMsg(LocaleUtils.getMessage("ctrl.member.login.success"));
        } catch (final Exception ex) {
            result.setMsg(LocaleUtils.getMessage("ctrl.member.login.IncorrectCredentialsException"));
        }
        this.eventService.add("authenticate", account, result.getMsg(), "INFO", req.getRequestURL().toString());
        return result;
    }
}